import static org.junit.Assert.*;

import application.Settings;
import org.junit.Test;
import application.SparqlController;

public class UnitTests {

    @Test
    public void testQuery() {
        SparqlController sparqlController = new SparqlController();
        sparqlController.query(Settings.dbpediaEndpoint, Settings.countriesDBPedia);
    }

    @Test
    public void testValidateAnswer() {
        SparqlController sparqlController = new SparqlController();
        assertTrue(sparqlController.validateAnswer(Settings.dbpediaEndpoint, Settings.doesCompanyExistQuery));
    }
}
