package domain;

/*
Class that represents an answer within the game.
 */

public class Answer {
    private String answerText;
    private long moveTime;


    public Answer(String a, long mt){
        this.answerText = a;
        this.moveTime = mt;
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
    public String toString(){
        return this.answerText + " in " + this.moveTime/1000.0 + "s";
    }
}
