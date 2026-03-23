package edu.touro.mco152.bm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;




class AppTest {


    private int savedBlockSizeKb;
    private int savedNumOfBlocks;
    @BeforeEach
    void save() {
        savedBlockSizeKb = App.blockSizeKb;
        savedNumOfBlocks = App.numOfBlocks;
    }
    @AfterEach
    void restore() {
        App.blockSizeKb = savedBlockSizeKb;
        App.numOfBlocks = savedNumOfBlocks;
    }
    /**Tests P in BICEP by assessing if the method can be called a large quantity of
     * times and still fit within a very short time frame
     */
    @Test
    void targetMarkSizeKbManyCalls() {
        App.blockSizeKb = 512;
        App.numOfBlocks = 32;
        assertTimeout(Duration.ofMillis(15), () -> {
            for (int i = 0; i < 5_000_000; i++) {
                App.targetMarkSizeKb();
            }
        });
    }

    /**Tests the C in BICEP for the {@link edu.touro.mco152.bm.App#targetMarkSizeKb()} by
     * assessing if the method call matches with hard coded multiplication*/
    @Test
    void targetMarkSizeKbCrossCheckAgainstMultiplication() {
        App.blockSizeKb = 512;
        App.numOfBlocks = 32;

        long expectedKb = (long) App.blockSizeKb * (long) App.numOfBlocks;
        assertEquals(expectedKb, App.targetMarkSizeKb());
    }
}