package vn.edu.likelion.OfficeDemo.Resume;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;

class PdfGenerator {
    private PDDocument document;
    private PDPage page;
    private PDPageContentStream content;
    private int yOffset = 750;
    private PDType0Font font;
    private final float leading = 15f; // Khoảng cách giữa các dòng

    public PdfGenerator() throws IOException {
        document = new PDDocument();
        page = new PDPage();
        document.addPage(page);
        content = new PDPageContentStream(document, page);

        // Load font Arial
        try {
            font = PDType0Font.load(document, new File("D:\\KTC2024\\OfficeDemo\\OfficeDemo\\src\\main\\resources\\font\\arial.ttf"));
        } catch (IOException e) {
            System.err.println("Error loading font: " + e.getMessage());
        }
    }

    public void addHeader(Resume resume) throws IOException {
        addSectionTitle("Personal Information");
        addText("Name: " + resume.getName());
        addText("Email: " + resume.getEmail());
        addText("Phone: " + resume.getPhone());
        addText("Address: " + resume.getAddress());
        addEmptyLine();
    }

    public void addSummary(Resume resume) throws IOException {
        addSectionTitle("Summary");
        addText(resume.getSummary());
        addEmptyLine();
    }

    public void addSkills(Resume resume) throws IOException {
        addSectionTitle("Skills");
        for (String skill : resume.getSkills()) {
            addText("- " + skill);
        }
        addEmptyLine();
    }

    public void addExperience(Resume resume) throws IOException {
        addSectionTitle("Experience");
        for (String experience : resume.getExperiences()) {
            addText("- " + experience);
        }
        addEmptyLine();
    }

    public void addEducation(Resume resume) throws IOException {
        addSectionTitle("Education");
        for (String education : resume.getEducations()) {
            addText("- " + education);
        }
        addEmptyLine();
    }

    public void addCertificates(Resume resume) throws IOException {
        addSectionTitle("Certificates");
        for (String certificate : resume.getCertificates()) {
            addText("- " + certificate);
        }
        addEmptyLine();
    }

    public void save(String fileName) throws IOException {
        content.close();
        document.save(fileName);
        document.close();
        System.out.println("CV saved successfully to " + fileName);
    }

    private void addText(String text) throws IOException {
        float pageWidth = page.getMediaBox().getWidth() - 100;
        float fontSize = 12;
        float textWidth = font.getStringWidth(text) / 1000 * fontSize;

        if (textWidth < pageWidth) {
            content.beginText();
            content.setFont(font, fontSize);
            content.newLineAtOffset(50, yOffset);
            content.showText(text);
            content.endText();
            yOffset -= leading;
        } else {
            String[] words = text.split("\\s+");
            StringBuilder line = new StringBuilder();
            for (String word : words) {
                String tempLine = line + word + " ";
                float tempWidth = font.getStringWidth(tempLine) / 1000 * fontSize;
                if (tempWidth < pageWidth) {
                    line.append(word).append(" ");
                } else {
                    content.beginText();
                    content.setFont(font, fontSize);
                    content.newLineAtOffset(50, yOffset);
                    content.showText(line.toString());
                    content.endText();
                    yOffset -= leading;
                    line = new StringBuilder(word + " ");
                }
            }
            if (line.length() > 0) {
                content.beginText();
                content.setFont(font, fontSize);
                content.newLineAtOffset(50, yOffset);
                content.showText(line.toString());
                content.endText();
                yOffset -= leading;
            }
        }
    }

    private void addSectionTitle(String title) throws IOException {
        float fontSize = 14;
        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset(50, yOffset);
        content.showText(title);
        content.endText();
        yOffset -= leading;
    }

    private void addEmptyLine() throws IOException {
        yOffset -= leading;
    }
}
