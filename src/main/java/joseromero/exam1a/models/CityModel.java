package joseromero.exam1a.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class CityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(scale = 4, precision = 10, name = "lat", nullable = false)
    private float lat;

    @Column(scale = 4, precision = 10, name = "lng", nullable = false)
    private float lng;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String state;

    @Column(nullable = false, length = 30)
    private String country;

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public float getLat(){
        return this.lat;
    }

    public void setLat(float lat){
        this.lat = lat;
    }

    public float getLng(){
        return this.lng;
    }

    public void setLng(float lng){
        this.lng = lng;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCountry(){
        return this.country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getFullName(){
        return this.name + ", " + this.state + ", " + this.country;
    }

}
