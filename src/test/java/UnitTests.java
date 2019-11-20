import static org.junit.Assert.*;

import domain.Answer;
import domain.Category;
import org.junit.Test;
import application.SparqlController;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitTests {

    @Test
    public void testQueryCity() {
        List<String> result = SparqlController.queryDBPedia(Category.CITY, "B");
        result.forEach(System.out::println);
    }

    @Test
    public void testQuerySoccerPlayer() {
        List<String> result = SparqlController.queryDBPedia(Category.SOCCERPLAYER, "B");
        result.forEach(System.out::println);
    }

    @Test
    public void testQueryScientist() {
        List<String> result = SparqlController.queryDBPedia(Category.SCIENTIST, "B");
        result.forEach(System.out::println);
    }

    @Test
    public void testQueryDrug() {
        List<String> result = SparqlController.queryDBPedia(Category.DRUG, "A");
        result.forEach(System.out::println);
    }

    @Test
    public void testQuerySoftware() {
        List<String> result = SparqlController.queryDBPedia(Category.SOFTWARE, "A");
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
            citeSizes.add(SparqlController.queryDBPedia(Category.CITY, String.valueOf(currentChar)).size());
            end = Instant.now();
            citeDurations.add(Duration.between(start, end));

            start = Instant.now();
            soccersSizes.add(SparqlController.queryDBPedia(Category.SOCCERPLAYER, String.valueOf(currentChar)).size());
            end = Instant.now();
            soccersDurations.add(Duration.between(start, end));

            start = Instant.now();
            scientistsSizes.add(SparqlController.queryDBPedia(Category.SCIENTIST, String.valueOf(currentChar)).size());
            end = Instant.now();
            scientistsDurations.add(Duration.between(start, end));

            start = Instant.now();
            drugsSizes.add(SparqlController.queryDBPedia(Category.DRUG, String.valueOf(currentChar)).size());
            end = Instant.now();
            drugsDurations.add(Duration.between(start, end));

            start = Instant.now();
            softwareSizes.add(SparqlController.queryDBPedia(Category.SOFTWARE, String.valueOf(currentChar)).size());
            end = Instant.now();
            softwareDurationss.add(Duration.between(start, end));

            start = Instant.now();
            riverSizes.add(SparqlController.queryDBPedia(Category.RIVER, String.valueOf(currentChar)).size());
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

    @Test
    public void AccuracyTest() {

        ArrayList<String> cityAnswers = new ArrayList<>();
        ArrayList<String> soccersAnswers = new ArrayList<>();
        ArrayList<String> scientistsAnswers = new ArrayList<>();
        ArrayList<String> drugsAnswers = new ArrayList<>();
        ArrayList<String> softwareAnswers = new ArrayList<>();
        ArrayList<String> riverAnswers = new ArrayList<>();
        ArrayList<String> countryAnswers = new ArrayList<>();


        cityAnswers.add("London");
        cityAnswers.add("Berlin");
        cityAnswers.add("Frankfurt");
        cityAnswers.add("Zurich");
        cityAnswers.add("Madrid");
        cityAnswers.add("Paris");
        cityAnswers.add("New York");
        cityAnswers.add("Munich");
        cityAnswers.add("Rome");
        cityAnswers.add("Mannheim");

        soccersAnswers.add("Kahn");
        soccersAnswers.add("Ribery");
        soccersAnswers.add("Ronaldo");
        soccersAnswers.add("Hummels");
        soccersAnswers.add("Van der Saar");
        soccersAnswers.add("Neuer");
        soccersAnswers.add("Ibrahimovic");
        soccersAnswers.add("Messi");
        soccersAnswers.add("Müller");
        soccersAnswers.add("Maradona");

        scientistsAnswers.add("Berners-Lee");
        scientistsAnswers.add("Bohr");
        scientistsAnswers.add("Freud");
        scientistsAnswers.add("Einstein");
        scientistsAnswers.add("Darwin");
        scientistsAnswers.add("Hawkins");
        scientistsAnswers.add("Spearman");
        scientistsAnswers.add("Thompson");
        scientistsAnswers.add("Gauß");
        scientistsAnswers.add("Euler");

        drugsAnswers.add("Aspirin");
        drugsAnswers.add("Ibuprofen");
        drugsAnswers.add("Adderal");
        drugsAnswers.add("Xanax");
        drugsAnswers.add("Silomat");
        drugsAnswers.add("Subutex");
        drugsAnswers.add("Buscopan");
        drugsAnswers.add("Prozac");
        drugsAnswers.add("Voltaren");
        drugsAnswers.add("Diazepam");


        softwareAnswers.add("Excel");
        softwareAnswers.add("Microsoft Excel");
        softwareAnswers.add("Kafka");
        softwareAnswers.add("Apache Kafka");
        softwareAnswers.add("Rapidminer" );
        softwareAnswers.add("IBM SPSS" );
        softwareAnswers.add("MySQL" );
        softwareAnswers.add("Teradata");
        softwareAnswers.add("Jena" );
        softwareAnswers.add("HRworks" );

        riverAnswers.add("Rhine");
        riverAnswers.add("Main");
        riverAnswers.add("Niger");
        riverAnswers.add("Amazonas");
        riverAnswers.add("Isar");
        riverAnswers.add("Lahn");
        riverAnswers.add("Neckar");
        riverAnswers.add("Seine");
        riverAnswers.add("Thames");
        riverAnswers.add("Nidda");


        countryAnswers.add("Brazil");
        countryAnswers.add("Germany");
        countryAnswers.add("USA");
        countryAnswers.add("France");
        countryAnswers.add("China");
        countryAnswers.add("Poland");
        countryAnswers.add("Congo");
        countryAnswers.add("Hungary");
        countryAnswers.add("Italy");
        countryAnswers.add("Spain");


        HashMap<String, Double> resultsCity = calculateAccuracy(cityAnswers, Category.CITY);
        HashMap<String, Double> resultsSoccers = calculateAccuracy(soccersAnswers, Category.SOCCERPLAYER);
        HashMap<String, Double> resultsScientists = calculateAccuracy(scientistsAnswers, Category.SCIENTIST);
        HashMap<String, Double> resultsDrugs = calculateAccuracy(drugsAnswers, Category.DRUG);
        HashMap<String, Double> resultsSoftware = calculateAccuracy(softwareAnswers, Category.SOFTWARE);
        HashMap<String, Double> resultsRivers = calculateAccuracy(riverAnswers, Category.RIVER);
        //HashMap<String, Double> resultsCountries = calculateAccuracy(countryAnswers, Category.COUNTRY);

        System.out.println("results city");
        printMap(resultsCity);
        System.out.println("results soccerplayer");
        printMap(resultsSoccers);
        System.out.println("results scientist");
        printMap(resultsScientists);
        System.out.println("results drug");
        printMap(resultsDrugs);
        System.out.println("results software");
        printMap(resultsSoftware);
        System.out.println("results rivers");
        printMap(resultsRivers);
        //System.out.println("results countries");
        //printMap(resultsCountries);
    }

    private HashMap<String, Double> calculateAccuracy(ArrayList<String> stringAnswers, Category cat) {

        HashMap<String, Double> accuracyMap = new HashMap<>();

        int validateAnswerCounter = 0;
        int validateSelectCounter = 0;
        int validateCombinedCounter = 0;

        for (int i = 0; i < stringAnswers.size(); i++) {
            validateAnswerCounter = (SparqlController.validateAnswer(new Answer(stringAnswers.get(i), cat, stringAnswers.get(i).charAt(0), (long)(0.01))))
                    ? validateAnswerCounter + 1 : validateAnswerCounter;
            validateSelectCounter = (SparqlController.validateWithSelect(new Answer(stringAnswers.get(i), cat, stringAnswers.get(i).charAt(0), (long)(0.01))))
                    ? validateSelectCounter + 1 : validateSelectCounter;
            validateCombinedCounter = SparqlController.validateMultiple(new Answer(stringAnswers.get(i), cat, stringAnswers.get(i).charAt(0), (long)(0.01)))
                    ? validateCombinedCounter + 1 : validateCombinedCounter;
        }
        accuracyMap.put("ask_query_approach", ((double)validateAnswerCounter / 10.0));
        accuracyMap.put("select_query_approach", ((double)validateSelectCounter / 10.0));
        accuracyMap.put("combined_approach", ((double)validateCombinedCounter / 10.0));

        return accuracyMap;
    }

    private void printMap(HashMap<String, Double> map){
        map.entrySet().forEach(entry->{
            System.out.println("Evaluation Approach: " + entry.getKey() + " Accuracy: " + entry.getValue());
        });
    }

    }
