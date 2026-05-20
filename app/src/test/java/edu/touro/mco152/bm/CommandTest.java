package edu.touro.mco152.bm;

import edu.touro.mco152.bm.command.Command;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Properties;

import static edu.touro.mco152.bm.App.dataDir;
import static edu.touro.mco152.bm.App.multiFile;
import static edu.touro.mco152.bm.App.nextMarkNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CommandTest {

    private static final int RUN_NUM_MARKS = 25;
    private static final int RUN_NUM_BLOCKS = 128;
    private static final int RUN_BLOCK_SIZE_KB = 2048;
    private static final DiskRun.BlockSequence RUN_BLOCK_ORDER = DiskRun.BlockSequence.SEQUENTIAL;


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

    private static void refreshDataDirContents() {
        if (dataDir != null && dataDir.exists()) {
            Util.deleteDirectory(dataDir);
        }
        if (dataDir != null) {
            dataDir.mkdirs();
        }
    }

    @Test
    void writeBenchmarkCommandTest() throws Exception {
        setupDefaultAsPerProperties();
        refreshDataDirContents();

        multiFile = true;
        nextMarkNumber = 1;

        NonSwingWorker nonSwingWorker = new NonSwingWorker();
        Command command = new Command(true, false, RUN_NUM_MARKS, RUN_NUM_BLOCKS, RUN_BLOCK_SIZE_KB,
                RUN_BLOCK_ORDER, nonSwingWorker);
        assertTrue(command.execute());//I only made execute() return a boolean because I needed to be able to return a failure for the
        //readBenchmark, as it did in the original implementation. I think maybe this violates LSP but I wasn't sure
        //what else to do. So this test is technically useless but I included it for symmetry

        File firstMarkFile = new File(dataDir, "testdata1.jdm");
        long bytesPerBlock = 1024L * (long) RUN_BLOCK_SIZE_KB;
        long expectedLength = (long) RUN_NUM_BLOCKS * bytesPerBlock;
        long actualLength = firstMarkFile.length();
        assertEquals(expectedLength, actualLength);
        assertEquals(100, nonSwingWorker.getMarkProgress());
    }

    @Test
    void readBenchmarkCommandTest() throws Exception {
        setupDefaultAsPerProperties();
        refreshDataDirContents();

        multiFile = true;
        nextMarkNumber = 1;

        NonSwingWorker writeNonSwing = new NonSwingWorker();
        Command writeCommand = new Command(true, false, RUN_NUM_MARKS, RUN_NUM_BLOCKS, RUN_BLOCK_SIZE_KB,
                RUN_BLOCK_ORDER, writeNonSwing);
        writeCommand.execute();

        nextMarkNumber = 1;
        NonSwingWorker readNonSwing = new NonSwingWorker();
        Command readCommand = new Command(false, true, RUN_NUM_MARKS, RUN_NUM_BLOCKS, RUN_BLOCK_SIZE_KB,
                RUN_BLOCK_ORDER, readNonSwing);
        assertTrue(readCommand.execute());
        assertEquals(100, readNonSwing.getMarkProgress());
    }
}
