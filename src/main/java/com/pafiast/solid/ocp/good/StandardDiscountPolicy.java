package com.pafiast.solid.ocp.good;

/**
 * Standard discount policy implementation.
 * Applies a 5% discount.
 */
public class StandardDiscountPolicy implements DiscountPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    public double applyDiscount(double price) {
        return price * 0.05;
    }
}

