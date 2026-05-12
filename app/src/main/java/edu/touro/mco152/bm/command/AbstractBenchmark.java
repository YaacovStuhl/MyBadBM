package edu.touro.mco152.bm.command;

import edu.touro.mco152.bm.BenchmarkWorker;

import java.io.IOException;

public abstract class AbstractBenchmark implements simpleExecutor{

    public abstract boolean execute() throws IOException;

    public abstract boolean getSuccess();
}
