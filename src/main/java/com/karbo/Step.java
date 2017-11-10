package com.karbo;

public class Step {

    private int bigBottleAmount;
    private int smallBottleAmount;

    public Step(int bigBottleAmount, int smallBottleAmount) {
        this.bigBottleAmount = bigBottleAmount;
        this.smallBottleAmount = smallBottleAmount;
    }

    @Override
    public String toString() {
        return "Step{" +
                "bigBottleAmount=" + bigBottleAmount +
                ", smallBottleAmount=" + smallBottleAmount +
                '}';
    }
}
