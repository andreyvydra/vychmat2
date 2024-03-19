package ru.itmoment;

import java.math.BigDecimal;
import java.math.MathContext;

public class Functions {
    public static BigDecimal functionFirstTask(BigDecimal x) {
        return x.pow(3).add(BigDecimal.valueOf(2.84).multiply(x.pow(2))).subtract(BigDecimal.valueOf(5.606).multiply(x)).subtract(BigDecimal.valueOf(14.766));
    }

    public static BigDecimal functionSimpleIterator(BigDecimal x) {
        return x.pow(3).add(BigDecimal.valueOf(2.84).multiply(x.pow(2))).subtract(BigDecimal.valueOf(5.606).multiply(x)).subtract(BigDecimal.valueOf(14.766)).divide(BigDecimal.valueOf(-19.674), new MathContext(5)).add(x);
    }

    public static BigDecimal functionSecondDerivative(BigDecimal x) {
        return x.multiply(BigDecimal.valueOf(6)).add(BigDecimal.valueOf(2 * 5.606));
    }

    public static BigDecimal functionFirstDerivative(BigDecimal x) {
        return x.pow(2).multiply(BigDecimal.valueOf(3)).add(x.multiply(BigDecimal.valueOf(2 * 5.606))).subtract(BigDecimal.valueOf(5.606));
    }

    public static BigDecimal systemXRow(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(Math.cos(y.doubleValue()) + 3);
    }

    public static BigDecimal systemYRow(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(-Math.cos(x.doubleValue() - 1) + 0.5);
    }
}
