package com.pafiast.solid.isp.bad;

/**
 * Bad example of Interface Segregation Principle (ISP).
 * <p>
 * This interface is "fat" because it forces implementing classes to define methods
 * for working, eating, and sleeping, even if they don't support all of them.
 */
public interface Worker {

    /**
     * Performs work.
     */
    void work();

    /**
     * Eats food.
     */
    void eat();

    /**
     * Sleeps.
     */
    void sleep();
}

