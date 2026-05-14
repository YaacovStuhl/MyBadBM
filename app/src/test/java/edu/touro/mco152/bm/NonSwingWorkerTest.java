package edu.touro.mco152.bm;

import java.io.File;
import java.util.Properties;

import edu.touro.mco152.bm.ui.MainFrame;
import edu.touro.mco152.bm.ui.Gui;
import static org.junit.jupiter.api.Assertions.*;

class NonSwingWorkerTest {



    /**
     * Bruteforce setup of static classes/fields to allow DiskWorker to run.
     *
     * @author lcmcohen
     */
    private static void setupDefaultAsPerProperties()
    {
        /// Do the minimum of what  App.init() would do to allow to run.
        Gui.mainFrame = new MainFrame();
        App.p = new Properties();
        App.loadConfig();

        Gui.progressBar = Gui.mainFrame.getProgressBar(); //must be set or get Nullptr

        // configure the embedded DB in .jDiskMark
        System.setProperty("derby.system.home", App.APP_CACHE_DIR);

        // code from startBenchmark
        //4. create data dir reference

        // may be null when tests not run in original proj dir, so use a default area
        if (App.locationDir == null) {
            App.locationDir = new File(System.getProperty("user.home"));
        }

        App.dataDir = new File(App.locationDir.getAbsolutePath()+File.separator+App.DATADIRNAME);

        //5. remove existing test data if exist
        if (App.dataDir.exists()) {
            if (App.dataDir.delete()) {
                App.msg("removed existing data dir");
            } else {
                App.msg("unable to remove existing data dir");
            }
        }
        else
        {
            App.dataDir.mkdirs(); // create data dir if not already present
        }
    }


    @org.junit.jupiter.api.Test
    void runInBackGround() throws Exception {
        DiskWorker uiWorker = new DiskWorker();
        NonSwingWorker nonSwingWorker = new NonSwingWorker();
        uiWorker.setBenchmarkWorker(nonSwingWorker);
        App.worker = uiWorker;//I assume this is a big no-no. I was just grasping at straws.
        //I kept getting a null pointer exception and wasn't sure how to fix it
        setupDefaultAsPerProperties();
        Boolean programRan = uiWorker.runInBackground();
        assertNotNull(programRan);
        assertNotEquals(0,nonSwingWorker.getMarkProgress());//I called this from
        //nonswingworker directly because I didnt make getMarkProgress part of the
        //interface because the swing version had no need of it. I assume this is a big no-no
    }
}