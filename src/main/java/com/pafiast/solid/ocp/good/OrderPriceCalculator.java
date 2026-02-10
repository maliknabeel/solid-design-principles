package com.pafiast.solid.ocp.good;

/**
 * Good example of Open/Closed Principle (OCP).
 * <p>
 * This class is closed for modification because it depends on the {@link DiscountPolicy} abstraction.
 * It is open for extension because new discount policies can be passed in without changing this class.
 */
public class OrderPriceCalculator {

    private final DiscountPolicy discountPolicy;

    /**
     * Constructs a calculator with a specific discount policy.
     *
     * @param discountPolicy the policy to use for price calculation
     */
    public OrderPriceCalculator(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    /**
     * Calculates the final price after applying the discount.
     *
     * @param price the original price
     * @return the final price
     */
    public double calculatePrice(double price) {
        return price - discountPolicy.applyDiscount(price);
    }
}

