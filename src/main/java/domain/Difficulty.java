package domain;

/**
 * Enum representing the three available difficulties
 */

public enum Difficulty {
    EASY(25), MEDIUM(12), HARD(5);

    /**
     * Value representing the upper bound for a move time (25s, 12s or 5s) depending on the difficulty.
     */
    private int val;

    Difficulty(int val) {
        this.val = val;
    }


    /**
     * Returns the upper bound for the move time depending on the chosen difficulty.
     * @return int
     */
    public int getMoveTimeUpperBound() {
        switch (this) {
            case EASY:
                return 25;
            case MEDIUM:
                return 12;
        }
        //case HARD
        return 5;
    }

    /**
     * Returns Difficulty Enum from String input. Used for initial setup when reading the user's difficulty preference from the command line.
     * @param s
     * @return Difficulty
     */
    public static Difficulty getDifficulty(String s) {
        switch (s) {
            case "EASY":
                return EASY;
            case "MEDIUM":
                return MEDIUM;
        }
        return HARD;
    }
}
