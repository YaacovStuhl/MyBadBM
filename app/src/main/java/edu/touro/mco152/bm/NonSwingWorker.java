package edu.touro.mco152.bm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NonSwingWorker implements UIWorker{

    int progress = 0;

    @Override
    public Boolean runInBackground() throws Exception {
        return App.worker.runInBackground();
    }

    @Override
    public void process(List<DiskMark> markList) {

    }

    @Override
    public void done() {

    }

    @Override
    public Boolean goGet() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void publish(DiskMark mark) {

    }

    @Override
    public void setMarkProgress(int progress) {
        if ((progress < 0) || (progress > 100)) {
            throw new IllegalArgumentException("progress must be between 0 and 100");
        }
        this.progress = progress;

    }

    public int getMarkProgress() {
        return progress;
    }

    @Override
    public boolean cancelAction(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean checkIsCancelled() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

}
