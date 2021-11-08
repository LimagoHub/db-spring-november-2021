package de.client;

import de.math.Calculator;

public class CalcClient {

    private final Calculator calculator;

    public CalcClient(Calculator calculator) {
        this.calculator = calculator;
    }


    public void go() {

        System.out.println(calculator.add(3,4));
    }
}
