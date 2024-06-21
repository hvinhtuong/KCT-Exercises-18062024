import static java.lang.Math.round;

public class Bai4 {

    public static void removeRepeatCharacters(StringBuilder str) {
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length()-1; i++) {
            for (int j = 1; j < str.length()-2; j++) {
                if ((str.charAt(i) == str.charAt(j)) && i != j) {
                    str.deleteCharAt(j);
                }
            }
        }
    }
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder("characters");
        System.out.println("Your string: " + str);
        removeRepeatCharacters(str);
        System.out.println("Your result: " + str);
    }
}
