package edu.touro.mco152.bm.ui;

import edu.touro.mco152.bm.Observers.CustomObserver;
import edu.touro.mco152.bm.persist.DiskRun;

public class UIObserver implements CustomObserver {

    /**
     * An implementation of the {@link edu.touro.mco152.bm.Observers.CustomObserver CustomObserver} interface whose update() method will be called by the
     * {@link edu.touro.mco152.bm.Observers.CustomObservable CustomObservable} class and will update the UI
     * to reflect benchmark completion
     * */

    public UIObserver(){};

    @Override
    public void update(DiskRun run) {
        Gui.runPanel.addRun(run);
    }
}
