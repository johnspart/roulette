package roulette.game.security;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import roulette.game.constant.ErrorCodes;
import roulette.game.constant.OperationalConstant;
import roulette.game.error.ErrorBusinessException;

import java.util.Optional;
@Component
public class CaptureHeadersFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        String userId = Optional.ofNullable(serverWebExchange.getRequest().getHeaders().get(OperationalConstant.USER_ID))
                .map(t -> t.get(0)).orElseThrow(() -> new ErrorBusinessException(ErrorCodes.NOT_PRESENT_HEADER_USER_ID));
        return webFilterChain.filter(serverWebExchange).contextWrite(context -> {
            context = context.put(OperationalConstant.USER_ID, userId);
            return context;
        });
    }
}
