package roulette.game.dto;
import roulette.game.constant.RouletteStatus;
public class RouletteStatusDTO {
    private String rouletteId;
    private RouletteStatus status;
    public String getRouletteId() {
        return rouletteId;
    }
    public void setRouletteId(String rouletteId) {
        this.rouletteId = rouletteId;
    }
    public RouletteStatus getStatus() {
        return status;
    }
    public void setStatus(RouletteStatus status) {
        this.status = status;
    }
}
