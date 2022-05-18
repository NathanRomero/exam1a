package joseromero.exam1a.responses;

public class DayWeather {
    /**
     * Temperatura promedio
     */
    public String temperature;


    /**
     * Humedad promedio
     */
    public String humidity;

    /**
     * Velocidad Viento promedio
     */
    public String wind;

    /**
     *
     */
    public String pressure;

    /**
     * Resumen del día en texto
     */
    public String description;

    public DayWeather() {

    }

    public DayWeather(String country, String temperature, String humidity, String wind, String pressure, String description, String icon) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
        this.description = description;
    }
}
