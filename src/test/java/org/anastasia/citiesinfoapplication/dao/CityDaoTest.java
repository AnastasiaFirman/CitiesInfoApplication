package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.model.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class CityDaoTest extends BaseIntegrationTest{
    private final CityDao cityDao;
    private final DataSource dataSource;

    @Autowired
    public CityDaoTest(CityDao cityDao, DataSource dataSource) {
        this.cityDao = cityDao;
        this.dataSource = dataSource;
    }


    @AfterEach
    void deleteAll() {
        cityDao.deleteAll();
    }

    @Test
    void saveAndFindByRegionNumberTest() {
        City cityForSave = new City("Якутск", "14");
        cityDao.save(cityForSave);
        City foundCity = cityDao.findByRegionCode(cityForSave.getRegionCode()).get();
        Assertions.assertEquals(cityForSave, foundCity);
    }

    @Test
    void findAllTest() {
        cityDao.save(new City("Ярославль", "76"));
        cityDao.save(new City("Краснодар", "23"));
        Assertions.assertEquals(2, cityDao.findAll().size());
    }

    @Test
    void deleteByRegionNumberTest() {
        City city = new City("Калининград", "39");
        cityDao.deleteByRegionCode(city.getRegionCode());
        Assertions.assertEquals(0, cityDao.findAll().size());
    }
}
