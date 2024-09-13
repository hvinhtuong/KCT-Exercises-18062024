package com.example.bookmanager.service;

import com.example.bookmanager.entity.PasswordResetToken;
import com.example.bookmanager.entity.UserEntity;
import com.example.bookmanager.repository.PasswordResetTokenRepository;
import com.example.bookmanager.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserInfoRepository userInfoRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public UserService(UserInfoRepository userInfoRepository, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userInfoRepository = userInfoRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public UserEntity findUserByUsername(final String username) {
        return userInfoRepository.findByUsername(username);
    }

    public boolean checkPassword(UserEntity user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public void updatePassword(UserEntity user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userInfoRepository.save(user);
    }

    public String generateResetPasswordToken() {
        return UUID.randomUUID().toString();
    }

    public boolean validatePasswordStrength(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void createPasswordResetTokenForUser(final Optional<UserEntity> userOptional, final String token) {
        if (userOptional.isPresent()) {
            final UserEntity user = userOptional.get();
            final PasswordResetToken myToken = new PasswordResetToken(token, user);

            // Thiết lập thời gian hết hạn (ví dụ: 1 giờ sau thời điểm hiện tại)
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR_OF_DAY, 1);
            myToken.setExpiryDate(cal.getTime());

            passwordResetTokenRepository.save(myToken);
        } else {
            // Xử lý trường hợp không tìm thấy người dùng
            throw new UsernameNotFoundException("User not found");
        }
    }

    public boolean isValidResetPasswordToken(String token) {
        Optional<PasswordResetToken> passwordResetTokenOptional = passwordResetTokenRepository.findByToken(token);
        if (passwordResetTokenOptional.isPresent()) {
            PasswordResetToken passwordResetToken = passwordResetTokenOptional.get();
            return !isTokenExpired(passwordResetToken);
        }
        return false;
    }

    public UserEntity getUserByResetPasswordToken(String token) {
        Optional<PasswordResetToken> passwordResetTokenOptional = passwordResetTokenRepository.findByToken(token);
        if (passwordResetTokenOptional.isPresent()) {
            return passwordResetTokenOptional.get().getUser();
        }
        throw new UsernameNotFoundException("Token không hợp lệ hoặc đã hết hạn");
    }

    public void deleteResetPasswordToken(String token) {
        passwordResetTokenRepository.deleteByToken(token);
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    public void saveResetPasswordToken(UserEntity user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiryDate(calculateExpiryDate());
        passwordResetTokenRepository.save(passwordResetToken);
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1); // 1 hour expired
        return cal.getTime();
    }
}