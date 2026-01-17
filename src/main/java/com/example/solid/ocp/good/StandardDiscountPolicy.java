package com.example.solid.ocp.good;

public class StandardDiscountPolicy implements DiscountPolicy {

    @Override
    public double applyDiscount(double price) {
        return price * 0.05;
    }
}

