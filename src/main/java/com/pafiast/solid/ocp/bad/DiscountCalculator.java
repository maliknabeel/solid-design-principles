package com.pafiast.solid.ocp.bad;

/**
 * Bad example of Open/Closed Principle (OCP).
 * <p>
 * This class violates OCP because it relies on conditional logic based on {@link CustomerType}.
 * Adding a new customer type requires modifying this class, which risks breaking existing functionality.
 */
public class DiscountCalculator {

    /**
     * Calculates the discount based on the customer type.
     *
     * @param customerType the type of customer
     * @param price        the original price
     * @return the discount amount
     */
    public double calculateDiscount(CustomerType customerType, double price) {
        if (customerType == CustomerType.STANDARD) {
            return price * 0.05;
        }
        if (customerType == CustomerType.PREMIUM) {
            return price * 0.10;
        }
        if (customerType == CustomerType.VIP) {
            return price * 0.15;
        }
        return 0.0;
    }
}

