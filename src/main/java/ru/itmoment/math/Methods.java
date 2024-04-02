package ru.itmoment.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import org.apache.commons.lang3.tuple.Pair;

import static ru.itmoment.math.Constants.MATH_CONTEXT;


public class Methods {
    public static Pair<BigDecimal, Integer> methodHalfDiv(FunctionOneVariable f, BigDecimal a, BigDecimal b, BigDecimal e) throws InvalidIntervalException {
        BigDecimal x = null;
        int counter = 0;
        while (a.subtract(b).abs().compareTo(e) > 0 && counter != 50) {
            BigDecimal f1 = f.compute(a);
            BigDecimal f2 = f.compute(b);
            x = a.add(b).divide(BigDecimal.TWO, MATH_CONTEXT);
            BigDecimal f3 = f.compute(x);
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
            } else {
                throw new InvalidIntervalException();
            }
            counter += 1;
        }
        if (Objects.isNull(x)) throw new InvalidIntervalException();
        return Pair.of(x, counter);
    }

    public static Pair<BigDecimal, Integer> methodSimpleIteration(FunctionOneVariable f, BigDecimal a, BigDecimal b, BigDecimal e) throws MathException {
        BigDecimal x = null;
        BigDecimal l = f.getLambda(a, b);
        BigDecimal x_prev = f.phiFunction(a, l);
        BigDecimal a1 = a;
        while (a1.compareTo(b) <= 0) {
            if (f.phiFirstDerivative(a, l).abs().compareTo(BigDecimal.ONE) > 0) {
                throw new MathException("Функция не сходится на отрезке!");
            }
            a1 = a1.add(BigDecimal.valueOf(0.01));
        }
        int counter = 0;
        while (counter != 50) {
            x = f.phiFunction(x_prev, l);
            if (x_prev.subtract(x).abs().compareTo(e) < 0) {
                break;
            }
            x_prev = x;
            counter++;
        }
        return Pair.of(x, counter);
    }

    public static Pair<BigDecimal, Integer> methodSimpleNewton(FunctionOneVariable f, BigDecimal a, BigDecimal b, BigDecimal e) throws MathException {
        BigDecimal xi1;
        BigDecimal xi;
        if (f.compute(a).multiply(f.computeSecondDerivative(a)).compareTo(BigDecimal.ZERO) > 0) {
            xi1 = a;
            xi = xi1.add(f.getStep());
        } else {
            xi1 = b;
            xi = xi1.subtract(f.getStep());
        }
        BigDecimal x = xi;
        int counter = 0;
        while (f.compute(x).abs().compareTo(e) > 0 && counter != 50) {
            x = xi.subtract(xi.subtract(xi1).
                    divide(f.compute(xi).subtract(f.compute(xi1)), MATH_CONTEXT).
                    multiply(f.compute(xi))).setScale(6, RoundingMode.HALF_UP);
            xi1 = xi;
            xi = x;
            counter++;
        }

        return Pair.of(x, counter);
    }

}
