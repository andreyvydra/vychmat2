package ru.itmoment.math.system;

import java.math.BigDecimal;

public abstract class FunctionTwoVariable {
    public abstract BigDecimal computeFunction(BigDecimal x, BigDecimal y);
    public abstract BigDecimal computeFDerivativeX(BigDecimal x, BigDecimal y);
    public abstract BigDecimal computeFDerivativeY(BigDecimal x, BigDecimal y);

    public abstract BigDecimal computeByX(BigDecimal x);
    public abstract BigDecimal computeByY(BigDecimal y);
}
