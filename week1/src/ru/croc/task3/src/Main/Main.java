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
        int min = 0, max = 0, temp_min = 0, temp_max = 0;
        int[] numbers2 = new int [numbers.length];
        for(int i = 0; i < numbers.length; ++i){

            numbers2[i] = numbers[i];

        }
        Arrays.sort(numbers2);
        min = numbers2[0];
        System.out.println(min);
        max = numbers2[numbers2.length - 1];
        for(int i = 0; i < numbers.length; ++i){

            if(numbers[i] == min) temp_min = i;
            if(numbers[i] == max) temp_max = i;

        }
        System.out.println(max);
        int temp_num1 = numbers[0];
        int temp_num2 = numbers[numbers.length - 1];
        numbers[0] = min;
        numbers[temp_min] = temp_num1;
        numbers[numbers.length - 1] = max;
        numbers[temp_max] = temp_num2;
        System.out.println(Arrays.toString(numbers));
    }
}
