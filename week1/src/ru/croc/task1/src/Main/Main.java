package Main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class Main {
    static class Point {
        BigDecimal x;
        BigDecimal y;
    }
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Point a = new Point();
        Point b = new Point();
        Point c = new Point();
        System.out.println("Введите координату х вершины №1:");
        a.x = scanner.nextBigDecimal();
        System.out.println("Введите координату y вершины №1:");
        a.y = scanner.nextBigDecimal();
        System.out.println("Введите координату х вершины №2:");
        b.x = scanner.nextBigDecimal();
        System.out.println("Введите координату y вершины №2:");
        b.y = scanner.nextBigDecimal();
        System.out.println("Введите координату х вершины №3:");
        c.x = scanner.nextBigDecimal();
        System.out.println("Введите координату y вершины №3:");
        c.y = scanner.nextBigDecimal();
        BigDecimal p = (((dist(a, b)).add(dist(b, c))).add(dist(a, c))).divide(BigDecimal.valueOf(2));
        System.out.println(p);
        MathContext mc
                = new MathContext(5);
        BigDecimal square = (p.multiply(p.subtract(dist(a, b))).multiply(p.subtract(dist(a, c))).multiply(p.subtract(dist(b, c)))).sqrt(mc);
        System.out.println("Площадь треугольника: " + square.stripTrailingZeros());
    }
    public static BigDecimal dist(Point a, Point b) {
        MathContext mc
                = new MathContext(10);
        return (((b.x.subtract(a.x)).pow(2)).add((b.y.subtract(a.y)).pow(2))).sqrt(mc) ;
    }
}