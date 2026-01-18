package com.pafiast.solid.ocp.good;

public class VipDiscountPolicy implements DiscountPolicy {

    @Override
    public double applyDiscount(double price) {
        return price * 0.15;
    }
}

