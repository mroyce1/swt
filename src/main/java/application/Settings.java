package application;

public class Settings {
    public static final String citiesDBPedia =  "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"+
            "SELECT ?cityName WHERE {\n" +
            "   ?s foaf:name ?cityName.\n" +
            "   ?s a dbo:City.\n" +
            "  filter( regex(str(?cityName), \"^B\" ))\n" +
            "}\n" +
            "LIMIT 100\n";

    public static final String countriesDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"+
            "SELECT ?companyName WHERE {\n" +
            " ?c foaf:name ?companyName.\n" +
            " ?c a dbo:Company.\n" +
            " FILTER( regex(str(?companyName) , \"^B\"))\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String countries = "bla";
    public static final String rivers = "bla";


    public static final String dbpediaEndpoint = "http://dbpedia.org/sparql";
}
