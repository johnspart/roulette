package roulette.game.model;
import roulette.game.constant.RouletteStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Roulette implements Serializable {
    private String rouletteId;
    private RouletteStatus rouletteStatus;
    private List<Bet> bets;
    public Roulette() {
        this.bets = new ArrayList<>();
    }
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
    public List<Bet> getBets() {
        return bets;
    }
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
