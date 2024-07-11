package Example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreams {

    public static void main(String[] args) throws FileNotFoundException {
        //Link
        String fileName = "Class3.txt";
        //NOi dung file
        String content = "Hello class nhe..............................(updated)";

        //Khoi tao doi tuong
        FileOutputStream fos = null;
        try {
            //tao ra 1 instance voi ten Class3.txt
            fos = new FileOutputStream(fileName);
            fos.write(content.getBytes());
            System.out.println("Write file successfully.");
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                //Bat cuoc close
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
