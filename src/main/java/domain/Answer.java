package domain;

/*
Class that represents an answer within the game.
 */

public class Answer {
    private String answerText;
    private long moveTime;
    private Category category;
    private boolean correct;
    private char initialChar;

    public Answer(String a, Category c, char initialChar, long mt) {
        //lower casing entire string, capitalizing first letter
        this.answerText = String.valueOf(a.charAt(0)).toUpperCase() + a.toLowerCase().substring(1, a.length());
        this.moveTime = mt;
        this.category = c;
        this.correct = false;
        this.initialChar = initialChar;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public char getInitialChar() {
        return initialChar;
    }

    public void setInitialChar(char initialChar) {
        this.initialChar = initialChar;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public long getMoveTime() {
        return moveTime;
    }

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
