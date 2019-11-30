package domain;

/**
 * Class that represents an answer within the game.
 */

public class Answer {
    /**
     * Text of the given answer
     */
    private String answerText;
    /**
     * How long it took the player to enter the answer
     */
    private long moveTime;
    /**
     * The category for which the answer was entered
     */
    private Category category;
    /**
     * Whether the answer has been found correct or incorrect
     * default: false
     */
    private boolean correct;
    /**
     * Initial character for this answer
     */
    private char initialChar;

    /**
     * Constructor of Answer class. correct value is not set right away.
     * @param answer Answer text
     * @param category Type of Category
     * @param initialChar starting character
     * @param mt moveTime
     */
    public Answer(String answer, Category category, char initialChar, long mt) {
        //lower casing entire string, capitalizing first letter
        this.answerText = String.valueOf(answer.charAt(0)).toUpperCase() + answer.toLowerCase().substring(1, answer.length());
        this.moveTime = mt;
        this.category = category;
        this.correct = false;
        this.initialChar = initialChar;
    }

    /**
     * Second Constructor of Answer class. correct value is set right away.
     * @param answer Answer text
     * @param category Type of Category
     * @param initialChar starting character
     * @param mt moveTime
     */
    public Answer(String answer, Category category, char initialChar, long mt, boolean correct) {
        //lower casing entire string, capitalizing first letter
        this.answerText = String.valueOf(answer.charAt(0)).toUpperCase() + answer.toLowerCase().substring(1, answer.length());
        this.moveTime = mt;
        this.category = category;
        this.correct = false;
        this.initialChar = initialChar;
        this.correct = correct;
    }

    /**
     * @return boolean whether answer is correct
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     *
     * @param correct set whether answer is correct
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    /**
     * returns answer's category
     * @return Category
     */
    public Category getCategory() {
        return category;
    }

    /**
     *
     * @param category Set the answer's category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Get the answer's starting char
     * @return char
     */
    public char getInitialChar() {
        return initialChar;
    }

    /**
     *
     * @param initialChar set the answer's starting char
     */
    public void setInitialChar(char initialChar) {
        this.initialChar = initialChar;
    }

    /**
     *  return the answer's text
     * @return String
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     *
     * @param answerText set the answer's text
      */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /**
     * Get the answer's moveTime
     * @return long
     */
    public long getMoveTime() {
        return moveTime;
    }

    /**
     *
     * @param moveTime Set the answer's movetime
     */
    public void setMoveTime(long moveTime) {
        this.moveTime = moveTime;
    }

    @Override
    public String toString() {
        String answerText = this.answerText + " in " + this.moveTime / 1000.0 + "s:   ";
        answerText += this.correct ? "correct" : "incorrect";
        return answerText;
    }
}
