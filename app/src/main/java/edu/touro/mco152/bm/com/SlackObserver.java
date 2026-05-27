package edu.touro.mco152.bm.com;

import edu.touro.mco152.bm.Observers.CustomObserver;
import edu.touro.mco152.bm.persist.DiskRun;


public class SlackObserver implements CustomObserver {

    SlackManager slackManager = new SlackManager("YStuhlBadBM");

    /**
     * An implementation of the {@link edu.touro.mco152.bm.Observers.CustomObserver CustomObserver} interface whose update() method will be called by the
     * {@link edu.touro.mco152.bm.Observers.CustomObservable CustomObservable} class and will send a slack message marking
     * benchmark completion to our Slack channel
     */


    public SlackObserver(){};

    @Override
    public void update(DiskRun run) {
        if(run.getRunMax() > run.getRunAvg() * 1.05){
            slackManager.postMsg2OurChannel(":smile: Benchmark completed by YStuhl. We would have like the Max run to be faster");
        }
    }
}
