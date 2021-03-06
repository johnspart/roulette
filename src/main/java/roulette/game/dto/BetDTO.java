package roulette.game.dto;
import roulette.game.constant.Color;

import java.math.BigDecimal;
public class BetDTO {
    private String rouletteId;
    private String userId;
    private Integer number;
    private Color color;
    private BigDecimal betValue;
    public String getRouletteId() {
        return rouletteId;
    }
    public void setRouletteId(String rouletteId) {
        this.rouletteId = rouletteId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public BigDecimal getBetValue() {
        return betValue;
    }
    public void setBetValue(BigDecimal betValue) {
        this.betValue = betValue;
    }
}
