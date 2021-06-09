package com.distancecalc.DCApplication;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Entity
@Table(name="cities")
@JacksonXmlRootElement(localName = "cities")
@XmlRootElement(name="cities")
public class City {
    @Id
    @JacksonXmlProperty(isAttribute = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JacksonXmlProperty(isAttribute = true)
    @Column(unique = true)
    private String cityName;

    @JacksonXmlProperty
    private Double latitude;
    @JacksonXmlProperty
    private Double longitude;

    @OneToMany(mappedBy = "fromCity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Distance> City1;

    @OneToMany(mappedBy = "toCity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Distance> City2;

    City(){}

    City(String name, Double latitude, Double longitude){
        this.cityName=name;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    @XmlElement(name="cityId")
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id=id;
    }

    @XmlElement(name="cityName")
    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String name)
    {
        this.cityName=name;
    }

    @XmlElement(name="latitude")
    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude=latitude;
    }

    @XmlElement(name="longitude")
    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude=longitude;
    }

    @Override
    public String toString()
    {
        return "{ id = " + id + ", name = " + cityName + " }";
    }

    
}
