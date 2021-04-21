package roulette.game.util;
import java.util.Random;
public final class RandomGenerate {
    private RandomGenerate() {
        super();
    }
    public static int generateRandomNumberForRouletteGame() {
        Random rand = new Random();
        int upperbound = 37;
        return rand.nextInt(upperbound);
    }
}
