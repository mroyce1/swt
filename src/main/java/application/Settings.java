package application;

public class Settings {

    //DBPedia Queries

    public static final String citiesDBPedia = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "SELECT ?cityName WHERE {\n" +
            "   ?s foaf:name ?cityName.\n" +
            "   ?s a dbo:City.\n" +
            "  filter( regex(str(?cityName), \"^%s\" ))\n" +
            "}\n" +
            "LIMIT 100\n";

    public static final String companiesDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "SELECT ?companyName WHERE {\n" +
            " ?c foaf:name ?companyName.\n" +
            " ?c a dbo:Company.\n" +
            " FILTER( regex(str(?companyName) , \"^%s\"))\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String animalsDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "SELECT DISTINCT ?animalName WHERE {\n" +
            " ?an rdfs:label ?animalName.\n" +
            " ?an a dbo:Animal.\n" +
            " FILTER( regex(str(?animalName) , \"^%s\"))\n" +
            " FILTER (lang(?animalName) = 'en')\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String riversDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?riverName WHERE {\n" +
            "?a rdfs:label ?riverName.\n" +
            "?a a dbo:River.\n" +
            "FILTER (lang(?riverName) = 'en')\n" +
            " FILTER( regex(str(?riverName) , \"^%s\"))\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String soccerplayersDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?soccerPlayerName WHERE {\n" +
            "?a foaf:surname ?soccerPlayerName.\n" +
            "?a a dbo:SoccerPlayer.\n" +
            "FILTER (lang(?soccerPlayerName) = 'en')\n" +
            " FILTER( regex(str(?soccerPlayerName) , \"^%s\"))\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String scientistDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?scientistName WHERE {\n" +
            "?a foaf:surname ?scientistName.\n" +
            "?a a dbo:Scientist.\n" +
            "FILTER (lang(?scientistName) = 'en')\n" +
            " FILTER( regex(str(?scientistName) , \"^%s\"))\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String drugDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?drugName WHERE {\n" +
            "?a rdfs:label ?drugName.\n" +
            "?a a dbo:Drug.\n" +
            "FILTER (lang(?drugName) = 'en')\n" +
            " FILTER( regex(str(?drugName) , \"^%s\"))\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String softwareDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?softwareName WHERE {\n" +
            "?a rdfs:label ?softwareName.\n" +
            "?a a dbo:Software.\n" +
            "FILTER (lang(?softwareName) = 'en')\n" +
            " FILTER( regex(str(?softwareName) , \"^%s\"))\n" +
            " }\n" +
            " LIMIT 100\n";

    //DBPedia Validation Queries

    public static final String doesCityExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK {\n" +
            "    ?city a dbo:PopulatedPlace;\n" +
            "    rdfs:label \"%s\"@en.}";

    public static final String doesCompanyExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK {\n" +
            " ?c foaf:name \"%s\"@en.\n" +
            " ?c a dbo:Company.\n" +
            " }\n";

    public static final String doesAnimalExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK {\n" +
            " ?animal rdfs:label \"%s\"@en.\n" +
            " }\n";

    public static final String doesRiverExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK\n" +
            "{?c rdfs:label\"%s\"@en.\n" +
            "?c a dbo:River.}\n";

    public static final String doesSoccerPlayerExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK\n" +
            "{?c foaf:surname \"%s\"@en.\n" +
            "?c a dbo:SoccerPlayer.}\n";

    public static final String doesScientistExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK\n" +
            "{?c foaf:surname \"%s\"@en.\n" +
            "?c a dbo:Scientist.}\n";

    public static final String doesDrugExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK\n" +
            "{?c rdfs:label \"%s\"@en.\n" +
            "?c a dbo:Drug.}\n";

    public static final String doesSoftwareExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK\n" +
            "{?c rdfs:label \"%s\"@en.\n" +
            "?c a dbo:Software.}\n";


    // Wikidata Queries

    public static final String countriesWikiData = "SELECT ?country ?countryLabel ?article WHERE {" +
            "    ?country wdt:P31 wd:Q3624078 ." +
            "    ?article schema:about ?country ." +
            "    ?article schema:isPartOf <https://en.wikipedia.org/>." +
            "    SERVICE wikibase:label {" +
            "       bd:serviceParam wikibase:language \"en\"" +
            "    }" +
            "}";

    public static final String riversWikiData = "PREFIX bd: <http://www.bigdata.com/rdf#>" +
            "PREFIX wikibase: <http://wikiba.se/ontology#>" +
            "PREFIX wdt: <http://www.wikidata.org/prop/direct/>" +
            "PREFIX wd: <http://www.wikidata.org/entity/>" +
            "SELECT DISTINCT ?riverLabel" +
            "WHERE " +
            "{n" +
            "  {?river wdt:P30 wd:Q46.}" +
            "  UNION {?river wdt:P30 wd:Q48.}" +
            "  UNION {?river wdt:P30 wd:Q15.}" +
            "  UNION {?river wdt:P30 wd:Q18.}" +
            "  UNION {?river wdt:P30 wd:Q49.}" +
            "  UNION {?river wdt:P30 wd:Q538}" +
            "  UNION {?river wdt:P30 wd:Q51.}" +
            "  ?river wdt:P31 wd:Q4022;" +
            "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }" +
            "}";



    public static final String dbpediaEndpoint = "http://dbpedia.org/sparql";
    public static final String wikiDataEndpoint = "https://query.wikidata.org/sparql?query=";
}
