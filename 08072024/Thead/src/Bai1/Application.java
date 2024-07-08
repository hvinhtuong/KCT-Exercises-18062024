package Bai1;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);

    public static void main(String[] args) throws InterruptedException {
        Thread odd = new Thread(new firstThread());
        Thread even = new Thread(new twoThread());

        odd.start();
        even.start();

    }
}
