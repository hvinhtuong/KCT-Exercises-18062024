package vn.edu.likelion.OfficeDemo.Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcel {
    public static void main(String[] args) throws IOException {

        String str = "Dữ liệu từ màn hình";
        XSSFWorkbook work = new XSSFWorkbook();
        //Tao 1 sheet trong workbook
        Sheet shit = work.createSheet();

        // Tao ra  1 row ben trong shit (row num la vi tri dong muon chen)
        Row rau = shit.createRow(0);
        // Tao ra 1 cell trong row
        Cell ceo1 = rau.createCell(0);
        ceo1.setCellValue("meo meo meo");
        Cell ceo2 = rau.createCell(1);
        ceo2.setCellValue("3 con meo");

        Row rau1 = shit.createRow(1);
        // Tao ra 1 cell trong row
        Cell ceo11 = rau1.createCell(0);
        ceo11.setCellValue("gau gau");
        Cell ceo22 = rau1.createCell(1);
        ceo22.setCellValue("dog");
//        Cell ceo3 = rau.createCell(2);
//        ceo3.setCellValue("3 Cats");
//        Cell ceo4 = rau.createCell(3);
//        ceo4.setCellValue(101010101);
//        Cell ceo5 = rau.createCell(4);
//        ceo5.setCellValue(true);

        try {
            FileOutputStream fos = new FileOutputStream("out.xlsx");
            work.write(fos);
            System.out.println("Ghi successful");
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
