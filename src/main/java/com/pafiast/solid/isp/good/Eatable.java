package com.pafiast.solid.isp.good;

/**
 * Interface representing the ability to eat.
 * Segregated from other capabilities to follow ISP.
 */
public interface Eatable {

    /**
     * Eats food.
     */
    void eat();
}

