package com.pafiast.solid.ocp.good;

public class OrderPriceCalculator {

    private final DiscountPolicy discountPolicy;

    public OrderPriceCalculator(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public double calculatePrice(double price) {
        return price - discountPolicy.applyDiscount(price);
    }
}

