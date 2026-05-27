package edu.touro.mco152.bm.Observers;

import edu.touro.mco152.bm.persist.DiskRun;

public interface CustomObserver {
    /**
     * A custom Observer interface of which any  implementation of it will be able
     * to be registered to the {@link edu.touro.mco152.bm.Observers.CustomObservable CustomObservable}
     */

    public void update(DiskRun run);
}
