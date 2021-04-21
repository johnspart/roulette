package roulette.game.dto;
import java.math.BigDecimal;
public class BetResultWithUserDTO {
    private String userId;
    private BigDecimal returnValue;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public BigDecimal getReturnValue() {
        return returnValue;
    }
    public void setReturnValue(BigDecimal returnValue) {
        this.returnValue = returnValue;
    }
}
