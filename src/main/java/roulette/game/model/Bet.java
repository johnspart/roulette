package roulette.game.model;
import roulette.game.constant.Color;

import java.io.Serializable;
import java.math.BigDecimal;
public class Bet implements Serializable {
    private String user;
    private Integer number;
    private Color color;
    private BigDecimal betValue;
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
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
