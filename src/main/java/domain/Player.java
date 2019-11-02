package domain;

import java.util.List;

public abstract class Player {
    private String name;
    private int points;
    private PlayerType playerType;

    public Player(String name, int points, PlayerType playerType) {
        this.name = name;
        this.points = points;
        this.playerType = playerType;
    }

    public void performMove() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public List<Answer> getListOfMoves(List<Category> categories, char initialChar) {
        return null;
    }

    public Answer getMove(Category category, char initialChar){
        return null;
    }

    public void incrementPoints(int pts){
        this.points += pts;
    }

    @Override
    public String toString() {
        return this.playerType + " | " + this.name;
    }
}
