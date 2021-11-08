package main;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];  //path is like ./resources/Test.txt
        Path path = Paths.get(fileName);
        Scanner sc = new Scanner(path);
        int counter = 0;
        while (sc.hasNext()){
            String word = sc.next();
            if(!Objects.equals(word, "?") && !Objects.equals(word, ".") && !Objects.equals(word, ",") && !Objects.equals(word, ";") && !Objects.equals(word, "/") && !Objects.equals(word, "!")) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
