package com.pafiast.solid.isp.good;

/**
 * Interface representing the ability to sleep.
 * Segregated from other capabilities to follow ISP.
 */
public interface Sleepable {

    /**
     * Sleeps.
     */
    void sleep();
}

