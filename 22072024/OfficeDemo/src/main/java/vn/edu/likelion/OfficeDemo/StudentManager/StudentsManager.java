package vn.edu.likelion.OfficeDemo.StudentManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/*
    - teacherA: Role Giao vien - Quan tri vien, co the thuc hien tat ca cac chuc nang
    - mentorB: Role Tro giang, co the thuc hien tât cac cac chuc nang ngoai tru ghi du lieu vao database
    - hocvien1: Role Học vien, chi co quyen duoc xem danh sach hoc sinh co mat trong lop
    - hocvien2: Role Hoc vien, co quyen Diem danh, xem danh sach hoc sinh vang, co mat, nhung khong the ghi du lieu vao databse
 */
public class StudentsManager {
    static Scanner scanner = new Scanner(System.in);
    static List<String> lines = new ArrayList<>();
    static int count = 0;
    static boolean checked = false; // Check xem hom nay da diem danh chua

    public static void main(String[] args) throws IOException {
        //Ghi du lieu file nguon Students
        readListTXT(0);   // Doc danh sach lop ghi vao array
        createExcel();         // Tao file excel luu danh sach hoc sinh co mat
        createWord();          // Tao file word luu danh sach hoc sinh vang mat
        start();
    }

    public static void start() throws IOException {
        System.out.println("------------STUDENTS MANAGER-----------");
        System.out.println("1. Đăng nhập             2. Đăng kí");
        System.out.print("Your chose: ");
        int chose = scanner.nextInt();
        while (chose != 1 && chose != 2) {
            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại. ");
            System.out.print("Your chose: ");
            chose = scanner.nextInt();
        };
        if (chose == 1) {
            scanner.nextLine();
            signIn();  // Dang nhap
        }
        if (chose == 2) {
            register();
            System.out.println("Register account successfully.");// Dang ki
            signIn();
        }
    }

     /*
      * menu - Menu cac chuc nang
      */
    public static void menu(Connection conn, String user, int position) throws IOException, SQLException {
        while (true) {
            System.out.println("---------------------- WELCOME " + user + " ------------------------");
            System.out.println("1. Điểm danh ngày " + LocalDate.now() + "      2. Xem danh sách vắng");
            System.out.println("3. Xem danh sach có mặt           4. Xuất dữ liệu vào sổ đầu bài");
            System.out.println("5. Xem danh sách lớp hôm nay      6. Log Out");
            System.out.print("Your chose: ");
            int num = scanner.nextInt();
            // Diem danh hom nay
            if (num == 1 && (position == 1 || position == 2)) {
                daily(lines);
                checked = true;
                continue;
            }
            //Xem danh sach vang tu file word
            if (num == 2 && (position == 1 || position == 2)) {
                System.out.println("Những tên vắng mặt: ");
                readWord();
                continue;
            }
            // Xem sanh sach co mat tu file excel
            if (num == 3) {
                System.out.println("Nhưng người có mặt hôm nay: ");
                readExcel();
                continue;
            }
            // Ghi du lieu vao database
            if (num == 4 && (position == 1)) {
                    writDB(conn, "StudentsList.txt");
                    System.out.println("Successful write to database!");
                    continue;
            }
            //Xem danh sach lop hom nay
            if (num == 5 && (position == 1 || position == 2)) {
                if (!checkModify("StudentsList.txt") || !checked) {
                    System.out.println("Chưa điểm danh hôm nay. Hãy điểm danh trước.");
                    continue;
                };
                readListTXT(num);
                continue;
            }
            // Dang xuat
            if (num == 6) {
                scanner.nextLine();
                System.out.println("Đăng xuất thành công. Đăng nhập lại để tếp tục.");
                signIn();
            }
            System.err.println("Bạn không có quyền chon option " + num +".");
        }
    }

     /*
      * checkRole - Kiem tra role user
      */
    public static void checkRole(String user, int position) {
            if (position == 1) {
                System.out.println("Đăng nhập thành công với tư cách Quản trị viên - " + user);
            }
            if (position == 2) {
                System.out.println("Đăng nhập thành công với tư cách Trợ giảng - " + user);
            }
            if (position == 3) {
                System.out.println("Đăng nhập thành công với tư cách Học viên - " + user);
            }
    }

