package com.pafiast.solid.ocp.bad;

public class DiscountCalculator {

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

