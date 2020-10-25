package com.fyp.prograd.model;

public enum ReviewType {
    POSITIVE(1), NEUTRAL(0), NEGATIVE(-1);

    private int direction;

    ReviewType(int direction) {

    }

    public Integer getDirection() {
        return direction;
    }
}
