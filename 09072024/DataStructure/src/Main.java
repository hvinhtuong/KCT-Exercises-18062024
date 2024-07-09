import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo và định nghĩa 1 Array
        String[] arr = { "JAVA", "REACTJS", "JSP", "VUEJS" };
        // Khởi tạo 1 ArrayList
        ArrayList<String> list = new ArrayList<String>();
        // Chuyển đổi Array thành ArrayList
        Collections.addAll(list, arr);
        // Thêm phần tử vào ArrayList sau khi chuyển đổi
        list.add("C#");
        list.add("PHP");
        System.out.println("ArrayList sau khi chuyển đổi từ Array: " + list);

    }
}