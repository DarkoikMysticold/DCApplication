package com.distancecalc.DCApplication;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="distance")
@JacksonXmlRootElement(localName = "distance")
@XmlRootElement(name="distance")
public class Distance {
    @Id
    @JacksonXmlProperty(isAttribute = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private Integer distance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fromCity", nullable = false)
    private City fromCity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "toCity", nullable = false)
    private City toCity;

    Distance(Long fromCity, Long toCity, Integer distance)
    {
        this.fromCity.setId(fromCity);
        this.toCity.setId(toCity);
        this.distance=distance;
    }

    @XmlElement(name="distanceId")
    public Integer getId() {return id;}

    public void setId(Integer id) {this.id=id;}

    @XmlElement(name="fromCity")
    public Long getFromCity() { return fromCity.getId(); }

    public void setFromCity(Long fromCity)
    {
        this.fromCity.setId(fromCity);
    }

    @XmlElement(name="toCity")
    public Long getToCity()
    {
        return toCity.getId();
    }

    public void setToCity(Long toCity)
    {
        this.toCity.setId(toCity);
    }

    @XmlElement(name="distance")
    public Integer getDistance()
    {
        return distance;
    }

    public void setDistance(Integer distance)
    {
        this.distance = distance;
    }
}
