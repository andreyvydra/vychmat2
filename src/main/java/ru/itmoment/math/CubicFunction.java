package ru.itmoment.math;

import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static ru.itmoment.math.Constants.MATH_CONTEXT;

public class  CubicFunction extends FunctionOneVariable {
    public CubicFunction() {
        setX1(BigDecimal.valueOf(-5));
        setX2(BigDecimal.valueOf(3));
        setStep(BigDecimal.valueOf(0.5));
    }

    @Override
    public BigDecimal compute(BigDecimal x) {
        return x.pow(3).
                add(BigDecimal.valueOf(2.84).multiply(x.pow(2))).
                subtract(BigDecimal.valueOf(5.606).multiply(x)).
                subtract(BigDecimal.valueOf(14.766)).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal computeFirstDerivative(BigDecimal x) {
        return x.pow(2).
                multiply(BigDecimal.valueOf(3)).
                add(x.multiply(BigDecimal.valueOf(2 * 5.606))).
                subtract(BigDecimal.valueOf(5.606)).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal computeSecondDerivative(BigDecimal x) {
        return x.
                multiply(BigDecimal.valueOf(6)).
                add(BigDecimal.valueOf(2 * 5.606)).
                setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "x^3 + 2.84x^2 - 5.606x - 14.766";
    }
}
