package joseromero.exam1a.controllers;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TreeMap;

import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import joseromero.exam1a.externalrequests.weatherApi.weatherApi;
import joseromero.exam1a.models.CityModel;
import joseromero.exam1a.responses.DayWeather;
import joseromero.exam1a.responses.WeatherResponse;
import joseromero.exam1a.schemas.toGroupResult;
import joseromero.exam1a.services.CityService;

@Controller
public class MainController {

    @Autowired
    CityService cityService;

    @GetMapping("/getWeather")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public WeatherResponse getWeather(
        @RequestParam(required = true) Long cityId, 
        @RequestParam(required = true) String unitsType,
        @RequestParam(required = false, defaultValue = "1-5") String range
    ) throws ParseException {
        Optional<CityModel> city = cityService.getCity(cityId);
        WeatherResponse response = new WeatherResponse();
        response.city = city;
        response.status = "OK";
        response.message = "success";
        response.data = new ArrayList<DayWeather>();
        if (city.isPresent()) {
            weatherApi weatherApi = new weatherApi();
            Forecast forecast = weatherApi.getWeatherFor(city.get().getLat(), city.get().getLng(), unitsType);
            TreeMap<String, toGroupResult> days = new TreeMap<String, toGroupResult>();
            for (WeatherForecast item : forecast.getWeatherForecasts()) {
                String key = item.getForecastTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (!days.containsKey(key)) {
                    days.put(key, new toGroupResult());
                }

                toGroupResult day = days.get(key);
                day.humidity.add(item.getHumidity().getValue());
                day.temperatures.add(item.getTemperature().getValue());
                day.wind.add(item.getWind().getSpeed());
                day.pressure.add(item.getAtmosphericPressure().getValue());
                day.maxTemperatures.add(item.getTemperature().getMaxTemperature());
                day.minTemperatures.add(item.getTemperature().getMinTemperature());

                days.put(key, day);
            }
            
            DecimalFormat formater = new DecimalFormat("#.##");
            for (String dayKey : days.keySet()) {
                toGroupResult day = days.get(dayKey);
                DayWeather dayWeather = new DayWeather();
                dayWeather.date = dayKey;
                String temp = unitsType.equals("metric") ? " C" : " F";
                dayWeather.humidity = formater.format(
                    day.humidity.stream().reduce(0, (a, b) -> a + b) / day.humidity.size()
                ) + " %";
                dayWeather.temperature = formater.format(day.temperatures.stream().reduce(0d, (a, b) -> a + b) / day.temperatures.size()) + temp;
                dayWeather.wind = formater.format(day.wind.stream().reduce(0d, (a, b) -> a + b) / day.wind.size()) + " m/s";
                dayWeather.pressure = formater.format(day.pressure.stream().reduce(0d, (a, b) -> a + b) / day.pressure.size()) + " hPa";
                dayWeather.maxTemperature = formater.format(day.maxTemperatures.stream().reduce(0d, (a, b) -> a + b) / day.maxTemperatures.size()) + temp;
                dayWeather.minTemperature = formater.format(day.minTemperatures.stream().reduce(0d, (a, b) -> a + b) / day.minTemperatures.size()) + temp;
                response.data.add(dayWeather);
            }
            
            String[] rangeArray = range.split("-");

            if (rangeArray.length == 2) {
                int start = Integer.parseInt(rangeArray[0]);
                int end = Integer.parseInt(rangeArray[1]);
                if (start > end) {
                    int aux = start;
                    start = end;
                    end = aux;
                }
                start = start - 1;
                response.data = response.data.subList(start, end);
            }
        }

        return response;
    }

}
