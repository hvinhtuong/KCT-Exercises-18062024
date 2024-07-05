import java.util.Optional;

public class ViduOptional {
    public static void main(String[] arg) {
        // OPtional empty
        Optional<Person> opt = Optional.empty();
        System.out.println(opt);
        Optional<String> test = opt.map(Person::getName);
        System.out.println("Test case");
        test.ifPresent(System.out::println);


        //OPtional non-null
        String str = "Tuong";
        Optional<String> str1 = Optional.ofNullable(str);
        str1.ifPresent(System.out::println);

        Person per = new Person();
        //Optional null
        Optional<Person> optional = Optional.ofNullable(per);
        Optional<String> str4 = optional.map(Person::getName);
        System.out.print("Person class name: " );
        str4.ifPresent(System.out::println);
    }
}
