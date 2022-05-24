package joseromero.exam1a.externalrequests.weatherApi;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;


public class weatherApi {
    private String apiKey;

    public weatherApi() {
        this.apiKey = "529f104acc06892e0f457061b2b6ed38";
    }

    public Forecast getWeatherFor(float lat, float lng, String units) {
        OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(this.apiKey);
        Coordinate coordinate = Coordinate.of(lat, lng);
        UnitSystem unit = null;
        if (units.equals("Metric")) {
            unit = UnitSystem.METRIC;
        } else {
            unit = UnitSystem.IMPERIAL;
        }
        Forecast response = openWeatherClient.forecast5Day3HourStep()
            .byCoordinate(coordinate)
            .unitSystem(unit)
            .language(Language.SPANISH)
            .retrieve()
            .asJava();

        return response;
    }

}
