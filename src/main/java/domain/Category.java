package domain;
import application.Settings;

public enum Category {
    COUNTRY, RIVER, CITY;

    public String getQuery(){
        switch(this){
            case COUNTRY:
                return Settings.countriesDBPedia;
            case CITY:
                return Settings.citiesDBPedia;
        }
        return Settings.countriesDBPedia;
    }
}
