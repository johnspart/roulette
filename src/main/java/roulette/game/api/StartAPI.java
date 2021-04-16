package roulette.game.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import roulette.game.constant.OperationStatus;
import roulette.game.dto.GenericValue;

import java.util.UUID;

@RestController
@RequestMapping("/start")
public class StartAPI {
    public Mono<GenericValue<String>> newRoulette() {
        return Mono.just("");
    }

    public Mono<GenericValue<OperationStatus>> openRoulette(String rouletteId){
        return null;
    }
}
