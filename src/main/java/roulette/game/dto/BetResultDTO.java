package roulette.game.dto;
import roulette.game.constant.Color;

import java.util.List;
public class BetResultDTO {
    private Color winningColor;
    private Integer winningNumber;
    private List<BetResultWithUserDTO> betResultWithUser;
    public Color getWinningColor() {
        return winningColor;
    }
    public void setWinningColor(Color winningColor) {
        this.winningColor = winningColor;
    }
    public Integer getWinningNumber() {
        return winningNumber;
    }
    public void setWinningNumber(Integer winningNumber) {
        this.winningNumber = winningNumber;
    }
    public List<BetResultWithUserDTO> getBetResultWithUser() {
        return betResultWithUser;
    }
    public void setBetResultWithUser(List<BetResultWithUserDTO> betResultWithUser) {
        this.betResultWithUser = betResultWithUser;
    }
}
