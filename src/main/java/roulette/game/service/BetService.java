package roulette.game.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import roulette.game.constant.*;
import roulette.game.dto.BetDTO;
import roulette.game.dto.GenericWrapper;
import roulette.game.error.ErrorBusinessException;
import roulette.game.model.Bet;
import roulette.game.model.Roulette;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class BetService {
    private final ReactiveValueOperations<String, Roulette> rouletteValueOps;
    @Autowired
    public BetService(ReactiveValueOperations<String, Roulette> rouletteValueOps) {
        this.rouletteValueOps = rouletteValueOps;
    }
    public Mono<GenericWrapper<OperationStatus>> makeBet(BetDTO bet) {
        return Mono.deferContextual(ctx -> {
            bet.setUserId(ctx.get(OperationalConstant.USER_ID));
            preValidBet(bet);
            return validOpenRoulette(bet.getRouletteId()).map(roulette -> {
                List<Bet> bets = roulette.getBets();
                Bet newBet = new Bet();
                newBet.setUser(bet.getUserId());
                newBet.setColor(bet.getColor());
                newBet.setNumber(bet.getNumber());
                newBet.setBetValue(bet.getBetValue());
                bets.add(newBet);
                return new GenericWrapper<>(OperationStatus.APPROVED);
            });
        });
    }
    private void preValidBet(BetDTO bet) {
        Color color = bet.getColor();
        Integer number = bet.getNumber();
        BigDecimal betValue = bet.getBetValue();
        if (number < 0 || number > 36) {
            throw new ErrorBusinessException(ErrorCodes.THE_BET_NUMBER_NOT_IS_VALID);
        }
        int colorValid = number % 2;
        if (Color.RED.equals(color) && colorValid != 0) {
            throw new ErrorBusinessException(ErrorCodes.THE_BET_NUMBER_NOT_IS_VALID);
        } else if (Color.BLACK.equals(color) && colorValid > 0) {
            throw new ErrorBusinessException(ErrorCodes.THE_BET_NUMBER_NOT_IS_VALID);
        }
        if (OperationalConstant.MIN_VALUE_IN_BET.compareTo(betValue) > 0 || OperationalConstant.MAX_VALUE_IN_BET.compareTo(betValue) < 0) {
            throw new ErrorBusinessException(ErrorCodes.THE_BET_VALUE_NOT_IS_VALID);
        }
    }
    private Mono<Roulette> validOpenRoulette(String rouletteId) {
        return rouletteValueOps.get(rouletteId).map(Optional::ofNullable).map(roulette ->
                roulette.filter(rouletteFilter ->
                        RouletteStatus.OPEN.equals(rouletteFilter.getRouletteStatus())).orElseThrow(() ->
                        new ErrorBusinessException(ErrorCodes.NOT_EXIST_ROULETTE_VALID_FOR_THE_BET)));
    }
}
