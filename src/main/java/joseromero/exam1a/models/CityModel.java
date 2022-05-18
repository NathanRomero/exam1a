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
    @Column(name = "id")
    private Long id;

    private float lat;

    private float lng;

    private String name;

    private String country;


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

}
