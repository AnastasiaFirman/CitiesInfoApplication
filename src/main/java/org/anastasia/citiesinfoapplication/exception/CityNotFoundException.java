package org.anastasia.citiesinfoapplication.exception;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException() {
        super("Город не найден");

    }
}
