package roulette.game.api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import roulette.game.constant.OperationStatus;
import roulette.game.dto.GenericWrapper;
import roulette.game.service.StartService;
@RestController
@RequestMapping("/start")
public class StartAPI {
    private final StartService startService;
    public StartAPI(StartService startService) {
        this.startService = startService;
    }
    @GetMapping(value = "/newRoulette", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GenericWrapper<String>> newRoulette() {
        return startService.createNewRoulette();
    }
    @GetMapping(value = "/openRoulette", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GenericWrapper<OperationStatus>> openRoulette(String rouletteId) {
        return startService.openRouletteById(rouletteId);
    }
}
