package de.math;


// Runas Admin
public class CalculatorImpl implements Calculator {


    // Role allowed Admin
    @Override
    public double add(double a, double b){
        return a + b;
    }

    // Role allowed Guest
    @Override
    public double sub(double a, double b) {
        return add(a,-b);
    }
}
