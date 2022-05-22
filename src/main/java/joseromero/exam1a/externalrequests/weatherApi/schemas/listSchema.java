package joseromero.exam1a.externalrequests.weatherApi.schemas;

import joseromero.exam1a.externalrequests.weatherApi.schemas.main;
import java.util.List;

public class listSchema {
    public String dt;
    public main main;
    public List<weather> weather;
    public clouds clouds;
    public wind wind;
    public long visibility;
    public int pop;
    public String dt_text;

    public String getDt() {
        return this.dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public main getMain() {
        return this.main;
    }

    public void setMain(main main) {
        this.main = main;
    }

    public List<weather> getWeather() {
        return this.weather;
    }

    public void setWeather(List<weather> weather) {
        this.weather = weather;
    }

    public clouds getClouds() {
        return this.clouds;
    }

    public void setClouds(clouds clouds) {
        this.clouds = clouds;
    }

    public wind getWind() {
        return this.wind;
    }

    public void setWind(wind wind) {
        this.wind = wind;
    }

    public long getVisibility() {
        return this.visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public int getPop() {
        return this.pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public String getDt_text() {
        return this.dt_text;
    }

    public void setDt_text(String dt_text) {
        this.dt_text = dt_text;
    }
    
}
