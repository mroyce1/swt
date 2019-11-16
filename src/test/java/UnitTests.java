import static org.junit.Assert.*;

import domain.Answer;
import domain.Category;
import org.junit.Test;
import application.SparqlController;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UnitTests {

    @Test
    public void testQueryCity() {
        List<String> result = SparqlController.queryDBPedia(Category.CITY, 'B');
        result.forEach(System.out::println);
    }

    @Test
    public void testQuerySoccerPlayer() {
        List<String> result = SparqlController.queryDBPedia(Category.SOCCERPLAYER, 'B');
        result.forEach(System.out::println);
    }

    @Test
    public void testQueryScientist() {
        List<String> result = SparqlController.queryDBPedia(Category.SCIENTIST, 'B');
        result.forEach(System.out::println);
    }

    @Test
    public void testQueryDrug() {
        List<String> result = SparqlController.queryDBPedia(Category.DRUG, 'A');
        result.forEach(System.out::println);
    }

    @Test
    public void testQuerySoftware() {
        List<String> result = SparqlController.queryDBPedia(Category.SOFTWARE, 'A');
        result.forEach(System.out::println);
    }

    @Test
    public void testValidateAnswerCity() {
        assertTrue(SparqlController.validateAnswer(new Answer("Berlin", Category.CITY, 'C', 100)));
    }

    @Test
    public void testValidateAnswerCityNegative() {
        assertFalse(SparqlController.validateAnswer(new Answer("Hechen", Category.CITY, 'H', 100)));
    }

    @Test
    public void testValidateAnswerSoccerPlayer() {
        assertTrue(SparqlController.validateAnswer(new Answer("Kahn", Category.SOCCERPLAYER, 'K', 103)));
    }

    @Test
    public void testValidateAnswerSoccerPlayerNegative() {
        assertFalse(SparqlController.validateAnswer(new Answer("Hechen", Category.SOCCERPLAYER, 'H', 97326)));
    }

    @Test
    public void testValidateAnswerScientist() {
        assertTrue(SparqlController.validateAnswer(new Answer("Einstein", Category.SCIENTIST, 'E', 83276)));
    }

    @Test
    public void testValidateAnswerScientistNegative() {
        assertFalse(SparqlController.validateAnswer(new Answer("Hechen", Category.SCIENTIST, 'H', 56)));
    }

    @Test
    public void testValidateAnswerDrug() {
        assertTrue(SparqlController.validateAnswer(new Answer("Aspirin", Category.DRUG, 'A', 456)));
    }

    @Test
    public void testValidateAnswerDrugNegative() {
        assertFalse(SparqlController.validateAnswer(new Answer("Hechen", Category.DRUG, 'H', 834331)));
    }

    @Test
    public void testValidateAnswerSoftware() {
        assertTrue(SparqlController.validateAnswer(new Answer("Apache Kafka", Category.SOCCERPLAYER, 'A', 93334)));
    }

    @Test
    public void testValidateAnswerSoftwareNegative() {
        assertFalse(SparqlController.validateAnswer(new Answer("Apache Hechen", Category.SOFTWARE, 'A', 482)));
    }

    @Test
    public void PerformanceTest() {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        ArrayList<Integer> citeSizes = new ArrayList<>();
        ArrayList<Integer> soccersSizes = new ArrayList<>();
        ArrayList<Integer> scientistsSizes = new ArrayList<>();
        ArrayList<Integer> drugsSizes = new ArrayList<>();
        ArrayList<Integer> softwareSizes = new ArrayList<>();
        ArrayList<Integer> riverSizes = new ArrayList<>();

        ArrayList<Duration> citeDurations = new ArrayList<>();
        ArrayList<Duration> soccersDurations = new ArrayList<>();
        ArrayList<Duration> scientistsDurations = new ArrayList<>();
        ArrayList<Duration> drugsDurations = new ArrayList<>();
        ArrayList<Duration> softwareDurationss = new ArrayList<>();
        ArrayList<Duration> riverDurations = new ArrayList<>();

        Instant start = Instant.now();
        Instant end = Instant.now();

        for (char currentChar : alphabet) {
            System.out.println("current Char: " + currentChar);
            start = Instant.now();
            citeSizes.add(SparqlController.queryDBPedia(Category.CITY, currentChar).size());
            end = Instant.now();
            citeDurations.add(Duration.between(start, end));

            start = Instant.now();
            soccersSizes.add(SparqlController.queryDBPedia(Category.SOCCERPLAYER, currentChar).size());
            end = Instant.now();
            soccersDurations.add(Duration.between(start, end));

            start = Instant.now();
            scientistsSizes.add(SparqlController.queryDBPedia(Category.SCIENTIST, currentChar).size());
            end = Instant.now();
            scientistsDurations.add(Duration.between(start, end));

            start = Instant.now();
            drugsSizes.add(SparqlController.queryDBPedia(Category.DRUG, currentChar).size());
            end = Instant.now();
            drugsDurations.add(Duration.between(start, end));

            start = Instant.now();
            softwareSizes.add(SparqlController.queryDBPedia(Category.SOFTWARE, currentChar).size());
            end = Instant.now();
            softwareDurationss.add(Duration.between(start, end));

            start = Instant.now();
            riverSizes.add(SparqlController.queryDBPedia(Category.RIVER, currentChar).size());
            end = Instant.now();
            riverDurations.add(Duration.between(start, end));

        }

        System.out.println("Summary City-Queries");
        System.out.println(citeSizes.stream().mapToInt(Integer::intValue).average());
        System.out.println(citeSizes.stream().mapToInt(Integer::intValue).max());
        System.out.println(citeSizes.stream().mapToInt(Integer::intValue).min());
        System.out.println(citeDurations.stream().mapToDouble(Duration::toMillis).average());
        System.out.println(citeDurations.stream().mapToDouble(Duration::toMillis).max());
        System.out.println(citeDurations.stream().mapToDouble(Duration::toMillis).min());

        System.out.println("Summary SoccerPlayer-Queries");
        System.out.println(soccersSizes.stream().mapToInt(Integer::intValue).average());
        System.out.println(soccersSizes.stream().mapToInt(Integer::intValue).max());
        System.out.println(soccersSizes.stream().mapToInt(Integer::intValue).min());
        System.out.println(soccersDurations.stream().mapToDouble(Duration::toMillis).average());
        System.out.println(soccersDurations.stream().mapToDouble(Duration::toMillis).max());
        System.out.println(soccersDurations.stream().mapToDouble(Duration::toMillis).min());

        System.out.println("Summary Scientist-Queries");
        System.out.println(scientistsSizes.stream().mapToInt(Integer::intValue).average());
        System.out.println(scientistsSizes.stream().mapToInt(Integer::intValue).max());
        System.out.println(scientistsSizes.stream().mapToInt(Integer::intValue).min());
        System.out.println(scientistsDurations.stream().mapToDouble(Duration::toMillis).average());
        System.out.println(scientistsDurations.stream().mapToDouble(Duration::toMillis).max());
        System.out.println(scientistsDurations.stream().mapToDouble(Duration::toMillis).min());

        System.out.println("Summary drug-Queries");
        System.out.println(drugsSizes.stream().mapToInt(Integer::intValue).average());
        System.out.println(drugsSizes.stream().mapToInt(Integer::intValue).max());
        System.out.println(drugsSizes.stream().mapToInt(Integer::intValue).min());
        System.out.println(drugsDurations.stream().mapToDouble(Duration::toMillis).average());
        System.out.println(drugsDurations.stream().mapToDouble(Duration::toMillis).max());
        System.out.println(drugsDurations.stream().mapToDouble(Duration::toMillis).min());

        System.out.println("summary software-Queries");
        System.out.println(softwareSizes.stream().mapToInt(Integer::intValue).average());
        System.out.println(softwareSizes.stream().mapToInt(Integer::intValue).max());
        System.out.println(softwareSizes.stream().mapToInt(Integer::intValue).min());
        System.out.println(softwareDurationss.stream().mapToDouble(Duration::toMillis).average());
        System.out.println(softwareDurationss.stream().mapToDouble(Duration::toMillis).max());
        System.out.println(softwareDurationss.stream().mapToDouble(Duration::toMillis).min());

        System.out.println("summary river-Queries");
        System.out.println(riverSizes.stream().mapToInt(Integer::intValue).average());
        System.out.println(riverSizes.stream().mapToInt(Integer::intValue).max());
        System.out.println(riverSizes.stream().mapToInt(Integer::intValue).min());
        System.out.println(riverDurations.stream().mapToDouble(Duration::toMillis).average());
        System.out.println(riverDurations.stream().mapToDouble(Duration::toMillis).max());
        System.out.println(riverDurations.stream().mapToDouble(Duration::toMillis).min());
    }
}
