import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = "Hello, World!";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Hello world.txt", false))) {
            writer.append(str);
        }
        catch (IOException e){
           System.out.printf("Произошла ошибка при записи в файл: %s", e.getMessage());
        }
    }
}
