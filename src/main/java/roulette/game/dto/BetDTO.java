package roulette.game.dto;
import roulette.game.constant.Color;

import java.math.BigDecimal;
public class BetDTO {
    private String id;
    private Integer number;
    private Color color;
    private BigDecimal betValue;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
