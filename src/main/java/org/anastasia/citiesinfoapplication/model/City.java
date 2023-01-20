package org.anastasia.citiesinfoapplication.model;

import java.util.Objects;

public class City {
    private Long id;
    private String name;
    private int regionNumber;


    public City(Long id, String name, int regionNumber) {
        this.id = id;
        this.name = name;
        this.regionNumber = regionNumber;
    }

    public City(String name, int regionNumber) {
        this.name = name;
        this.regionNumber = regionNumber;
    }

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(int regionNumber) {
        this.regionNumber = regionNumber;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regionNumber=" + regionNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return regionNumber == city.regionNumber && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regionNumber);
    }
}
