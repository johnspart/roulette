package roulette.game.api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import roulette.game.dto.BetResultDTO;
import roulette.game.dto.GenericWrapper;
import roulette.game.dto.RouletteStatusDTO;
import roulette.game.model.Roulette;
import roulette.game.service.RouletteService;
@RestController
@RequestMapping("/rouletteAPI")
public class RouletteAPI {
    private final RouletteService rouletteService;
    public RouletteAPI(RouletteService rouletteService) {
        this.rouletteService = rouletteService;
    }
    @PostMapping(value = "/closeBetsInRoulette/{rouletteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BetResultDTO> closeBetsInRoulette(@PathVariable("rouletteId") String rouletteId) {
        return rouletteService.closeBetsInRoulette(rouletteId);
    }
    @GetMapping(value = "/getAllWithStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Roulette> getAllWithStatus() {
        return this.rouletteService.getAllRoulette();
    }
}
