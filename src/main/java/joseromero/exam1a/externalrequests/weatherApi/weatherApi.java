package joseromero.exam1a.externalrequests.weatherApi;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import joseromero.exam1a.externalrequests.weatherApi.schemas.forecast;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;


public class weatherApi {
    private String apiKey;

    public weatherApi() {
        this.apiKey = "529f104acc06892e0f457061b2b6ed38";
    }

    public forecast getWeatherFor(float lat, float lng, String units) {


        HttpResponse<String> s = Unirest.get("https://api.openweathermap.org/data/2.5/weather")
                .header("accept", "application/json")
                .queryString("lat", lat)
                .queryString("lon", lng)
                .queryString("units", units)
                .queryString("appid", this.apiKey)
                .asString();
        
        String body = s.getBody();
        JSONObject myJson = new JSONObject(body);
        JsonArray data = myJson.get("main");

        forecast response = new forecast();
        return response;
    }
   
}
