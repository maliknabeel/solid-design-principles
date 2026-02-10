package com.pafiast.solid.isp.good;

/**
 * Implementation of a human worker.
 * Humans implement {@link Workable}, {@link Eatable}, and {@link Sleepable} because they can do all three.
 */
public class HumanWorker implements Workable, Eatable, Sleepable {

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
