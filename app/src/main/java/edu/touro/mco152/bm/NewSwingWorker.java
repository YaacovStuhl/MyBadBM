package edu.touro.mco152.bm;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewSwingWorker extends SwingWorker<Boolean, DiskMark> implements BenchmarkWorker {
    @Override
    public Boolean runInBackground() throws Exception {
        return doInBackground();
    }


    @Override
    protected Boolean doInBackground() throws Exception {
        return App.worker.runInBackground();
    }

    @Override
    public void process(List<DiskMark> markList) {
        App.worker.process(markList);
    }

    @Override
    public void done() {
        App.worker.done();
    }

    @Override
    public void start() {
        execute();
    }


    @Override
    public void publish(DiskMark mark) {
        super.publish(mark);
    }

    @Override
    public void setMarkProgress(int progress) {
        super.setProgress(progress);
    }


    @Override
    public boolean cancelAction(boolean mayInterruptIfRunning) {
        return cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean checkIsCancelled() {
        return isCancelled();
    }


    @Override
    public Boolean goGet() throws InterruptedException, ExecutionException {
        return super.get();
    }
}
