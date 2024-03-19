package ru.itmoment.math;

import ru.itmoment.Functions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;
import org.apache.commons.lang3.tuple.Pair;

import static ru.itmoment.math.Constants.MATH_CONTEXT;


public class Methods {
    public static Pair<BigDecimal, Integer> methodHalfDiv(FunctionOneVariable f, BigDecimal a, BigDecimal b, BigDecimal e) throws InvalidIntervalException {
        BigDecimal x = null;
        int counter = 0;
        System.out.println(1);
        while (a.subtract(b).abs().compareTo(e) > 0) {
            BigDecimal f1 = f.compute(a);
            BigDecimal f2 = f.compute(b);
            x = a.add(b).divide(BigDecimal.TWO, MATH_CONTEXT);
            BigDecimal f3 = f.compute(x);
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
            } else {
                throw new InvalidIntervalException();
            }
            counter += 1;
        }
        if (Objects.isNull(x)) throw new InvalidIntervalException();
        return Pair.of(x, counter);
    }

    public static Pair<BigDecimal, Integer> methodSimpleIteration(FunctionOneVariable f, BigDecimal a, BigDecimal b, BigDecimal e) throws MathException {
        BigDecimal x_prev = Functions.functionSimpleIterator(a);
        BigDecimal x;
        BigDecimal l = f.getLambda(a, b);
        System.out.println(l);
        System.out.println(f.phiFirstDerivative(a, l));
        System.out.println(f.phiFirstDerivative(b, l));
        if (f.phiFirstDerivative(a, l).abs().compareTo(BigDecimal.ONE) > 0 || f.phiFirstDerivative(b, l).abs().compareTo(BigDecimal.ONE) > 0) {
            throw new MathException("Функция не сходится на отрезке!");
        }

        int counter = 0;
        while (true) {
            System.out.printf("%f\t", x_prev);
            x = f.phiFunction(x_prev, l);
            System.out.printf("%f\t", x);
            System.out.printf("%f\t", f.phiFunction(x, l));
            System.out.printf("%f\t", f.compute(x));
            System.out.printf("%f\n", x.subtract(x_prev).abs());
            if (x_prev.subtract(x).abs().compareTo(e) < 0) {
                break;
            }
            x_prev = x;
            counter++;
        }
        System.out.println(x);
        System.out.println(counter);
        return Pair.of(x, counter);
    }
}
