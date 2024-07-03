import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int x = 5;
        int y= 10;

        int z = toDo(x, y, service::num);
        System.out.println(" Tinh tong x va y = " + z);
    }

    public static  int toDo(int x, int y, Calcu calcu) {
        return calcu.num(x, y);
    }
}