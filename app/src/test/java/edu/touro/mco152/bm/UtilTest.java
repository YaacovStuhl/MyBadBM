package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.DecimalFormat;

class UtilTest {
    /**Tests for the B in BICEP specifically the second C in CORRECT by checking for
     * cardinality in a case where there should be only 1 outcome*/
    @Test
    void randIntCardinality() {
        for(int i = 0; i < 100; i++) {
        int result = Util.randInt(5, 5);
        assertEquals(5, result);
        }
    }
    /**Tests for the B in BICEP specifically the T in CORRECT by assessing if the method
     * can reliably produce legitimately random numbers consistently over time*/
    @Test
    void randIntTime() {
        boolean notConsistentlyRandom = false;
        int acceptableRepetitions = 420;//ChatGPT claims that a given number will
        // be called about 384 times so I added 36 for margin of error and double chai
        int repetitions = 0;
        int first = Util.randInt(25, 50);

        for (int i = 0; i < 10000; i++) {
            if (Util.randInt(25, 50) == first) {
                repetitions++;
                if (repetitions >= acceptableRepetitions) {
                    notConsistentlyRandom = true;
                    break;
                }

            }
        }
        System.out.println("repetitions: " + repetitions);
        assertFalse(notConsistentlyRandom, "Random values did not vary over repeated calls");
    }
    /**Tests the B in BICEP and R (Range) of CORRECT by testing the upper boundary of the {@link edu.touro.mco152.bm.Util#randInt(int, int)} method*/
    @ParameterizedTest
    @CsvSource({
            "25, 50",
    })
    void randIntUpperBoundary(int min, int max) {
        int randomNumber = Util.randInt(min, max);
        assertTrue(randomNumber <= max);
    }
    /**Tests the B in BICEP and R (Range) of CORRECT by testing the lower boundary of the {@link edu.touro.mco152.bm.Util#randInt(int, int)} method*/
    @Test
    void randIntLowerBoundary() {
        int min = 50;
        int max = 100;
        int randomNumber = Util.randInt(min - 50, max - 51);
        assertTrue(randomNumber >= min);
    }
    /**Implicit error call to test E of BICEP by giving an illegal argument, i.e. where the
     * min is greater than the max*/
    @Test
    void randIntError() {
        int min = 51;
        int max = 50;
        assertThrows(IllegalArgumentException.class, () -> Util.randInt(min, max));
    }

    /**
    * Tests for the C in BICEP by establishing if displayString works as well as the
    * DecimalFormat method which is supposed to accomplish the same thing*/
    @Test
    void displayString() {
        double displayInt = 100;
        assertEquals(Util.displayString(displayInt), DecimalFormat.getIntegerInstance().format(displayInt));
    }
}
