package vn.edu.likelion.Bai1;

public class UNTLN implements IMax{

    public UNTLN(int num) {
        findMax(num);
    }

    /*
     * checkPrime - Check Prime num
     */
    public static int checkPrime(int num) {
        for (int i = 2; i <= num/2; i++) {
            if (num % i == 0) {
                return 0;
            }
        }
        return num;
    }

    // Find Maximum Uoc nguyen to
    @Override
    public void findMax(int str) {
        for (int i = str/2 -1 ; i >=2; i--) {
            if (checkPrime(str) == str) {
                System.out.print("Uoc nguyen to lon nhat: " + str + "\n");
                return;
            }
            if ((checkPrime(i) == i) && (str % i == 0)) {
                System.out.print("Uoc nguyen to lon nhat: " + i + "\n");
                return;
            }
        }
    }
}
