package joseromero.exam1a.responses;

import java.util.ArrayList;

import joseromero.exam1a.models.CityModel;

public class WeatherResponse {

        public String status;

        public String message;

        public CityModel city;

        public ArrayList<DayWeather> data;

        public WeatherResponse() {

        }

        public WeatherResponse(String status, String message, CityModel city, ArrayList<DayWeather> data) {
                this.status = status;
                this.message = message;
                this.city = city;
                this.data = data;
        }
}
