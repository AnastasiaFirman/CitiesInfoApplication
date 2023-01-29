package org.anastasia.citiesinfoapplication.model;

import java.util.Objects;

public class City {
    private String name;
    private String regionCode;


    public City(String name, String regionCode) {
        this.name = name;
        this.regionCode = regionCode;
    }

    public City() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", regionNumber=" + regionCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return regionCode == city.regionCode && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regionCode);
    }
}
