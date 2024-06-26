package vn.edu.likelion.services;
import vn.edu.likelion.model.SpecificAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageAccount implements  IBanking{
    private List<SpecificAccount> specificAccounts = new ArrayList<>();
    private int accountIDcount = 1;
    private Scanner scanner = new Scanner(System.in);

    /*
     * listAccount - List all account
     */
    public void listAccount() {
        if (specificAccounts.isEmpty()) {
            System.out.println("Bạn chưa có tài khoản nào. Thêm tài khoản ngay !");
            return;
        }
        allCurrentAccount();
    }

    /*
     * addAccount - add new banking account
     */
    public void addAccount(String name, String category) {
        // Check duplicate account
            for (SpecificAccount existingAccount : specificAccounts) {
                if (existingAccount.getName().equalsIgnoreCase(name) && existingAccount != null) {
                    System.out.println("Tên tài khoản đã tồn tại!");
                    return;
                }
            }
        SpecificAccount specificAccount = new SpecificAccount(accountIDcount++, name, category, 0, 0.0125);
        specificAccounts.add(specificAccount);
        if (category.equalsIgnoreCase("save")) {
            System.out.println("Đã thêm tài khoản loại TIẾT KIỆM có tên -" + name + " - thành công!");
        } else {
            System.out.println("Đã thêm tài khoản loại thường ATM có tên -" + name + " - thành công!");
        }
    }

    public void detailAccount(int accountId) {
        SpecificAccount specificAccount = getAccountId(accountId);
        if (specificAccount == null) {
            System.out.println("Không tìm thấy Account ID vừa nhập.");
            return;
        }
        System.out.println("         ----ACCOUNT DETAIL-----");
        System.out.println("- ACCOUNT NAME: " + accountId);
        System.out.println("- ID: " + specificAccount.getName());
        System.out.println("- CATEGORY: " + specificAccount.getCategory());
        if (specificAccount.getCategory().equalsIgnoreCase("save")) {
            System.out.println("- SAVING ACCOUNT BALANCE: " + specificAccount.getBalance());
        } else {
            System.out.println("- NORMAL ACCOUNT BALANCE: " + specificAccount.getBalance());
        }
    }
    /*
     * Get list of account
     * Author: hvinhtuong
     */
    public List<SpecificAccount> getSpecificAccounts() {
        return specificAccounts;
    }

    /*
     * totalAccount - Get Account size
     * Author: hvinhtuong
     */
    public int totalAccount() {
        return specificAccounts.size();
    }

    /*
     * getAccountById - Get account via Id
     * Author: hvinhtuong
     */
    public SpecificAccount getAccountId(int id) {
        for (SpecificAccount specificAccount : specificAccounts) {
            if (specificAccount.getId() == id) {
                return specificAccount;
            }
        }
        return null;
    }

     /*
      * addBalance - Add account ballance via Id
      */
    @Override
    public void addBalance() {
        System.out.println("Danh sách tài khoản hiện tại:");
        allCurrentAccount();
        System.out.print("Nhập id tài khoản cần gửi: ");
        int id = scanner.nextInt();
        System.out.print("Nhập số tiền: ");
        double balance = scanner.nextDouble();
        for (SpecificAccount specificAccount : specificAccounts) {
            if (specificAccount.getId() == id) {
                double old = specificAccount.getBalance();
                specificAccount.setBalance(old+balance);
                System.out.print("Gửi thành công " + balance + " vào tài khoản "
                        + specificAccount.getName() + ". Số dư hiện tại: " + old+balance);
            }
        }
    }

     /*
      * withDraw - WithDraw via Account Id
      */
    @Override
    public void withDraw() {
        System.out.println("Danh sách tài khoản hiện tại:");
        allCurrentAccount();
        System.out.print("Nhập id tài khoản cần rút: ");
        int id = scanner.nextInt();
        for (SpecificAccount specificAccount : specificAccounts) {
            if (specificAccount == null) {
                System.out.println("Không tìm thấy Account ID vừa nhập.");
                return;
            }
        }
        System.out.print("Nhập số tiền: ");
        double draw = scanner.nextDouble();
        for (SpecificAccount specificAccount : specificAccounts) {
            if (specificAccount.getId() == id) {
                if (draw > 1000) {
                    System.out.println("Bạn không thể rút quá 1000$ :D.");
                    return;
                }
                double old = specificAccount.getBalance();
                specificAccount.setBalance(old-draw);
            }
        }
    }

    /*
     * interestRate - Count interest rate after 1 year
     */
    @Override
    public void interestRate() {
        System.out.println("*After applying interest 1.25%/yrs on Savings A/c for 1 year: ");
        for (SpecificAccount specificAccount : specificAccounts) {
            if (specificAccount == null) {
                System.out.println("Không tìm thấy Account ID nào.");
                return;
            }
            if (specificAccount.getCategory().equalsIgnoreCase("save")) {
                System.out.println("- Saving Account name " + specificAccount.getName() + " with balance: "
                        + specificAccount.getBalance()*1.0125);
            }
            if (specificAccount.getCategory().equalsIgnoreCase("atm")) {
                System.out.println("\"*After applying interest on Deposit A/c for 1 year does not change.");
                System.out.println("- Deposit account name " + specificAccount.getName() + " with balance: "+ specificAccount.getBalance());

            }
        }
    }

     /*
      * showBalance - Show account balance via Id
      */
    @Override
    public void showBalance() {
        System.out.println("Danh sách tài khoản hiện tại:");
        allCurrentAccount();
        System.out.print("Nhập id tài khoản cần xem số dư: ");
        int id = scanner.nextInt();
        for (SpecificAccount specificAccount : specificAccounts) {
            if (specificAccount.getId() == id) {
                System.out.println("         ----ACCOUNT BALANCE-----");
                System.out.println("- ACCOUNT NAME: " + id);
                System.out.println("- ID: " + specificAccount.getName());
                System.out.println("- CATEGORY: " + specificAccount.getCategory());
                System.out.println("- BALANCE: " + specificAccount.getBalance());
            }
        }
    }

     /*
      * allCurrentAccount - Show all Current account
      */
    public void allCurrentAccount() {
        System.out.println("STT| ID | Account Name         | Category |");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < specificAccounts.size(); i++) {
            SpecificAccount account = specificAccounts.get(i);
            System.out.printf("%-2d | %-2d | %-20s | %-8s |\n",
                    i + 1, account.getId(), account.getName(), account.getCategory());
        }
    }
}
