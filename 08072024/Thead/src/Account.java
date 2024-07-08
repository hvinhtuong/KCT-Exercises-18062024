public class Account extends Thread{
    private String name;
    private double amount;

    public Account(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public synchronized void withDraw(double amount) {
        if (this.amount>=  amount) {
            this.amount -= amount;
            System.out.println("Contain: " + this.amount);
        } else {
            System.out.println("Not enought money");
        }
    }

    @Override
    public void run() {
        this.withDraw(10);
    }
}
