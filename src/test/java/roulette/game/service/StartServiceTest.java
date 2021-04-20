package roulette.game.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import roulette.game.constant.OperationStatus;
import roulette.game.constant.RouletteStatus;
import roulette.game.dto.GenericWrapper;
import roulette.game.error.ErrorBusinessException;
import roulette.game.model.Roulette;
@ExtendWith(MockitoExtension.class)
public class StartServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartServiceTest.class);
    @Mock
    private ReactiveValueOperations<String, Roulette> rouletteValueOpsMock;
    @InjectMocks
    private StartService startService;
    @Test
    public void createNewRoulette() {
        Mono<Boolean> okResponseRedis = Mono.just(true);
        Mockito.when(rouletteValueOpsMock.set(Mockito.anyString(), Mockito.any(Roulette.class))).thenReturn(okResponseRedis);
        Mono<GenericWrapper<String>> resultNewRoulette = this.startService.createNewRoulette();
        StepVerifier.create(resultNewRoulette).consumeNextWith(rouletteId -> {
            LOGGER.info("Test ::: StartServiceTest.createNewRoulette ::: rouletteId = {}", rouletteId.getValue());
            Assertions.assertNotNull(rouletteId);
            Assertions.assertNotNull(rouletteId.getValue());
        }).verifyComplete();
    }
    @Test
    public void errorCreateNewRoulette() {
        Mono<Boolean> okResponseRedis = Mono.just(false);
        Mockito.when(rouletteValueOpsMock.set(Mockito.anyString(), Mockito.any(Roulette.class))).thenReturn(okResponseRedis);
        Mono<GenericWrapper<String>> resultNewRoulette = this.startService.createNewRoulette();
        StepVerifier.create(resultNewRoulette).expectError(ErrorBusinessException.class);
    }
    @Test
    public void openNewRoulette() {
        String rouletteId = initializeOpenRoulette(true, RouletteStatus.NEW);
        Mono<GenericWrapper<OperationStatus>> rouletteOpenStatus = this.startService.openRouletteById(rouletteId);
        StepVerifier.create(rouletteOpenStatus).consumeNextWith(openStatus -> {
            LOGGER.info("Test ::: StartServiceTest.openNewRoulette ::: Roulette return status = {}", openStatus.getValue());
            Assertions.assertNotNull(openStatus);
            Assertions.assertNotNull(openStatus.getValue());
            Assertions.assertEquals(OperationStatus.APPROVED, openStatus.getValue());
        }).verifyComplete();
    }
    private String initializeOpenRoulette(Boolean okRedisExpected, RouletteStatus rouletteStatus) {
        String rouletteId = "21345331584";
        Roulette roulette = new Roulette();
        roulette.setRouletteId(rouletteId);
        roulette.setRouletteStatus(rouletteStatus);
        Mono<Roulette> rouletteToOpen = Mono.just(roulette);
        Mono<Boolean> okResponseRedis = Mono.just(okRedisExpected);
        Mockito.when(rouletteValueOpsMock.get(rouletteId)).thenReturn(rouletteToOpen);
        if (okRedisExpected) {
            Mockito.when(rouletteValueOpsMock.set(Mockito.anyString(), Mockito.any(Roulette.class))).thenReturn(okResponseRedis);
            Mockito.when(rouletteValueOpsMock.set(rouletteId, roulette)).thenReturn(okResponseRedis);
        }
        return rouletteId;
    }
    @Test
    public void errorOpenNewRoulette() {
        String rouletteId = initializeOpenRoulette(false, RouletteStatus.CLOSE);
        Mono<GenericWrapper<OperationStatus>> resultNewRoulette = this.startService.openRouletteById(rouletteId);
        StepVerifier.create(resultNewRoulette).expectError(ErrorBusinessException.class);
    }
}
