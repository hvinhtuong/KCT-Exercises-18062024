import vn.edu.likelion.services.ManageAccount;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String name;
    static ManageAccount manageAccount = new ManageAccount();


    public static void firstPage() {
        System.out.print("\n================== VIETCOMBANK =================");
        System.out.println("\n================= LOGIN ACCOUNT ================");
        System.out.print("Account name/email/phone number: ");
        name = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();
        System.out.println("==================");
        System.out.println("Login successfully.");
        System.out.println("CHÀO MỪNG: " + name +" !");
    }

    public static void menu1() {
        System.out.print("\n=============== VIETCOMBANK DIGITAL BANING=================");
        System.out.println("\n================ ACCOUNT NAME: " + name +" ==============");
        System.out.printf("%-35s %-35s\n", "1. Xem danh sách tài khoản", "3. Xóa tài khoản");
        System.out.printf("%-35s %-35s\n", "2. Thêm tài khoản", "0. Thoát");
        System.out.print("Vui lòng chọn: ");
    }

    public static void menu2() {
        System.out.print("\n================== " + name + " =================");
        System.out.println("\n================== ACCOUNT PAGE ================");
        System.out.printf("%-35s %-35s\n", "1. Xem chi tiết tài khoản", "4. Tính lãi");
        System.out.printf("%-35s %-35s\n", "2. Gửi tiền", "5. Xem số dư");
        System.out.printf("%-35s %-35s\n", "3. Rút tiền", "0. Thoát");
        System.out.print("Vui lòng chọn: ");
    }


    public static void main(String[] args) {
        firstPage();
        while(true) {
            int opt = 0;
            menu1();
            try {
                opt = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
            }
            scanner.nextLine();
            switch (opt) {
                case 1: //Xem tài khoản
                    manageAccount.listAccount();
                    if (manageAccount.totalAccount() != 0) {
                        while (true) {
                            int opt1 = 0;
                            try {
                                menu2();
                                opt1 = scanner.nextInt();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                            }
                            scanner.nextLine();
                            switch (opt1) {
                                case 1:
                                    System.out.println("Nhập ID tài khoản cần xem: ");
                                    int id = scanner.nextInt();
                                    manageAccount.detailAccount(id);
                                    break;
                                case 2: // Gui tien
                                    manageAccount.addBalance();
                                    break;
                                case 3: // Rut tien
                                    manageAccount.withDraw();
                                    break;
                                case 4: // Tinh lai
                                    manageAccount.interestRate();
                                    break;
                                case 5: // Xem so du
                                    manageAccount.showBalance();
                                    break;
                                case 0:
                                    System.out.println("Kết thúc chương trình. Hẹn gặp lại!");
                                    return;
                                default:
                                    System.out.println("Lựa chọn không hợp lệ.");
                            }
                        }
                    }
                    break;
                case 2: // Thêm tài khoản
                    System.out.print("Nhập tên tài khoản muốn thêm: ");
                    String accountName = scanner.nextLine();
                    System.out.print("Nhập loại tài khoản (SAVE or ATM): \n" +
                            "save - Saving account \n" +
                            "atm - Normal account\n" +
                            "Your chose: ");
                    String category = null;
                    try {
                        category = scanner.nextLine();
                        if (category.equalsIgnoreCase("save") ||
                            category.equalsIgnoreCase("atm")) {
                        } else {
                            System.out.println("Chỉ có 2 loại tài khoản là SAVE(Saving Account) " +
                                    "và ATM(Normal Account)");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Loại tài khoản không hợp lệ, vui lòng nhập lại.");
                        scanner.next();
                    }
                    manageAccount.addAccount(accountName, category);
                    break;
                case 3:
                    System.out.print("Nhập ID tài khoản cần xóa: ");
                    String accountId = scanner.nextLine();
                    System.err.println("STUPID. YOU CAN'T DELETE YOUR ACOUNTS !!!");
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình. Hẹn gặp lại!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}