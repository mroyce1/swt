package domain;

public enum Difficulty {
    EASY(40), MEDIUM(30), HARD(20);

    private int val;

    Difficulty(int val) {
        this.val = val;
    }


        /*
    Returns time associated with the AI's difficulty
     */
    public int getMoveTimeUpperBound() {
        switch (this) {
            case EASY:
                return 40;
            case MEDIUM:
                return 30;
        }
        //case HARD
        return 20;
    }

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
