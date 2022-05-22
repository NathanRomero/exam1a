package joseromero.exam1a.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import joseromero.exam1a.externalrequests.weatherApi.weatherApi;
import joseromero.exam1a.externalrequests.weatherApi.schemas.forecast;
import joseromero.exam1a.externalrequests.weatherApi.schemas.listSchema;
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
            forecast forecast = weatherApi.getWeatherFor(city.get().getLat(), city.get().getLng(), unitsType);
            HashMap<String, toGroupResult> days = new HashMap<String, toGroupResult>();
            for (listSchema item : forecast.getList()) {
                String date = item.getDt_text();
                DateTime dateTime = new DateTime(date)
                    .withHourOfDay(0)
                    .withMinuteOfHour(0)
                    .withSecondOfMinute(0)
                    .withMillisOfSecond(0);
                String key = dateTime.toString("yyyy-MM-dd");
                if (!days.containsKey(key)) {
                    days.put(key, new toGroupResult());
                }

                toGroupResult day = days.get(key);
                day.humidity.add(item.getMain().getHumidity());
                day.temperatures.add(item.getMain().getTemp());
                day.wind.add(item.getWind().getSpeed());
                day.pressure.add(item.getMain().getPressure());
                day.maxTemperatures.add(item.getMain().getTemp_max());
                day.minTemperatures.add(item.getMain().getTemp_min());

                days.put(key, day);
            }

            for (String dayKey : days.keySet()) {
                toGroupResult day = days.get(dayKey);
                DayWeather dayWeather = new DayWeather();
                dayWeather.date = dayKey;
                String temp = unitsType.equals("metric") ? " C" : " F";
                dayWeather.humidity = day.humidity.stream().reduce(0f, (a, b) -> a + b) / day.humidity.size()+" %";
                dayWeather.temperature = day.temperatures.stream().reduce(0f, (a, b) -> a + b) / day.temperatures.size() + temp;
                dayWeather.wind = day.wind.stream().reduce(0f, (a, b) -> a + b) / day.wind.size() + " m/s";
                dayWeather.pressure = day.pressure.stream().reduce(0f, (a, b) -> a + b) / day.pressure.size() + " hPa";
                dayWeather.maxTemperature = day.maxTemperatures.stream().reduce(0f, (a, b) -> a + b) / day.maxTemperatures.size() + temp;
                dayWeather.minTemperature = day.minTemperatures.stream().reduce(0f, (a, b) -> a + b) / day.minTemperatures.size() + temp;
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
                response.data = response.data.subList(start - 1, end);
            }
        }

        return response;
    }

}
