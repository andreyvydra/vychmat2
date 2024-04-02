package ru.itmoment.math;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class FourFunction extends FunctionOneVariable {

    public FourFunction() {
        setX1(BigDecimal.valueOf(-2));
        setX2(BigDecimal.valueOf(2));
        setStep(BigDecimal.valueOf(0.1));
    }

    @Override
    public BigDecimal compute(BigDecimal x) {
        return x.pow(4).add(x).subtract(BigDecimal.ONE).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal computeFirstDerivative(BigDecimal x) {
        return BigDecimal.valueOf(4).multiply(x.pow(3)).add(BigDecimal.ONE).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal computeSecondDerivative(BigDecimal x) {
        return BigDecimal.valueOf(12).multiply(x.pow(2)).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "x^4 + x - 1";
    }
}
