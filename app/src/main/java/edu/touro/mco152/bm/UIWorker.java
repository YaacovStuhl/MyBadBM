package edu.touro.mco152.bm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UIWorker {
    Boolean runInBackground() throws Exception;

    void process(List<DiskMark> markList);

    void done();

    Boolean goGet() throws InterruptedException, ExecutionException;

    void start();

    void publish(DiskMark mark);

    void setMarkProgress(int progress);

    boolean cancelAction(boolean mayInterruptIfRunning);

    boolean checkIsCancelled();

    void addPropertyChangeListener(PropertyChangeListener listener);




}
