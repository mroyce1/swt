package domain;

import java.util.List;

public class Game {
    private Player player1;
    private Player player2;
    private int maxRounds;
    private Difficulty difficulty;
    private List<String> categories;

    public Game(Player player1, Player player2, int maxRounds, Difficulty difficulty, List<String> categories) {
        this.player1 = player1;
        this.player2 = player2;
        this.maxRounds = maxRounds;
        this.difficulty = difficulty;
        this.categories = categories;
    }
}
