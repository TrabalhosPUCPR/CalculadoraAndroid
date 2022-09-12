package com.example.calculadora;

import java.util.Arrays;

class Calculator {
    private final double[] firstNumber;
    private final double[] secondNumber;
    MainActivity.OPERATORS operator;
    boolean currentNumber;
    private boolean expectOperator;
    boolean doubleMode;

    Calculator() {
        this.firstNumber = new double[2];
        this.secondNumber = new double[2];
        this.reset();
    }

    public void addNumberDigit(int number){
        int index = this.doubleMode ? 1 : 0;

        if(expectOperator) reset();

        if (currentNumber) this.secondNumber[index] = (this.secondNumber[index]*10) + number;
        else this.firstNumber[index] = (this.firstNumber[index]*10) + number;
    }

    public void setOperator(MainActivity.OPERATORS operator) {
        if (this.operator == null) this.operator = operator;
        this.nextNumber();
    }

    private void nextNumber(){
        this.currentNumber = true;
        this.expectOperator = false;
        this.doubleMode = false;
    }

    public void reset(){
        Arrays.fill(this.firstNumber, 0);
        Arrays.fill(this.secondNumber, 0);
        this.operator = null;
        this.currentNumber = false;
        this.expectOperator = false;
        this.doubleMode = false;
    }

    public double calculate(){
        double result;
        switch (operator){
            case PLUS:
                result = getFirstNumber() + getSecondNumber();
                break;
            case MINUS:
                result = getFirstNumber() - getSecondNumber();
                break;
            case MULTI:
                result = getFirstNumber() * getSecondNumber();
                break;
            case DIV:
                result = getFirstNumber() / getSecondNumber();
                break;
            case MOD:
                result = getFirstNumber() % getSecondNumber();
                break;
            default:
                result = -1;
                break;
        }
        reset();
        this.firstNumber[0] = result;
        this.expectOperator = true;
        return this.firstNumber[0];
    }
    public double getFirstNumber(){
        double f = firstNumber[1];
        while (f >= 1){
            f /= 10;
        }
        return firstNumber[0] + f;
    }
    public double getSecondNumber(){
        double f = secondNumber[1];
        while (f >= 1){
            f /= 10;
        }
        return secondNumber[0] + f;
    }
}