package com.karbo;

public class Bottle {

    private int amount = 0;

    private final Capacity capacity;

    public Bottle(Capacity capacity) {
        this.capacity = capacity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        //amount can not be negative
        amount = amount < 0 ? 0 : amount;
        //amount can not be larger than capacity
        amount = amount > capacity.getValue() ? capacity.getValue() : amount;
        this.amount = amount;
    }

    public int getCapacity() {
        return capacity.getValue();
    }

    public boolean isEmpty() {
        if (amount == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (amount == getCapacity()) {
            return true;
        } else {
            return false;
        }
    }
}

