package ru.itmoment.math.system;

import java.math.BigDecimal;

public class LnFunctionTwoVariable extends FunctionTwoVariable{
    @Override
    public BigDecimal computeFunction(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(Math.log(x.doubleValue())-y.doubleValue());
    }

    @Override
    public BigDecimal computeFDerivativeX(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(1/x.doubleValue());
    }

    @Override
    public BigDecimal computeFDerivativeY(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(-1);
    }

    @Override
    public BigDecimal computeByX(BigDecimal x) {
        return BigDecimal.valueOf(Math.log(x.doubleValue()));
    }

    @Override
    public BigDecimal computeByY(BigDecimal y) {
        return null;
    }

    @Override
    public String toString() {
        return "ln(x)-y=0";
    }
}
