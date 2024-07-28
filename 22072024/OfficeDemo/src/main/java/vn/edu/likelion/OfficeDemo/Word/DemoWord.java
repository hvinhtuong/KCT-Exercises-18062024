package vn.edu.likelion.OfficeDemo.Word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

public class DemoWord {
    public static void main(String[] args) {
        XWPFDocument document = new XWPFDocument(); // Tạo 1 tài liệu mới

        // Tạo 1 đoạn văn bản và thêm nội dung
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Xin chào, đây là file đầu tiên tôi viết file word \n");
        run.setText("Write Java file");

        // Ghi ra file output.docx
        try {
            FileOutputStream out = new FileOutputStream("output.docx");

            //Ghi cac gia tri document vao file
            document.write(out);
            System.out.println("Đã tạo file docx thành công!");
            document.close(); // Đóng tài liệu
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