     /*
      * signIn
      */
    public static void signIn() throws IOException {
        do {
            System.out.print("Username: ");
            String user = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl21",
                    "sys as sysdba", "admin123")) {

                String loginQuery = "SELECT TEN, PASS, POSITION FROM USERS WHERE TEN = ? AND PASS = ?";
                try (PreparedStatement login = con.prepareStatement(loginQuery)) {
                    String pass = Base64.encodeBase64String(password.getBytes());
                    login.setString(1, user);
                    login.setString(2, pass);

                    try (ResultSet rs = login.executeQuery()) {
                        if (rs.next()) {
                            int position = rs.getInt(3);
                            checkRole(user, position);
                            menu(con, user, position);
                        } else {
                            System.out.println("Tên tài khoản hoặc mật khẩu không chính xác.");
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        } while (true);
    }

     /*
      * register - Register user account
      */
     public static void register() {
         scanner.nextLine();
         Random rand = new Random();
         System.out.println("------------DANG KI TAI KHOAN------------");
         System.out.print("Username: ");
         String regisUser = scanner.nextLine();
         System.out.print("Password: ");
         String regisPass = scanner.nextLine();

         try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl21",
                 "sys as sysdba", "admin123")) {

             String regisQuery = "INSERT INTO Users (STT, TEN, PASS, POSITION) VALUES (?, ?, ?, ?) ";
             try (PreparedStatement regis = con.prepareStatement(regisQuery)) {
                 String pass = Base64.encodeBase64String(regisPass.getBytes());
                 regis.setInt(1, rand.nextInt(10));
                 regis.setString(2, regisUser);
                 regis.setString(3, pass);
                 regis.setInt(4, 3);
                 regis.executeUpdate();
             }
         } catch (SQLException e) {
             System.err.println("Lỗi: " + e.getMessage());
         }
     }

