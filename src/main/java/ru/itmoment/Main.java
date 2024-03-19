package ru.itmoment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        BigDecimal x = BigDecimal.valueOf(3);
        BigDecimal y = BigDecimal.valueOf(1);
        BigDecimal e = BigDecimal.valueOf(0.01);
        BigDecimal x_prev = BigDecimal.ZERO;
        BigDecimal y_prev = BigDecimal.ZERO;
        while (x.subtract(x_prev).abs().compareTo(e) > 0 && y.subtract(y_prev).abs().compareTo(e) > 0) {
            x_prev = x;
            y_prev = y;
            x = Functions.systemXRow(x, y);
            y = Functions.systemYRow(x_prev, y_prev);
            System.out.printf("%f\t%f\t%f\t%f\t%f\t%f\n", x, y, x_prev, y_prev, x.subtract(x_prev).abs(), y.subtract(y_prev).abs());
        }
    }

    public static void methodNewton() {
        BigDecimal a = BigDecimal.valueOf(-2.5);
        BigDecimal b = BigDecimal.valueOf(-1.5);
        BigDecimal e = BigDecimal.valueOf(0.01);
        BigDecimal x;
        if (Functions.functionFirstTask(a).multiply(Functions.functionSecondDerivative(a)).compareTo(BigDecimal.ZERO) > 0) {
            x = a;
        } else {
            x = b;
        }
        while (Functions.functionFirstTask(x).abs().compareTo(e) > 0) {
            System.out.printf("%f\t%f\t%f\t", x, Functions.functionFirstTask(x), Functions.functionFirstDerivative(x));
            BigDecimal x_prev = x;
            x = x.subtract(Functions.functionFirstTask(x).divide(Functions.functionFirstDerivative(x), new MathContext(5)));
            System.out.printf("%f\t%f\t\n", x, x_prev.subtract(x).abs());
        }
    }


    public static void methodSimpleIteration1() {
        BigDecimal a = BigDecimal.valueOf(-4);
        BigDecimal b = BigDecimal.valueOf(-3);
        BigDecimal e = BigDecimal.valueOf(0.01);
        BigDecimal x_prev = Functions.functionSimpleIterator(BigDecimal.valueOf(-4));
        BigDecimal x;
        while (true) {
            System.out.printf("%f\t", x_prev);
            x = Functions.functionSimpleIterator(x_prev);
            System.out.printf("%f\t", x);
            System.out.printf("%f\t", Functions.functionSimpleIterator(x));
            System.out.printf("%f\t", Functions.functionFirstTask(x));
            System.out.printf("%f\n", x.subtract(x_prev).abs());
            if (x_prev.subtract(x).abs().compareTo(e) < 0) {
                break;
            }
            x_prev = x;
        }
    }

    public static void methodHalfDiv() {
        BigDecimal a = BigDecimal.valueOf(2);
        BigDecimal b = BigDecimal.valueOf(3);
        BigDecimal e = BigDecimal.valueOf(0.01);
        while (a.subtract(b).abs().compareTo(e) > 0) {
            BigDecimal f1 = Functions.functionFirstTask(a);
            BigDecimal f2 = Functions.functionFirstTask(b);
            BigDecimal x = a.add(b).divide(BigDecimal.TWO);
            BigDecimal f3 = Functions.functionFirstTask(x);
            System.out.printf("%f\t%f\t%f\t%f\t%f\t%f\t%f\t\n", a, b, x, f1, f2, f3, a.subtract(b).abs());
            if (f1.compareTo(BigDecimal.ZERO) > 0 && f3.compareTo(BigDecimal.ZERO) < 0) {
                b = a;
                a = x;
            } else if (f1.compareTo(BigDecimal.ZERO) < 0 && f3.compareTo(BigDecimal.ZERO) > 0) {
                b = x;
            } else if (f2.compareTo(BigDecimal.ZERO) < 0 && f3.compareTo(BigDecimal.ZERO) > 0) {
                a = b;
                b = x;
            } else if (f2.compareTo(BigDecimal.ZERO) > 0 && f3.compareTo(BigDecimal.ZERO) < 0) {
                a = x;
            }
        }
    }
}