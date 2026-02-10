package com.pafiast.solid.ocp.good;

/**
 * Premium discount policy implementation.
 * Applies a 10% discount.
 */
public class PremiumDiscountPolicy implements DiscountPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    public double applyDiscount(double price) {
        return price * 0.10;
    }
}

