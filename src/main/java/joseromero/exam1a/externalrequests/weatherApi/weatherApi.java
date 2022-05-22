package joseromero.exam1a.externalrequests.weatherApi;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import joseromero.exam1a.externalrequests.weatherApi.schemas.forecast;
import joseromero.exam1a.externalrequests.weatherApi.schemas.listSchema;

import kong.unirest.Unirest;

public class weatherApi {
    private String apiKey;

    public weatherApi() {
        this.apiKey = "529f104acc06892e0f457061b2b6ed38";
    }

    public forecast getWeatherFor(float lat, float lng, String units) {


        Map r = Unirest.get("https://api.openweathermap.org/data/2.5/weather")
                .header("accept", "application/json")
                .queryString("lat", lat)
                .queryString("lon", lng)
                .queryString("units", units)
                .queryString("appid", this.apiKey)
                .asObject(i -> new Gson().fromJson(i.getContentReader(), HashMap.class))
                .getBody();
        
        forecast response = new forecast();
        response.cnt = (int) r.get("cnt");
        response.message = (int) r.get("message");
        response.cod = (String) r.get("cod");
        


        return response;
    }
   
}
