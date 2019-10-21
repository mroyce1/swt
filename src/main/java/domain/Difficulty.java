package domain;

public enum Difficulty {
    EASY(40), MEDIUM(30), HARD(20);

    private int val;

    Difficulty(int val){
        this.val = val;
    }

    public int getVal(){
        switch(this){
            case EASY:
                return 40;
            case MEDIUM:
                return 30;
        }
        //case HARD
        return 20;

    }
}
