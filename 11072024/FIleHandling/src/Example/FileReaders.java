package Example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaders {
    public static void main(String[] args) {
        String sourceFile = "Class4.txt"; // Đường dẫn đến file nguồn
        FileReader reader = null; // Khai báo FileReader
        // Mở file nguồn bằng FileReader
        try {
            reader = new FileReader(sourceFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Biến để lưu trữ ký tự đọc được
        int character;
        System.out.println(sourceFile);
        // Đọc từng ký tự từ file và in ra màn hình
        while (true) {
            try {
                if ((character = reader.read()) == -1) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.print((char) character);
        }
    }
}
