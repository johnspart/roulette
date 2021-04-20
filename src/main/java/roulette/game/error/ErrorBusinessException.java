package roulette.game.error;
import roulette.game.constant.ErrorCodes;
public class ErrorBusinessException extends RuntimeException {
    private final ErrorCodes errorCode;
    public ErrorBusinessException(ErrorCodes errorCode) {
        super();
        this.errorCode = errorCode;
    }
    public ErrorCodes getErrorCode() {
        return errorCode;
    }
}
