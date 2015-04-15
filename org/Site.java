package org;

import javax.persistence.*;

import java.util.List;

import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double latitude;
    private double longitude;
    private String name;
    @OneToMany(mappedBy = "site")
    @XmlElement(name="tower")
    private List<Tower> towers;


    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public double getLatitude() {
	return this.latitude;
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }

    public double getLongitude() {
	return this.longitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }


    public List<Tower> getTowers() {
	return this.towers;
    }

    public void setTowers(List<Tower> towers) {
	this.towers = towers;
    }

    public Tower addTower(Tower tower) {
	getTowers().add(tower);
	tower.setSite(this);

	return tower;
    }

    public Tower removeTower(Tower tower) {
	getTowers().remove(tower);
	tower.setSite(null);

	return tower;
    }

    public Site(int id, double latitude, double longitude, String name,
	    List<Tower> towers) {
	super();
	this.id = id;
	this.latitude = latitude;
	this.longitude = longitude;
	this.name = name;
	this.towers = towers;
    }

    public Site() {
	super();
    }

}