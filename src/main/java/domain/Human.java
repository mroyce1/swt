package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Human extends Player {
    public Human(String name, int points) {
        super(name, points, PlayerType.HUMAN);
    }



    /*
    Returns a list of answers for each category and starting with the respective character.
     */
    @Override
    public List<Answer> getListOfAnswers(List<Category> categories, char initialChar) {
        System.out.println("You can forgo an answer by simply pressing return.");
        List<Answer> answers = new ArrayList<Answer>();
        for (Category category : categories){
            answers.add(readAnswer(category, initialChar));
        }
        return answers;
    }

        /*
    Returns a single answer for each category and starting with the respective character.
     */
    @Override
    public Answer getAnswer(Category category, char initialChar) {
        Answer answer = this.readAnswer(category, initialChar);
        System.out.println("answer is " + answer);
        return answer;
    }


    /*
    Read answer from command line.
     */
    private Answer readAnswer(Category category, char initialChar){
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        String a = null;
        long start = System.currentTimeMillis();
        try{
            while(a == null) {
                System.out.println(String.format("Please enter a %s beginning with %c: ", category, initialChar));
                a = bufR.readLine();
            }
        }
        catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        long duration = System.currentTimeMillis() - start;
        return new Answer(a, duration);
    }
}
