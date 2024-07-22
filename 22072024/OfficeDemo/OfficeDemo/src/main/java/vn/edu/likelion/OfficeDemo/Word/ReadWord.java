package vn.edu.likelion.OfficeDemo.Word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadWord {
    public static void main(String[] args) {
        //Doc file output
        File read = new File("C:\\Users\\Administrator\\Desktop\\OfficeDemo\\output.docx");
        try {
            InputStream in = new FileInputStream(read);
            XWPFDocument document = new XWPFDocument(in);

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                System.out.println(paragraph.getText());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
