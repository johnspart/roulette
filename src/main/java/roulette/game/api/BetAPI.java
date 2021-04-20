package roulette.game.api;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import roulette.game.dto.BetDTO;
public class BetAPI {
    public Mono<String> makeABet(BetDTO bet) {
        return null;
    }
    public Flux<String> closeBet(String rouletteId) {
        return null;
    }
}
