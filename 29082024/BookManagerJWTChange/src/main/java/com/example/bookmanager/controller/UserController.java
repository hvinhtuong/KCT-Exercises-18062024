package com.example.bookmanager.controller;

import com.example.bookmanager.model.AuthRequest;
import com.example.bookmanager.entity.UserEntity;
import com.example.bookmanager.model.ChangePasswordRequest;
import com.example.bookmanager.model.ResetPasswordRequest;
import com.example.bookmanager.repository.UserInfoRepository;
import com.example.bookmanager.service.JwtService;
import com.example.bookmanager.service.UserInfoService;
import com.example.bookmanager.service.UserService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.web.configuration.SpringWebMvcImportSelector;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private SpringWebMvcImportSelector springWebMvcImportSelector;

    @GetMapping("/welcome")
    @PreAuthorize("hasRole('admin')")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                return ResponseEntity.ok(new AuthResponse(token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserEntity userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('user')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('admin')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String mail;


    @PostMapping("/change-password")
    @Transactional
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {


        UserEntity user = getCurrentUser();

        if (!userService.checkPassword(user, request.getOldPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password is incorrect");
        }

        if (!userService.validatePasswordStrength(request.getNewPassword())) {
            return ResponseEntity.badRequest().body("Password is not enough strength.");
        }

        userService.updatePassword(user, request.getNewPassword());

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo("xmenhvt@gmail.com");
            helper.setSubject("Xác nhận thay đổi mật khẩu");

            Context context = new Context();
            context.setVariable("subject", "Doi mat khau");
            context.setVariable("message", "Doi mat khau");

            String htmlContent = templateEngine.process("confirm-reset-password-email", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi gửi email xác nhận");
        }
        return ResponseEntity.ok("Password changed successfully!");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> requestPasswordReset(@RequestParam String username) {
        UserEntity user = userService.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo("xmenhvt@gmail.com");
            helper.setSubject("Xác nhận thay đổi mật khẩu");

            Context context = new Context();
            context.setVariable("subject", "Doi mat khau");
            context.setVariable("message", "Doi mat khau");

            String htmlContent = templateEngine.process("confirm-reset-password-email", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi gửi email xác nhận");
        }

        return ResponseEntity.ok("Reset password request sent. Please check your email.");
    }

    @PostMapping("/reset-password/confirm")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequest request) {
//        if (!userService.isValidResetPasswordToken(token)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
//        }

        if (!userService.validatePasswordStrength(request.getNewPassword())) {
            return ResponseEntity.badRequest().body("New password is not strong enough.");
        }

        UserEntity user = userService.findUserByUsername(request.getUsername());
        userService.updatePassword(user, request.getNewPassword());

//        userService.deleteResetPasswordToken(token);

        try {
            MimeMessage message = mailSender.createMimeMessage();
//            mailSender
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("hvinhtuong@gmail.com");
            helper.setSubject("Xác nhận đặt lại mật khẩu thành công");

            Context context = new Context();
            String htmlContent = templateEngine.process("confirm-reset-password-email.html", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending reset password email");
        }

        return ResponseEntity.ok("Password reset successfully!");
    }

    private UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            UserEntity user = userInfoRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
        }
        throw new UsernameNotFoundException("You must login first");
    }

}
