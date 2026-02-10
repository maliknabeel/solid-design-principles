package com.pafiast.solid.isp.good;

/**
 * Interface representing the ability to work.
 * Segregated from other capabilities to follow ISP.
 */
public interface Workable {

    /**
     * Performs work.
     */
    void work();
}

