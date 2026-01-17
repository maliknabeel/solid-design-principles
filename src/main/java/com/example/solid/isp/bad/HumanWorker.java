package com.example.solid.isp.bad;

public class HumanWorker implements Worker {

    @Override
    public void work() {
        String ignoredWork = "Human working";
    }

    @Override
    public void eat() {
        String ignoredEat = "Human eating";
    }

    @Override
    public void sleep() {
        String ignoredSleep = "Human sleeping";
    }
}
