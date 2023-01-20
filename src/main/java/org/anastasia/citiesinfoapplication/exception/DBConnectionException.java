package org.anastasia.citiesinfoapplication.exception;

public class DBConnectionException extends RuntimeException{
    public DBConnectionException(String message) {
        super(message);
    }
}
