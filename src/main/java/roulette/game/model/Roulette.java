package roulette.game.model;
import roulette.game.constant.RouletteStatus;

import java.io.Serializable;
public class Roulette implements Serializable {
    private String rouletteId;
    private RouletteStatus rouletteStatus;
    public String getRouletteId() {
        return rouletteId;
    }
    public void setRouletteId(String rouletteId) {
        this.rouletteId = rouletteId;
    }
    public RouletteStatus getRouletteStatus() {
        return rouletteStatus;
    }
    public void setRouletteStatus(RouletteStatus rouletteStatus) {
        this.rouletteStatus = rouletteStatus;
    }
}
