package edu.touro.mco152.bm.command;

import edu.touro.mco152.bm.BenchmarkRead;
import edu.touro.mco152.bm.BenchmarkWorker;
import edu.touro.mco152.bm.BenchmarkWrite;
import edu.touro.mco152.bm.persist.DiskRun;

import java.io.IOException;

public class Command implements simpleExecutor {

    boolean isReadTest;
    boolean isWriteTest;
    int numMarks;
    int diskBlocks;
    int blockSize;
    DiskRun.BlockSequence blockSequence;
    BenchmarkWorker benchmarkWorker;
    simpleExecutor simpleExecutor;

    public Command(boolean isWriteTest, boolean isReadTest, int numMarks, int diskBlocks, int blockSize,  DiskRun.BlockSequence blockSequence,  BenchmarkWorker benchmarkWorker )
    {
        this.isWriteTest = isWriteTest;
        this.isReadTest = isReadTest;
        this.numMarks = numMarks;
        this.diskBlocks = diskBlocks;
        this.blockSize = blockSize;
        this.blockSequence =  blockSequence;
        this.benchmarkWorker = benchmarkWorker;
        setSimpleExecutor();
    }

    private void setSimpleExecutor(){
        if(isReadTest){
            simpleExecutor = new BenchmarkRead(numMarks, diskBlocks, blockSize, blockSequence, benchmarkWorker);
        } else if (isWriteTest) {
            simpleExecutor = new BenchmarkWrite(numMarks, diskBlocks, blockSize, blockSequence, benchmarkWorker);
        }
    }

    @Override
    public boolean execute() throws IOException {
       return simpleExecutor.execute();
    }
}
