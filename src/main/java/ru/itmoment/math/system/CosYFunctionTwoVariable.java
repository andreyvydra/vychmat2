package ru.itmoment.math.system;

import java.math.BigDecimal;

public class CosYFunctionTwoVariable extends FunctionTwoVariable {
    @Override
    public BigDecimal computeFunction(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(x.doubleValue() - Math.cos(y.doubleValue()) - 3);
    }

    @Override
    public BigDecimal computeFDerivativeX(BigDecimal x, BigDecimal y) {
        return BigDecimal.ONE;
    }

    @Override
    public BigDecimal computeFDerivativeY(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(Math.sin(y.doubleValue()));
    }

    @Override
    public BigDecimal computeByX(BigDecimal x) {
        return null;
    }

    @Override
    public BigDecimal computeByY(BigDecimal y) {
        return BigDecimal.valueOf(Math.cos(y.doubleValue()) + 3);
    }

    @Override
    public String toString() {
        return "x-cos(y)=3";
    }
}
