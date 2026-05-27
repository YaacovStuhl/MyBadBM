package edu.touro.mco152.bm.Observers;

import edu.touro.mco152.bm.persist.DiskRun;


public class TestObserver implements CustomObserver {
    boolean observerCalled = false;
    public TestObserver(){

    }

    @Override
    public void update(DiskRun run) {
        observerCalled = true;
    }

    public boolean getObserverCalled() {
        return observerCalled;
    }
}
