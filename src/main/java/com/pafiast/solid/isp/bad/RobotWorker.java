package com.pafiast.solid.isp.bad;

/**
 * Bad example of Interface Segregation Principle (ISP).
 * <p>
 * This class is forced to implement {@link Worker}, which includes methods {@code eat()} and {@code sleep()}
 * that do not apply to robots. This results in methods that throw exceptions.
 */
public class RobotWorker implements Worker {

    /**
     * {@inheritDoc}
     */
    @Override
    public void work() {
        String ignoredWork = "Robot working";
    }

    /**
     * Throws an exception because robots do not eat.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robots do not eat");
    }

    /**
     * Throws an exception because robots do not sleep.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public void sleep() {
        throw new UnsupportedOperationException("Robots do not sleep");
    }
}
