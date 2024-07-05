-   **1\. Function<T, R>**

    Function nhận một đầu vào kiểu T và trả về một đầu ra kiểu R. Chúng ta sử dụng Function để chuyển đổi một chuỗi (String) thành độ dài của nó (Integer).

    Java

    ```
    Function<String, Integer> stringLengthFunction = String::length;

    List<String> strings = Arrays.asList("Java", "Functional", "Interface");
    List<Integer> lengths = strings.stream()
                                   .map(stringLengthFunction)
                                   .collect(Collectors.toList());

    System.out.println(lengths); // Output: [4, 10, 9]

    ```

    Hãy [thận trọng](/faq#coding) khi sử dụng các đoạn mã.

    content_copy

    *Hãy thận trọng khi sử dụng các đoạn mã.*

    -   **2\. Predicate<T>**

    Predicate nhận một đầu vào kiểu T và trả về một giá trị boolean. Chúng ta sử dụng Predicate để lọc các phần tử trong danh sách dựa trên một điều kiện.

    Java

    ```
    Predicate<String> longStringPredicate = s -> s.length() > 5;

    List<String> longStrings = strings.stream()
                                     .filter(longStringPredicate)
                                     .collect(Collectors.toList());

    System.out.println(longStrings); // Output: [Functional, Interface]

    ```

    Hãy [thận trọng](/faq#coding) khi sử dụng các đoạn mã.

    content_copy

    *Hãy thận trọng khi sử dụng các đoạn mã.*

    -   **3\. Consumer<T>**

    Consumer nhận một đầu vào kiểu T và không trả về giá trị. Chúng ta sử dụng Consumer để thực hiện một hành động trên từng phần tử của danh sách.

    Java

    ```
    Consumer<Integer> printLengthConsumer = System.out::println;

    lengths.forEach(printLengthConsumer); // Output: 4 10 9

    ```

    Hãy [thận trọng](/faq#coding) khi sử dụng các đoạn mã.

    content_copy

    *Hãy thận trọng khi sử dụng các đoạn mã.*

    -   **4\. Supplier<T>**

    Supplier không nhận đầu vào và trả về một giá trị kiểu T. Chúng ta sử dụng Supplier để tạo ra một danh sách mới từ một nguồn dữ liệu khác.

    Java

    ```
    Supplier<List<String>> stringListSupplier = () -> Arrays.asList("Lambda", "Expression");

    List<String> newStrings = stringListSupplier.get();
    System.out.println(newStrings); // Output: [Lambda, Expression]
    ```
