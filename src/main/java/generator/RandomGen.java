package generator;

import java.util.Random;

public class RandomGen {

    private final int[] randomNums;

    private final float[] probabilities;

    private final Random random;

    public RandomGen(int[] randomNums, float[] probabilities) {
        if (randomNums.length != probabilities.length) {
            throw new IllegalArgumentException("Random Numbers and Probabilities must have the same length");
        }

        this.randomNums = randomNums;
        this.probabilities = probabilities;
        this.random = new Random();
    }

    public int nextNum() {
        float cumulativeProbability = 0F;
        float currentProbability = random.nextFloat();

        for (int i = 0; i < randomNums.length; i++) {
            if (currentProbability >= cumulativeProbability
                    && currentProbability < cumulativeProbability + probabilities[i]) {
                return randomNums[i];
            } else {
                cumulativeProbability = cumulativeProbability + probabilities[i];
            }
        }

        return randomNums[randomNums.length - 1];
    }
}
