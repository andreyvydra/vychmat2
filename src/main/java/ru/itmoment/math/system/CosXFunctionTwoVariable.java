package ru.itmoment.math.system;

import java.math.BigDecimal;

public class CosXFunctionTwoVariable extends FunctionTwoVariable {
    @Override
    public BigDecimal computeFunction(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(Math.cos(x.doubleValue() - 1) + y.doubleValue() - 0.5);
    }

    @Override
    public BigDecimal computeFDerivativeX(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(-Math.sin(x.doubleValue() - 1));
    }

    @Override
    public BigDecimal computeFDerivativeY(BigDecimal x, BigDecimal y) {
        return BigDecimal.ONE;
    }

    @Override
    public BigDecimal computeByX(BigDecimal x) {
        return BigDecimal.valueOf(-Math.cos(x.doubleValue() - 1) + 0.5);
    }

    @Override
    public BigDecimal computeByY(BigDecimal y) {
        return null;
    }

    @Override
    public String toString() {
        return "cos(x-1)+y=0.5";
    }
}
