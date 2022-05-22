package joseromero.exam1a.responses;

import java.util.List;
import java.util.Optional;

import joseromero.exam1a.models.CityModel;

public class WeatherResponse {

        public String status;

        public String message;

        public Optional<CityModel> city;

        public List<DayWeather> data;

        public WeatherResponse() {

        }
}
