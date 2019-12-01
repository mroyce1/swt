package domain;

import application.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Enum that represents the various categories.
 */

public enum Category {
    COUNTRY, RIVER, CITY, SOCCERPLAYER, SCIENTIST, DRUG, SOFTWARE;

    /**
     * Returns the SELECT query depending on the chosen category.
     * @return String
     */
    public String getListQuery() {
        switch (this) {
            case COUNTRY:
                return Settings.countriesWikiData;
            case CITY:
                return Settings.citiesDBPedia;
            case SOCCERPLAYER:
                return Settings.soccerplayersDBPedia;
            case SCIENTIST:
                return Settings.scientistDBPedia;
            case DRUG:
                return Settings.drugDBPedia;
            case SOFTWARE:
                return Settings.softwareDBPedia;
        }
        return Settings.riversDBPedia;
    }

    /**
     * Returns the validate (ASK) query depending on the chosen category.
     * @return String
     */
    public String getValidateQuery() {
        switch (this) {
            case COUNTRY:
                return Settings.citiesDBPedia;
            case CITY:
                return Settings.doesCityExistQuery;
            case SOCCERPLAYER:
                return Settings.doesSoccerPlayerExistQuery;
            case SCIENTIST:
                return Settings.doesScientistExistQuery;
            case DRUG:
                return Settings.doesDrugExistQuery;
            case SOFTWARE:
                return Settings.doesSoftwareExistQuery;
        }
        return Settings.doesRiverExistQuery;
    }

    public List<String> getSuffixes() {
        switch (this) {
            case COUNTRY:
                return new ArrayList<String>() {{
                    add(" Country");
                    add(" country");
                    add(" (Country)");
                    add(" (country)");
                }};
            case CITY:
                return new ArrayList<String>() {{
                    add(" City");
                    add(" city");
                    add(" (City)");
                    add(" (city)");
                }};
            case SOCCERPLAYER:
                return new ArrayList<String>() {{
                    add(" SoccerPlayer");
                    add(" soccerplayer");
                    add(" (SoccerPlayer)");
                    add(" (soccerplayer)");
                }};
            case SCIENTIST:
                return new ArrayList<String>() {{
                    add(" Scientist");
                    add(" scientist");
                    add(" (Scientist)");
                    add(" (scientist)");
                }};
            case DRUG:
                return new ArrayList<String>() {{
                    add(" Drug");
                    add(" drug");
                    add(" (Drug)");
                    add(" (drug)");
                }};
            case SOFTWARE:
                return new ArrayList<String>() {{
                    add(" Software");
                    add(" software");
                    add(" (Software)");
                    add(" (software)");
                }};
        }
        return new ArrayList<String>() {{
            add(" River");
            add(" river");
            add(" (River)");
            add(" (river)");
        }};
    }

    public String getQueryVariable(){
        switch (this) {
            case COUNTRY:
                return "country";
            case CITY:
                return "cityName";
            case SOCCERPLAYER:
                return "soccerPlayerName";
            case SCIENTIST:
                return "scientistName";
            case DRUG:
                return "drugName";
            case SOFTWARE:
                return "softwareName";
        }
        return "riverName";
    }

    /**
     * Returns the endpoint (DBPedia or WikiData) depending on the chosen category
     * @return String
     */
    public String getEndpoint() {
        if (this == COUNTRY) {
            return Settings.wikiDataEndpoint;
        }
        return Settings.dbpediaEndpoint;
    }
}