     /*
      * writDB - Ghi du lieu vao database
      */
    public static void writDB(Connection conn, String filePath) throws SQLException, IOException {
        String checkSql = "SELECT ten, status FROM Students WHERE ten = ?";
        String insertSql = "INSERT INTO Students (stt, ten, status) VALUES (?, ?, ?)";
        String updateSql = "UPDATE Students SET status = ? WHERE ten = ?";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             PreparedStatement checkExist = conn.prepareStatement(checkSql);
             PreparedStatement insert = conn.prepareStatement(insertSql);
             PreparedStatement update = conn.prepareStatement(updateSql)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                int id = Integer.parseInt(parts[0]);
                String ten = parts[1];
                int status = Integer.parseInt(parts[2]);

                checkExist.setString(1, ten);
                try (ResultSet rs = checkExist.executeQuery()) {
                    if (rs.next()) {
                        // Dòng đã tồn tại, cập nhật nếu trạng thái khác
                        update.setInt(1, status);
                        update.setString(2, ten);
                        update.executeUpdate();
                    } else {
                        // Dòng chưa tồn tại, thêm mới
                        insert.setInt(1, id);
                        insert.setString(2, ten);
                        insert.setInt(3, status);
                        insert.executeUpdate();
                    }
                } catch (SQLException e) {
                    System.err.println("Lỗi: " + e.getMessage());
                }
            }
        }
    }

     /*
      * daily - Diem danh hang ngay
      */
    public static void daily(List<String> lines) {
        count = 1;
        System.out.println("Điểm danh ngày " + LocalDate.now() + ":");
        for (int i = 0; i < lines.size(); i += 3) {
            System.out.print("STT: " + lines.get(i) + ", TÊN: " + lines.get(i + 1) + " (1 - Có mặt, 0 - Vắng): ");
            int status = scanner.nextInt();
            scanner.nextLine();

            while (status != 0 && status != 1) {
                System.out.print("Nhập không hợp lệ. Vui lòng nhập 1 (có mặt) hoặc 0 (vắng): ");
                status = scanner.nextInt();
                scanner.nextLine();
            }

            // Update status to lines arrray
            lines.set(i + 2, String.valueOf(status));
        }

        // Update StudentsList.txt
        try (PrintWriter writer = new PrintWriter("StudentsList.txt")) {
            for (int i = 0; i < lines.size(); i += 3) {
                writer.println(count++ + "\t" + lines.get(i + 1) + "\t" + lines.get(i + 2));
            }
        } catch (IOException e) {
            System.err.println("Error occur update StudentsList.txt: " + e.getMessage());
        }

        //Update word excel
        try {
            createExcel();
            createWord();
        } catch (IOException e) {
            System.err.println("Error occur writing Excel/Word: " + e.getMessage());
        }

        System.out.println("Điểm danh ngày " + LocalDate.now() + " thành công.");
        count = 0;
    }

     /*
      * checkModify - Check modify today yet
      */
    public static boolean checkModify(String sourceFile) {
        File file = new File(sourceFile);
        if (!file.exists()) {
            return false;
        }

        long lastModified = file.lastModified();
        LocalDate lastModifiedDate = Instant.ofEpochMilli(lastModified)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return lastModifiedDate.isEqual(LocalDate.now());
    }

    /*
     * createExcel - Create excel file
     */
    static void createExcel() throws IOException {
        count = 0;
        XSSFWorkbook work = new XSSFWorkbook();
        //Tao 1 sheet trong workbook
        Sheet shit = work.createSheet();

        Row rau0 = shit.createRow(0);
        // Tao ra 1 cell trong row
        Cell ceo00 = rau0.createCell(0);
        ceo00.setCellValue("STT");
        Cell ceo01 = rau0.createCell(1);
        ceo01.setCellValue("HO TEN HOC SINH VANG");

        //Tim hoc sinh co mat de ghi vao file
        for (int i = 0; i < lines.size();i+=3) {
            if (lines.get(i + 2).equalsIgnoreCase("1")) {

                count++;
                Row rau = shit.createRow(count);

                String hoTenEncoded = Base64.encodeBase64String(lines.get(i + 1).getBytes());
                Cell ceo1 = rau.createCell(0);
                ceo1.setCellValue(count);
                Cell ceo2 = rau.createCell(1);
                ceo2.setCellValue(hoTenEncoded);
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream("output.xlsx");
            work.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        count = 0; //Reset count
    }

    /*
     * createWord - Create word file
     */
    public static void createWord() throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        for (int i = 0; i < lines.size();i += 3) {
            if (lines.get(i + 2).equalsIgnoreCase("0")) {

                String hoTenEncoded = Base64.encodeBase64String(lines.get(i + 1).getBytes());
                run.setText(++count + ". \t" + hoTenEncoded);
                run.addCarriageReturn();
            }
        }
        // Ghi ra file output.docx
        try {
            FileOutputStream out = new FileOutputStream("output1.docx");
            //Ghi cac gia tri document vao file
            document.write(out);
            document.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * readExcel - Read excel file
     */
    public static void readExcel() throws IOException {
        // Đường dẫn đến file Excel
        FileInputStream in = new FileInputStream("output.xlsx");

        // Tạo workbook từ file Excel
        Workbook workbook = new XSSFWorkbook(in);
        // Lấy sheet đầu tiên từ workbook
        Sheet sheet = workbook.getSheetAt(0);
        // Duyệt qua từng dòng
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) continue;
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        String hoTenEncoded = cell.getStringCellValue();
                        String hoTenDecoded = new String(Base64.decodeBase64(hoTenEncoded));
                        System.out.print(hoTenDecoded);
                        break;
                    case NUMERIC:
                        System.out.print(rowIndex + ".\t");
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    default:
                        System.out.println(cell.getLocalDateTimeCellValue());
                }
            }
            System.out.println();
        }
        workbook.close();
        in.close();
    }

    /*
     * readWord - Read word file
     */
    public static void readWord() {
        File read = new File("output1.docx");
        try (InputStream in = new FileInputStream(read);
             XWPFDocument document = new XWPFDocument(in)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();

                String[] lines = text.split("\n");
                for (String line : lines) {
                    String[] parts = line.split("\t");
                    String hoTenEncoded = parts[1];
                    String hoTenDecoded = new String(Base64.decodeBase64(hoTenEncoded));
                    System.out.println(parts[0] + "\t" + hoTenDecoded);
                }
            }

        } catch (IOException ex) {
            System.err.println("Lỗi khi đọc file Word: " + ex.getMessage());
        }
    }


    /*
     * readListTXT - Save necessary list
     */
    public static void readListTXT(int num) throws IOException {
        String sourceFile = "StudentsList.txt";
        String line;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(sourceFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (num == 5) System.out.println("Danh sách lớp hôm nay,  " + LocalDate.now() + ":");
        while ((line = reader.readLine()) != null) {
            String[] values = line.split("\t");
            if (num == 0) {
                lines.addAll(Arrays.asList(values));
            } else {
                System.out.println("STT: " + values[0]
                        + ", TÊN: " + values[1]
                        + ", TRẠNG THÁI: " + ((values[2].equalsIgnoreCase("1")) ? "Có mặt":"Vắng mặt"));
            }
        }
        reader.close();
    }
}
