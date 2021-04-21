package roulette.game.api;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import roulette.game.dto.GenericWrapper;
import roulette.game.service.StartService;
@ExtendWith(MockitoExtension.class)
public class StartAPITest {
    @Mock
    private StartService startService;
    @InjectMocks
    private StartAPI startAPI;
    @Test
    public void initValidRoulette() {
        GenericWrapper<String> spectedId = new GenericWrapper<>("XXX");
        Mockito.when(this.startService.createNewRoulette()).thenReturn(Mono.just(spectedId));
        Mono<GenericWrapper<String>> response = startAPI.newRoulette();
        StepVerifier.create(response).expectNext(spectedId).verifyComplete();
    }
}
