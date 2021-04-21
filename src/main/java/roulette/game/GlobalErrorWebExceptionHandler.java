package roulette.game;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import roulette.game.constant.OperationalConstant;
import roulette.game.dto.GenericWrapper;
import roulette.game.error.ErrorBusinessException;
import roulette.game.error.ErrorMessagesBusiness;
@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources,
                                          ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageWriters(configurer.getWriters());
    }
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
                RequestPredicates.all(), this::renderErrorResponse);
    }
    private Mono<ServerResponse> renderErrorResponse(
            ServerRequest request) {
        Throwable error = getError(request);
        String messageToReturn = OperationalConstant.DEFAULT_ERROR_MESSAGE;
        if (error instanceof ErrorBusinessException) {
            messageToReturn = ErrorMessagesBusiness.getMessage(((ErrorBusinessException) error).getErrorCode());
        }
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new GenericWrapper<>(messageToReturn)));
    }
}

