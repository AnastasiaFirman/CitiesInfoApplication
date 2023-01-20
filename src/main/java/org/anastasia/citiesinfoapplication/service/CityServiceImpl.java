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
    public City findById(Long id) {
        return cityDao.findById(id).orElseThrow(CityNotFoundException::new);
    }

    @Override
    public City findByRegionNumber(int regionNumber) {
        return cityDao.findByRegionNumber(regionNumber).orElseThrow(CityNotFoundException::new);
    }

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public void deleteAll() {
        cityDao.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        cityDao.deleteById(id);
    }

    @Override
    public void deleteByRegionNumber(int regionNumber) {
        cityDao.deleteByRegionNumber(regionNumber);
    }
}
