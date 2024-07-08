public class cpu {
    private static Long getCurrentCPU() {
        return java.lang.management
                .ManagementFactory
                .getThreadMXBean()
                .getThreadCpuTime(Thread.currentThread().getId());
    }
    public static void main(String[] args) {
        System.out.println("Thread " + Thread.currentThread().getName()
                + " is running on CPU core: " + getCurrentCPU());
    }
}
