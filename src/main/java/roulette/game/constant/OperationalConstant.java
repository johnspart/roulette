package roulette.game.constant;
import java.math.BigDecimal;
public final class OperationalConstant {
    private OperationalConstant() {
        super();
    }
    public static final String REDIS_HOST_VAR_NAME = "REDIS_HOST";
    public static final String REDIS_PORT_VAR_NAME = "REDIS_PORT";
    public static final String DEFAULT_ERROR_MESSAGE = "Error tecnico, comuniquese con el area administrativa";
    public static final String USER_ID = "userId";
    public static final BigDecimal MAX_VALUE_IN_BET = new BigDecimal(10000);
    public static final BigDecimal MIN_VALUE_IN_BET = new BigDecimal("0.1");
    public static final BigDecimal WINNING_MULTIPLY_BY_NUMBER = new BigDecimal(5);
    public static final BigDecimal WINNING_MULTIPLY_BY_COLOR = new BigDecimal("1.8");
}
