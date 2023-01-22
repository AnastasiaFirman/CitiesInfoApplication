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

    @GetMapping("/api/v1/city/{id}")
    public City findById(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @GetMapping("/api/v2/city/{regionNumber}")
    public City findByRegionNumber(@PathVariable("regionNumber") Integer regionNumber) {
        return cityService.findByRegionNumber(regionNumber);
    }

    @PostMapping("/api/v1/city")
    public City save(@RequestBody City city) {
        return cityService.save(city);
    }

    @PatchMapping("/api/v1/city/{id}")
    public City update(@RequestBody City city, @PathVariable("id") Long id) {
        return cityService.update(id, city);
    }

    @DeleteMapping("/api/v1/city/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        cityService.deleteById(id);
    }

}
