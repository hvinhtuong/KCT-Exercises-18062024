package Example;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Baitap extends Thread {
    static Scanner scanner = new Scanner(System.in);

    /*
     * readSourceFile - Doc file nguon
     */
    public static void readSourceFile(String fileName) throws IOException {
        String sourceFile = "StudentsList.txt"; // Đường dẫn đến file nguồn
        String line;
        BufferedReader reader = null; // Khai báo BufferedReader
        List<String> lines = new ArrayList<>();
        // Mở file nguồn bằng FileReader và BufferedReader
        try {
            reader = new BufferedReader(new FileReader(sourceFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Đọc từng dòng từ file và in ra màn hình
        while ((line = reader.readLine()) != null) {
            lines.add(line);; //Save to list and write to goal
        }
        reader.close();

        //Write data to temporary file
        write("StudentsList-Copy-Recovery.txt", lines);

        File file = new File(sourceFile);
        if (file.delete()) {
            // Ghi temporaty list vao file dich
            write(fileName, lines);
        } else {
            System.err.println("Khog the xoa file " + sourceFile);
        }
    }

    /*
     * write - Ghi vao file dich
     */
    public static void write(String fileName, List<String> lines) throws IOException {
        String destFile = fileName; // Đường dẫn đến file đích
        BufferedWriter writer = null; // Khai báo BufferedWriter

        // Mở (hoặc tạo) file đích bằng FileWriter và BufferedWriter
        try {
            writer = new BufferedWriter(new FileWriter(destFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Chuỗi dữ liệu cần ghi
        // Write to file dich
        for (int i = 0; i < lines.size(); i++) {
            // Ghi chuỗi vào file,
            try {
                writer.write(lines.get(i)+ "\n");
                System.out.println("Write successfull data: " + lines.get(i) + " - vào File đích.\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Chào mừng bạn đến với chương trình ghi file StudentsList: ");
        System.out.print("Nhập name file đích (bao gồm cả đuôi file): ");
        String fileName = scanner.nextLine();
        readSourceFile(fileName);
        System.out.println("Đã ghi file thành công.");
    }

}
