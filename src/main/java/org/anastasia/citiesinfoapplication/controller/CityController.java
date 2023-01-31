package org.anastasia.citiesinfoapplication.controller;

import org.anastasia.citiesinfoapplication.model.City;
import org.anastasia.citiesinfoapplication.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/api/v1/city")
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/api/v1/city/{regionCode}")
    public City findByRegionCode(@PathVariable("regionCode") String regionCode) {
        return cityService.findByRegionCode(regionCode);
    }

    @PostMapping("/api/v1/city")
    public City save(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/api/v1/city/{regionCode}")
    public City update(@RequestBody City city, @PathVariable("regionCode") String regionCode) {
        return cityService.update(regionCode, city);
    }

    @DeleteMapping("/api/v1/city/{regionCode}")
    public void deleteByRegionCode(@PathVariable("regionCode") String regionCode) {
        cityService.deleteByRegionCode(regionCode);
    }

}
