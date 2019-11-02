package domain;

/*
Class that represents an answer within the game.
 */

public class Answer {
    private String answerText;
    private long moveTime;
    private Category category;


    public Answer(String a, Category c, long mt) {
        this.answerText = a;
        this.moveTime = mt;
        this.category = c;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        return this.answerText + " in " + this.moveTime / 1000.0 + "s";
    }
}
