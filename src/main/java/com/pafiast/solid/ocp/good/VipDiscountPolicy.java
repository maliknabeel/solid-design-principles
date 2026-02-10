package com.pafiast.solid.ocp.good;

/**
 * VIP discount policy implementation.
 * Applies a 15% discount.
 */
public class VipDiscountPolicy implements DiscountPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    public double applyDiscount(double price) {
        return price * 0.15;
    }
}

