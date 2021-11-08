package de.math;

public class CalculatorSecure implements Calculator{
    private final Calculator calculator;

    public CalculatorSecure(Calculator calculator) {
        this.calculator = calculator;
    }

    public double add(double a, double b) {
        System.out.println("Du kommst rein");
        return calculator.add(a, b);
    }

    public double sub(double a, double b) {
        return calculator.sub(a, b);
    }
}
