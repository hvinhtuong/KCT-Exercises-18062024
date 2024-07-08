package Bai1;

import java.util.Arrays;
import java.util.List;

public class twoThread implements Runnable {
    private List<Integer> list;

    public twoThread() {
        list = Application.list;
    }

        public void printNum(List<Integer> list) throws InterruptedException {
        for (int number: list) {
            if (number % 2 == 0) {
                System.out.println("Even number from evenThread: " + number);
                Thread.sleep(1000);
            }
        }
    }

    @Override
    public void run() {
        try {
            this.printNum(list);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
