package vn.edu.likelion.OfficeDemo;

import java.io.*;

public class CheckTab {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("StudentsList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                for (String value : values) {
                    System.out.println(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
