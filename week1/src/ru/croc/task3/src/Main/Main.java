package Main;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");
        int[] numbers = new int[str.length];
        for(int i = 0; i < str.length; ++i)
        {
            numbers[i] = Integer.parseInt(str[i]);
        }
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));

    }

}
