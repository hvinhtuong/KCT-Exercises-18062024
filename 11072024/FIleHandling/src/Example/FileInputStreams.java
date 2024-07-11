package Example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreams {

    public static void main(String[] args) {

        // Có thể gõ tiếng việt không tôi cũng không biết nuữa khi nào ta yêu nhau. Đây là bản test tiêng Việt trên hệ điêều hành Window
        //Các hệ điều hành khác có thể không gõ được tiếng Việt như mong muốn nếu bạn thật sự muốn gõ tếng Việt thì hãy làm theo cách sau

        String sourceFile = "Class3.txt"; // Đường dẫn đến file nguồn
        FileInputStream inputStream = null;// Khai báo FileInputStream
// Mở file nguồn bằng FileInputStream
        try {
            inputStream = new FileInputStream(sourceFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
// Buffer để đọc dữ liệu
        byte[] buffer = new byte[1024];
        int bytesRead;
// Đọc dữ liệu từ file và in ra màn hình
        while (true) {
            try {
                if ((bytesRead = inputStream.read(buffer)) == -1) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.print(new String(buffer, 0, bytesRead));
        }

    }
}
