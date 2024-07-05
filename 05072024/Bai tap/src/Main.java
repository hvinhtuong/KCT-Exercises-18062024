import java.util.function.Consumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            int TEST_NUMBER = 5;
            Consumer<Integer> math1 = (e) -> System.out.println(e * 2);
            Consumer<Integer> math2 = (e) -> System.out.println(e * e);
            Consumer<Integer> math3 = (e) -> System.out.println(e % 2 == 1);
// thực hiện phương thức accept của lần lượt 3 Consumer
            math1.accept(TEST_NUMBER);
            math2.accept(TEST_NUMBER);
            math3.accept(TEST_NUMBER);
// thực hiện tuần tự Consumer
            Consumer<Integer> combineConsumer = math1.andThen(math2).andThen(math3);
            combineConsumer.accept(TEST_NUMBER);
    }
}