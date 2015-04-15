package org;

import javax.persistence.*;

import java.util.List;

import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Tower{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double height;
    private String name;
    private int sides;
    @OneToMany(mappedBy = "tower")
    @XmlElement(name = "equipment")
    private List<Equipment> equipments;
    @ManyToOne
    @JoinColumn(name = "siteId")
    @XmlTransient
    private Site site;

    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public double getHeight() {
	return this.height;
    }

    public void setHeight(double height) {
	this.height = height;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getSides() {
	return this.sides;
    }

    public void setSides(int sides) {
	this.sides = sides;
    }


    public List<Equipment> getEquipments() {
	return this.equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
	this.equipments = equipments;
    }

    public Equipment addEquipment(Equipment equipment) {
	getEquipments().add(equipment);
	equipment.setTower(this);

	return equipment;
    }

    public Equipment removeEquipment(Equipment equipment) {
	getEquipments().remove(equipment);
	equipment.setTower(null);

	return equipment;
    }


    public Site getSite() {
	return this.site;
    }

    public void setSite(Site site) {
	this.site = site;
    }

    public Tower(int id, double height, String name, int sides,
	    List<Equipment> equipments, Site site) {
	super();
	this.id = id;
	this.height = height;
	this.name = name;
	this.sides = sides;
	this.equipments = equipments;
	this.site = site;
    }

    public Tower() {
	super();
    }

}