import static org.junit.Assert.*;

import domain.Category;
import org.junit.Test;
import application.SparqlController;

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
        assertTrue(SparqlController.validateAnswer(Category.CITY, "Berlin"));
    }

    @Test
    public void testValidateAnswerCityNegative() {
        assertFalse(SparqlController.validateAnswer(Category.CITY, "Hechen"));
    }

    @Test
    public void testValidateAnswerSoccerPlayer() {
        assertTrue(SparqlController.validateAnswer(Category.SOCCERPLAYER, "Kahn"));
    }

    @Test
    public void testValidateAnswerSoccerPlayerNegative() {
        assertFalse(SparqlController.validateAnswer(Category.SOCCERPLAYER, "Hechen"));
    }

    @Test
    public void testValidateAnswerScientist() {
        assertTrue(SparqlController.validateAnswer(Category.SCIENTIST, "Einstein"));
    }

    @Test
    public void testValidateAnswerScientistNegative() {
        assertFalse(SparqlController.validateAnswer(Category.SCIENTIST, "Hechen"));
    }

    @Test
    public void testValidateAnswerDrug() {
        assertTrue(SparqlController.validateAnswer(Category.DRUG, "Aspirin"));
    }

    @Test
    public void testValidateAnswerDrugNegative() {
        assertFalse(SparqlController.validateAnswer(Category.DRUG, "Hechen"));
    }

    @Test
    public void testValidateAnswerSoftware() {
        assertTrue(SparqlController.validateAnswer(Category.SOFTWARE, "Apache Kafka"));
    }

    @Test
    public void testValidateAnswerSoftwareNegative() {
        assertFalse(SparqlController.validateAnswer(Category.SOFTWARE, "Apache Hechen"));
    }
}
