package generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RandomGenTest {

    private RandomGen randomGen;

    private int[] randomNumbers;

    private float[] probabilities;

    @BeforeEach
    public void setUp() {
        randomNumbers = new int[]{1, 2, 3};
        probabilities = new float[]{0.2f, 0.5f, 0.3f};
        randomGen = new RandomGen(randomNumbers, probabilities);
    }

    @Test
    public void textNextGeneratedNumber() {
        int generatedNumber = randomGen.nextNum();
        assertTrue(generatedNumber == 1 || generatedNumber == 2 || generatedNumber == 3);
    }

    @Test
    public void testWithMismatchedLengths() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGen(new int[]{1}, new float[]{0.9f, 0.1f}));
    }

    @Test
    public void testProbabilities() {
        int iterations = 100000;
        Map<Integer, Integer> integerAndCountMap = new HashMap<>();
        for (int i = 0; i < iterations; i++) {
            int randomlyGeneratedNumber = randomGen.nextNum();
            if (integerAndCountMap.get(randomlyGeneratedNumber) == null) {
                integerAndCountMap.put(randomlyGeneratedNumber, 0);
            } else {
                integerAndCountMap.put(randomlyGeneratedNumber, integerAndCountMap.get(randomlyGeneratedNumber) + 1);
            }
        }

        for (int i = 0; i < probabilities.length; i++) {
            double testValue = iterations * probabilities[i];
            double actualValue = integerAndCountMap.get(randomNumbers[i]);
            double tolerance = 0.01 * testValue;

            double lowerBound = testValue - tolerance;
            double upperBound = testValue + tolerance;

            assertTrue(actualValue >= lowerBound && actualValue <= upperBound);
        }
    }
}
