package domain;

import java.util.List;

/**
 * Abstract class representing a player (AI or Human) within the game.
 */
public abstract class Player {
    /**
     * Name of the player
     */
    private String name;
    /**
     * Current points of the player
     * default: 0
     */
    private int points;
    /**
     * PlayerType (AI or HUMAN)
     */
    private PlayerType playerType;

    /**
     * Constructor of Player class
     * @param name Player's name
     * @param points Player's points
     * @param playerType Player type
     */
    public Player(String name, int points, PlayerType playerType) {
        this.name = name;
        this.points = points;
        this.playerType = playerType;
    }

    /**
     * Get the player's name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the player's name
     * @param name Desired name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the player's points
     * @return int
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set the player's points
     * @param points desired points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Get the player's type
     * @return PlayerType
     */
    public PlayerType getPlayerType() {
        return playerType;
    }

    /**
     * Set the player's type
     * @param playerType desired type
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    /**
     * Returns a list of answers for each category and starting with the respective character.
     * @param categories List of categories
     * @param initialChar starting char
     * @return List<Answer>
     */
    public List<Answer> getListOfAnswers(List<Category> categories, char initialChar) {
        return null;
    }

    /**
     * Returns a single answer for each category and starting with the respective character.
     * @param category Single category
     * @param initialChar starting char
     * @return Answer
     */
    public Answer getAnswer(Category category, char initialChar){
        return null;
    }

    /**
     * Increment the player's points
     * @param pts number by which the player's score should be incremented
     */
    public void incrementPoints(int pts){
        this.points += pts;
    }

    @Override
    public String toString() {
        return this.playerType + " | " + this.name;
    }
}
