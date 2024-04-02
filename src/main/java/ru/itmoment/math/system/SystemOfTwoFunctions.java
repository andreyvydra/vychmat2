package ru.itmoment.math.system;


import org.apache.commons.lang3.tuple.Pair;
import ru.itmoment.math.Constants;

import java.math.BigDecimal;
import java.math.MathContext;

public class SystemOfTwoFunctions {
    private FunctionTwoVariable function1;
    private FunctionTwoVariable function2;

    private BigDecimal a1;
    private BigDecimal a2;
    private BigDecimal b1;
    private BigDecimal b2;

    public SystemOfTwoFunctions(FunctionTwoVariable f1, FunctionTwoVariable f2, BigDecimal a1, BigDecimal a2, BigDecimal b1, BigDecimal b2) {
        function1 = f1;
        function2 = f2;
        this.a1 = a1;
        this.a2 = a2;
        this.b1 = b1;
        this.b2 = b2;
    }

    public Pair<Integer, Pair<BigDecimal, BigDecimal>> solveByNewton(BigDecimal a, BigDecimal b, BigDecimal e) {
        int counter = 0;
        while (counter != 50) {
            BigDecimal f = BigDecimal.ZERO.subtract(function1.computeFunction(a, b));
            BigDecimal g = BigDecimal.ZERO.subtract(function2.computeFunction(a, b));
            BigDecimal[][] c  = {
                    {function1.computeFDerivativeX(a, b), function1.computeFDerivativeY(a, b)},
                    {function2.computeFDerivativeX(a, b), function2.computeFDerivativeY(a, b)}
            };
            BigDecimal det = c[0][0].multiply(c[1][1]).subtract(c[0][1].multiply(c[1][0]));
            BigDecimal detX = f.multiply(c[1][1]).subtract(c[0][1].multiply(g));
            BigDecimal detY = c[0][0].multiply(g).subtract(f.multiply(c[1][0]));

            BigDecimal x = a.add(detX.divide(det, Constants.MATH_CONTEXT));
            BigDecimal y = b.add(detY.divide(det, Constants.MATH_CONTEXT));
            if (a.subtract(x).abs().compareTo(e) <= 0 && b.subtract(y).abs().compareTo(e) <= 0) {
                break;
            }
            a = x;
            b = y;
            counter++;
            System.out.println(x + " " + y);
            System.out.println();
        }
        return Pair.of(counter, Pair.of(a, b));

    }

    public BigDecimal computeFirstFunction(BigDecimal x, BigDecimal y) {
        return function1.computeFunction(x, y);
    }

    public BigDecimal computeSecondFunction(BigDecimal x, BigDecimal y) {
        return function2.computeFunction(x, y);
    }

    public FunctionTwoVariable getFunction1() {
        return function1;
    }

    public FunctionTwoVariable getFunction2() {
        return function2;
    }

    public BigDecimal getA1() {
        return a1;
    }

    public BigDecimal getA2() {
        return a2;
    }

    public BigDecimal getB1() {
        return b1;
    }

    public BigDecimal getB2() {
        return b2;
    }

    @Override
    public String toString() {
        return function1.toString() + "\n" + function2.toString();
    }
}
