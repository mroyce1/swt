package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Subclass of abstract class Player representing a human player
 */
public class Human extends Player {

    /**
     * Constructor of Human class
     * @param name player's name
     * @param points player's points
     */
    public Human(String name, int points) {
        super(name, points, PlayerType.HUMAN);
    }


    /**
     * Returns a list of answers for each category and starting with the respective character.
     * @param categories List of categories
     * @param initialChar starting char
     * @return List<Answer>
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

    /**
     * Returns a single answer for each category and starting with the respective character.
     * @param category Single category
     * @param initialChar starting char
     * @return Answer
     */
    @Override
    public Answer getAnswer(Category category, char initialChar) {
        Answer answer = this.readAnswer(category, initialChar);
        System.out.println("answer is " + answer);
        return answer;
    }

    /**
     * Reads answer from command line.
     * @param category Specified Category
     * @param initialChar starting char
     * @return Answer
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
        return new Answer(a, category, initialChar, duration);
    }
}
