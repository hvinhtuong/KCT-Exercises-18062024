package Example;

import java.io.IOException;
import java.io.FileWriter;

public class FileWriters {

    public static void main(String[] args) {
        String desFile = "Class4.txt";
        String content = "hello class :D";

        FileWriter files = null;

        try {
            files = new FileWriter(desFile);
            files.write(content);
            System.out.println("Successfully");
        } catch (Exception io) {
            io.printStackTrace();
        } finally {
            try {
                files.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
