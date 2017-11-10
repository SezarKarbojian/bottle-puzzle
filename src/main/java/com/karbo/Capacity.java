package com.karbo;

public enum Capacity {
    THREE(3), FIVE(5);
    private final int value;

    Capacity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
