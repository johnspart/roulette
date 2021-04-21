package roulette.game.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import roulette.game.constant.OperationStatus;
import roulette.game.dto.BetDTO;
import roulette.game.dto.GenericWrapper;
import roulette.game.service.BetService;
@RequestMapping("/betApi")
@RestController
public class BetAPI {
    private final BetService betService;
    @Autowired
    public BetAPI(BetService betService) {
        this.betService = betService;
    }
    @PostMapping(value = "/makeABet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GenericWrapper<OperationStatus>> makeABet(BetDTO bet) {
        return this.betService.makeBet(bet);
    }
}
