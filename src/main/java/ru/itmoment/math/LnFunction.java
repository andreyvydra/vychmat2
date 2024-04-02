package ru.itmoment.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LnFunction extends FunctionOneVariable {

    public LnFunction() {
        setX1(BigDecimal.valueOf(0.1));
        setX2(BigDecimal.valueOf(2));
        setStep(BigDecimal.valueOf(0.1));
    }

    @Override
    public BigDecimal compute(BigDecimal x) {
        return BigDecimal.valueOf(Math.log(x.doubleValue()) * x.doubleValue()).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal computeFirstDerivative(BigDecimal x) {
        return BigDecimal.valueOf(Math.log(x.doubleValue()) + 1).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal computeSecondDerivative(BigDecimal x) {
        return BigDecimal.valueOf(1 / (x.doubleValue())).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "xln(x)";
    }
}
