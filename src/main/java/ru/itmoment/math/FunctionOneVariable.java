package ru.itmoment.math;

import java.math.BigDecimal;

public abstract class FunctionOneVariable {
    private BigDecimal x1 = BigDecimal.valueOf(-10);
    private BigDecimal x2 = BigDecimal.TEN;

    private BigDecimal step = BigDecimal.valueOf(0.5);

    public abstract BigDecimal compute(BigDecimal x);

    public abstract BigDecimal computeFirstDerivative(BigDecimal x);

    public abstract BigDecimal computeSecondDerivative(BigDecimal x);

    public abstract BigDecimal getLambda(BigDecimal a, BigDecimal b);

    public abstract BigDecimal phiFunction(BigDecimal x, BigDecimal l);

    public abstract BigDecimal phiFirstDerivative(BigDecimal x, BigDecimal l);

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
