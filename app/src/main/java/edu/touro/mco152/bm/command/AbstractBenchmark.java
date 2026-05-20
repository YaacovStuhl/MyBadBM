package edu.touro.mco152.bm.command;


import java.io.IOException;

public abstract class AbstractBenchmark implements simpleExecutor{

    public abstract boolean execute() throws IOException;

}
