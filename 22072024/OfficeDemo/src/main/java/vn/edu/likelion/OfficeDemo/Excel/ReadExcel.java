package vn.edu.likelion.OfficeDemo.Excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;

public class ReadExcel {
    public static void main(String[] args) throws IOException {
        // Đường dẫn đến file Excel
        FileInputStream in = new FileInputStream("C:\\Users\\Administrator" +
                "\\Desktop\\OfficeDemo\\out.xlsx");

        // Tạo workbook từ file Excel
        Workbook workbook = new XSSFWorkbook(in);
        // Lấy sheet đầu tiên từ workbook
        Sheet sheet = workbook.getSheetAt(0);
        // Duyệt qua từng dòng của sheet và in ra giá trị của từng ô
        for (Row row : sheet) { for (Cell cell : row) {
            switch (cell.getCellType()) {
                case STRING:
                    System.out.println(cell.getStringCellValue() + " ");
                    break;
                case NUMERIC:
                    System.out.println(cell.getNumericCellValue());
                    break;
                case BOOLEAN:
                    System.out.println(cell.getBooleanCellValue());
                    break;
                default:
                    System.out.println(cell.getLocalDateTimeCellValue());
            }
        }
        }
        workbook.close();
        in.close();
    }
}
