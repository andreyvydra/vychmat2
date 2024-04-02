package ru.itmoment.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static ru.itmoment.math.Constants.MATH_CONTEXT;

public abstract class FunctionOneVariable {
    private BigDecimal x1 = BigDecimal.valueOf(-10);
    private BigDecimal x2 = BigDecimal.TEN;

    private BigDecimal step = BigDecimal.valueOf(0.5);

    public abstract BigDecimal compute(BigDecimal x);

    public abstract BigDecimal computeFirstDerivative(BigDecimal x);

    public abstract BigDecimal computeSecondDerivative(BigDecimal x);

    public BigDecimal getLambda(BigDecimal a, BigDecimal b) throws InvalidIntervalException {
        BigDecimal l = BigDecimal.ZERO;
        while (a.compareTo(b) <= 0) {
            l = BigDecimal.valueOf(Math.max(computeFirstDerivative(a).abs().doubleValue(), l.doubleValue()));
            a = a.add(BigDecimal.valueOf(0.01));
        }
        if (computeFirstDerivative(a).compareTo(BigDecimal.ZERO) >= 0 && computeFirstDerivative(b).compareTo(BigDecimal.ZERO) >= 0)  {
            l = l.multiply(BigDecimal.valueOf(-1));
        } else if (computeFirstDerivative(a).compareTo(BigDecimal.ZERO) < 0 && computeFirstDerivative(b).compareTo(BigDecimal.ZERO) < 0) {
            l = l.multiply(BigDecimal.ONE);
        } else {
            throw new InvalidIntervalException();
        }
        return BigDecimal.ONE.divide(l, MATH_CONTEXT).setScale(6, RoundingMode.HALF_UP);
    }

    public BigDecimal phiFunction(BigDecimal x, BigDecimal l) {
        return compute(x).multiply(l).add(x).setScale(6, RoundingMode.HALF_UP);
    }

    public BigDecimal phiFirstDerivative(BigDecimal x, BigDecimal l) {
        return computeFirstDerivative(x).multiply(l).add(BigDecimal.ONE).setScale(6, RoundingMode.HALF_UP);
    }

    public void setX1(BigDecimal x1) {
        this.x1 = x1;
    }

    public void setX2(BigDecimal x2) {
        this.x2 = x2;
    }

    public void setStep(BigDecimal step) {
        this.step = step;
    }

    public BigDecimal getX1() {
        return x1;
    }

    public BigDecimal getX2() {
        return x2;
    }

    public BigDecimal getStep() {
        return step;
    }
}
