package org.anastasia.citiesinfoapplication.service;

import org.anastasia.citiesinfoapplication.dao.CityDao;
import org.anastasia.citiesinfoapplication.exception.CityNotFoundException;
import org.anastasia.citiesinfoapplication.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;

    @Autowired
    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public City save(City city) {
        return cityDao.save(city);
    }

    @Override
    public City findByRegionCode(String regionCode) {
        return cityDao.findByRegionCode(regionCode).orElseThrow(CityNotFoundException::new);
    }

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public void deleteByRegionCode(String regionCode) {
        cityDao.findByRegionCode(regionCode).orElseThrow(CityNotFoundException::new);
        cityDao.deleteByRegionCode(regionCode);
    }

    @Override
    public City update(String regionCode, City city) {
        cityDao.findByRegionCode(regionCode).orElseThrow(CityNotFoundException::new);
        return cityDao.update(regionCode, city);
    }
}
