package vn.edu.likelion.OfficeDemo.PDF;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;

public class PDFExample {

    public static void writePDF () {

    }

    public static void createPDF () {
        try {
            PDDocument document = new PDDocument();

            PDPage page = new PDPage();
            PDPageContentStream content = new PDPageContentStream(document, page);
            // Set a standard font and size
            content.beginText();
            //Kiem tra toa do cua page
            System.out.println(page.getBleedBox());
            content.newLineAtOffset(20,772);
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            content.showText("This is my first PDF file");
            content.endText();

            // Lưu document PDF vào file
            File file = new File("output.pdf"); document.save(file);
            // Đóng content stream và document
            document.addPage(page);
            content.close();
            document.save(file);
            System.out.println("Successfully");
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
    createPDF();
    }
}
