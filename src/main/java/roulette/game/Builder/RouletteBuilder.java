package roulette.game.Builder;
import roulette.game.constant.RouletteStatus;
import roulette.game.model.Roulette;

import java.util.UUID;
public final class RouletteBuilder {
    private RouletteBuilder() {
        super();
    }
    public static Roulette newRoulette() {
        Roulette roulette = new Roulette();
        roulette.setRouletteId(UUID.randomUUID().toString());
        roulette.setRouletteStatus(RouletteStatus.NEW);
        return roulette;
    }
}
