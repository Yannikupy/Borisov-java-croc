package Main;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Locale.setDefault(new Locale("en", "US"));
        Scanner scanner = new Scanner(System.in);
        double bytes = scanner.nextDouble();
        if(bytes < 1024){
            System.out.println(bytes + " B");
        }
        if(bytes >= 1024 && bytes < Math.pow(1024, 2)){
            System.out.format("%.1f", bytes / 1024);
            System.out.println(" KB");
        }
        if(bytes >= Math.pow(1024, 2) && bytes < Math.pow(1024, 3)){
            System.out.format("%.1f", bytes / Math.pow(1024, 2));
            System.out.println(" MB");
        }
        if(bytes >= Math.pow(1024, 3) && bytes < Math.pow(1024, 4)){
            System.out.format("%.1f", bytes / Math.pow(1024, 3));
            System.out.println(" GB");
        }
        if(bytes >= Math.pow(1024, 4)){
            System.out.format("%.1f", bytes / Math.pow(1024, 4));
            System.out.println(" TB");
        }
    }
}
