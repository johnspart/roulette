package roulette.game.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import roulette.game.constant.Color;
import roulette.game.constant.ErrorCodes;
import roulette.game.constant.OperationalConstant;
import roulette.game.constant.RouletteStatus;
import roulette.game.dto.BetResultDTO;
import roulette.game.dto.BetResultWithUserDTO;
import roulette.game.error.ErrorBusinessException;
import roulette.game.model.Roulette;
import roulette.game.util.RandomGenerate;

import java.math.BigDecimal;
import java.util.stream.Collectors;
@Service
public class RouletteService {
    private final ReactiveValueOperations<String, Roulette> rouletteValueOps;
    private final ReactiveRedisTemplate<String, Roulette> rouletteReactiveRedisTemplate;
    @Autowired
    public RouletteService(ReactiveValueOperations<String, Roulette> rouletteValueOps,
                           ReactiveRedisTemplate<String, Roulette> rouletteReactiveRedisTemplate) {
        this.rouletteValueOps = rouletteValueOps;
        this.rouletteReactiveRedisTemplate = rouletteReactiveRedisTemplate;
    }
    public Mono<BetResultDTO> closeBetsInRoulette(String rouletteId) {
        return this.rouletteValueOps.get(rouletteId).map(roulette -> {
            if (!RouletteStatus.OPEN.equals(roulette.getRouletteStatus())) {
                throw new ErrorBusinessException(ErrorCodes.THE_ROULETTE_NOT_IS_OPEN);
            }
            return getAllBetResults(roulette);
        });
    }
    private BetResultDTO getAllBetResults(Roulette r) {
        BetResultDTO betResult = new BetResultDTO();
        betResult.setWinningNumber(RandomGenerate.generateRandomNumberForRouletteGame());
        betResult.setWinningColor(getWinningColorByNumber(betResult));
        betResult.setBetResultWithUser(r.getBets().stream().map(bet -> {
            BetResultWithUserDTO betResultWithUser = new BetResultWithUserDTO();
            betResultWithUser.setUserId(bet.getUser());
            betResultWithUser.setReturnValue(getValueToReturn(betResult, bet));
            return betResultWithUser;
        }).collect(Collectors.toList()));
        return betResult;
    }
    private BigDecimal getValueToReturn(BetResultDTO betResult, roulette.game.model.Bet bet) {
        BigDecimal valueToReturn = BigDecimal.ZERO;
        if (bet.getNumber().equals(betResult.getWinningNumber())) {
            valueToReturn = bet.getBetValue().multiply(OperationalConstant.WINNING_MULTIPLY_BY_NUMBER);
        } else if (bet.getColor().equals(betResult.getWinningColor())) {
            valueToReturn = bet.getBetValue().multiply(OperationalConstant.WINNING_MULTIPLY_BY_COLOR);
        }
        return valueToReturn;
    }
    private Color getWinningColorByNumber(BetResultDTO betResult) {
        return betResult.getWinningNumber() % 2 == 0 ? Color.RED : Color.BLACK;
    }
    public Flux<Roulette> getAllRoulette() {
        return this.rouletteReactiveRedisTemplate.keys("*").flatMap(rouletteValueOps::get);
    }
}
