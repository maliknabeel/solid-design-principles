package com.pafiast.solid.isp.good;

/**
 * Implementation of a robot worker.
 * Robots only implement {@link Workable}, adhering to ISP by not being forced to implement unrelated methods.
 */
public class RobotWorker implements Workable {

    /**
     * {@inheritDoc}
     */
    @Override
    public void work() {
        String ignoredWork = "Robot working";
    }
}
