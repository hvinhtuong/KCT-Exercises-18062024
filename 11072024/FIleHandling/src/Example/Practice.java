package Example;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Practice extends Thread {
    static Scanner scanner = new Scanner(System.in);
    static  int count = 0;
    static List<String> lines = new ArrayList<>();

    /*
     * readSourceFile - Doc file nguon
     */
    public static void readSourceFile(String fileName, String flag) throws IOException {
        String sourceFile = "StudentsList.txt"; // Đường dẫn đến file nguồn
        File file = new File(sourceFile);
        String line;
        BufferedReader reader = null; // Khai báo BufferedReader
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

        if (file.delete()) {
            writeFLAG(fileName, lines, flag); // Ghi temporaty list vao file dich
        } else {
            System.err.println("Khog the xoa file " + sourceFile);
        }

        // Continue ghi sau khi bi gian doan
        if (flag.equalsIgnoreCase("Y") && count != lines.size()) {
            System.out.print("ERROR!!!!!!! Chuong trinh da bi gian doan tai " + (count-1) + "... type Y to continue, N to exit!!!: ");
            String str = scanner.nextLine();
            if (str.equalsIgnoreCase("Y")) {
                writeFLAG(fileName, lines, flag); // Ghi temporaty list vao file dich
            }
        }
    }

    /*
     * write - Ghi vao file dich
     */
    public static int writeFLAG(String fileName, List<String> lines, String flag) throws IOException {
        String destFile = fileName; // Đường dẫn đến file đích
        FileWriter file = new FileWriter(fileName, true);
        BufferedWriter writer = null; // Khai báo BufferedWriter

        // Mở (hoặc tạo) file đích bằng FileWriter và BufferedWriter
        writer = new BufferedWriter(file);
        // Chuỗi dữ liệu cần ghi
        // Write to file dich
        for (int i = count; i < lines.size(); i++) {
            // Ghi chuỗi vào file,
            try {
                writer.write(lines.get(i)+ "\n");
                count++;
                System.out.println("Write successfull data: " + lines.get(i) + " - vào File đích.\n");
                if (flag.equalsIgnoreCase("Y") && i == 5) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        writer.close();
        return count;
    }

    /*
     * write - Ghi vao file dich - NO FLAG
     */
    public static void write(String fileName, List<String> lines) throws IOException {
        String destFile = fileName; // Đường dẫn đến file đích
        FileWriter file = new FileWriter(fileName, true);
        BufferedWriter writer = null; // Khai báo BufferedWriter

        // Mở (hoặc tạo) file đích bằng FileWriter và BufferedWriter
        writer = new BufferedWriter(file);
        // Chuỗi dữ liệu cần ghi
        // Write to file dich
        for (int i = count; i < lines.size(); i++) {
            // Ghi chuỗi vào file,
            try {
                writer.write(lines.get(i)+ "\n");
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
        System.out.println("--------Tiến trình Ghi file chuẩn bị bắt đầu.........---------");
        System.out.print("Bạn có muốn chương trinhg bị ngắt quảng không? (Y/N): ");
        String flag = scanner.nextLine();
        readSourceFile(fileName, flag);
        System.out.println("Đã ghi file thành công.");
    }

}
