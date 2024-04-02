package ru.itmoment.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SinCosFunction extends FunctionOneVariable {

    public SinCosFunction() {
        setX1(BigDecimal.valueOf(-2));
        setX2(BigDecimal.valueOf(5));
        setStep(BigDecimal.valueOf(0.1));
    }

    @Override
    public BigDecimal compute(BigDecimal x) {
        return BigDecimal.valueOf(Math.sin(x.doubleValue()) + Math.cos(x.doubleValue() * 2) - 1).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal computeFirstDerivative(BigDecimal x) {
        return BigDecimal.valueOf(Math.cos(x.doubleValue()) - 2 * Math.sin(x.doubleValue() * 2)).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal computeSecondDerivative(BigDecimal x) {
        return BigDecimal.valueOf(-Math.sin(x.doubleValue()) - 4 * Math.cos(x.doubleValue() * 2)).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "sin(x)+cos(2x)-1";
    }
}
