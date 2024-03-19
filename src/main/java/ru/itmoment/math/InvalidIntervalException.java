package ru.itmoment.math;

public class InvalidIntervalException extends MathException {
    public InvalidIntervalException() {
        super("Невалидный интервал для поиска значений!");
    }
}
