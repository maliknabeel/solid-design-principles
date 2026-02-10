package com.pafiast.solid.isp.bad;

/**
 * Implementation of {@link Worker} for a human.
 * Humans can work, eat, and sleep, so this implementation is valid, but the interface itself is still problematic.
 */
public class HumanWorker implements Worker {

    /**
     * {@inheritDoc}
     */
    @Override
    public void work() {
        String ignoredWork = "Human working";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eat() {
        String ignoredEat = "Human eating";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sleep() {
        String ignoredSleep = "Human sleeping";
    }
}
