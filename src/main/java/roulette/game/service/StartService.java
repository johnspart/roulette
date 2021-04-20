package roulette.game.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import roulette.game.Builder.RouletteBuilder;
import roulette.game.constant.ErrorCodes;
import roulette.game.constant.OperationStatus;
import roulette.game.constant.RouletteStatus;
import roulette.game.dto.GenericWrapper;
import roulette.game.error.ErrorBusinessException;
import roulette.game.model.Roulette;
@Service
public class StartService {
    private final ReactiveValueOperations<String, Roulette> rouletteValueOps;
    @Autowired
    public StartService(ReactiveValueOperations<String, Roulette> rouletteValueOps) {
        this.rouletteValueOps = rouletteValueOps;
    }
    public Mono<GenericWrapper<String>> createNewRoulette() {
        Roulette roulette = RouletteBuilder.newRoulette();
        return rouletteValueOps.set(roulette.getRouletteId(), roulette).map(ok -> setRoulette(roulette, ok, ErrorCodes.CREATE_NEW_ROULETTE));
    }
    private GenericWrapper<String> setRoulette(Roulette roulette, Boolean ok, ErrorCodes errorCode) {
        if (!ok) {
            throw new ErrorBusinessException(errorCode);
        }
        return new GenericWrapper<>(roulette.getRouletteId());
    }
    public Mono<GenericWrapper<OperationStatus>> openRouletteById(String rouletteId) {
        return rouletteValueOps.get(rouletteId).map(this::openRoulette);
    }
    private GenericWrapper<OperationStatus> openRoulette(Roulette roulette) {
        OperationStatus operationStatus;
        if (RouletteStatus.NEW.equals(roulette.getRouletteStatus())) {
            roulette.setRouletteStatus(RouletteStatus.OPEN);
            rouletteValueOps.set(roulette.getRouletteId(), roulette).map(ok -> setRoulette(roulette, ok, ErrorCodes.OPEN_ROULETTE));
            operationStatus = OperationStatus.APPROVED;
        } else {
            operationStatus = OperationStatus.DENIED;
        }
        return new GenericWrapper<>(operationStatus);
    }
}
