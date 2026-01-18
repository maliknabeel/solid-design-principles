package com.pafiast.solid.isp.good;

public class HumanWorker implements Workable, Eatable, Sleepable {

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
