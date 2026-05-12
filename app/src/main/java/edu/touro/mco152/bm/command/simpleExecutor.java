package edu.touro.mco152.bm.command;

import edu.touro.mco152.bm.BenchmarkWorker;

import java.io.IOException;

public interface simpleExecutor {
    boolean execute() throws IOException;
}
