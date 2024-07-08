package Bai1;

import java.util.List;

public class firstThread implements Runnable{
    private List<Integer> list;

    public firstThread() {
        list = Application.list;
    }

    public void printNum(List<Integer> list) throws InterruptedException {
        for (int number: list) {
            if (number % 2 != 0) {
                System.out.println("Odd number from oddThread: " + number);
                Thread.sleep(1100);
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
