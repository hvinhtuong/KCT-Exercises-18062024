public class Vidu implements Runnable{
    public String name;

    public Vidu(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Runnable task1 = new Vidu("Xem diu tu be");
        Thread thread1 = new Thread(task1);
        Runnable task2 = new Vidu("Xem tốp tốp");
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i<10; i++) { System.out.println(name); }
    }
}
