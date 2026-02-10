package com.pafiast.solid.ocp.good;

/**
 * Interface defining a discount policy.
 * Adheres to OCP by allowing new policies to be added without modifying existing code.
 */
public interface DiscountPolicy {

    /**
     * Calculates the discount amount for a given price.
     *
     * @param price the original price
     * @return the discount amount
     */
    double applyDiscount(double price);
}

