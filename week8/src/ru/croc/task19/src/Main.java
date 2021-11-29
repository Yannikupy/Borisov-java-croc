import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = "Hello, World!";
        BufferedWriter writer = new BufferedWriter(new FileWriter("Hello world!", false));
        writer.append(str);
        writer.close();
    }
}
