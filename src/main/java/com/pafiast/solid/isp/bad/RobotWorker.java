package com.pafiast.solid.isp.bad;

public class RobotWorker implements Worker {

    @Override
    public void work() {
        String ignoredWork = "Robot working";
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robots do not eat");
    }

    @Override
    public void sleep() {
        throw new UnsupportedOperationException("Robots do not sleep");
    }
}
