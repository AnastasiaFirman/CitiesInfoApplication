package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.dbconnector.Connector;
import org.anastasia.citiesinfoapplication.exception.CityNotFoundException;
import org.anastasia.citiesinfoapplication.exception.SQLProcessingException;
import org.anastasia.citiesinfoapplication.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class CityDaoImpl implements CityDao {
    private static final String FIND_ALL = "select * from city;";
    private static final String DELETE_ALL = "delete from city;";
    private static final String FIND_BY_REGION_NUMBER = "select * from city where region_number = ?;";
    private static final String FIND_BY_ID = "select * from city where id = ?;";
    private static final String SAVE_CITY = "insert into city (name, region_number) values(?, ?);";
    private static final String DELETE_BY_ID = "delete from city where id = ?;";
    private static final String DELETE_BY_REGION_NUMBER = "delete from city where region_number = ?;";
    private static final String UPDATE_BY_ID = "update city set name = ?, region_number = ? where id = ?;";

    @Autowired
    private final Connector connector;

    public CityDaoImpl(Connector connector) {
        this.connector = connector;
    }


    @Override
    public City save(City city) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CITY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getRegionNumber());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                city.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
        return city;
    }

    @Override
    public Optional<City> findById(Long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(buildCity(resultSet));
            }
                return Optional.empty();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
    }

    @Override
    public Optional<City> findByRegionNumber(int regionNumber) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_REGION_NUMBER)) {
            preparedStatement.setInt(1, regionNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildCity(resultSet));
            }
                return Optional.empty();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
    }

    @Override
    public List<City> findAll() {
        List<City> result = new LinkedList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(buildCity(resultSet));
            }
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
        return result;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
    }

    @Override
    public void deleteByRegionNumber(int regionNumber) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_REGION_NUMBER)) {
            preparedStatement.setInt(1, regionNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
    }

    @Override
    public City update(Long id, City city) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getRegionNumber());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
        return city;
    }

    private City buildCity(ResultSet resultSet) {
        City city = new City();
        try {
            city.setId(resultSet.getLong("id"));
            city.setName(resultSet.getString("name"));
            city.setRegionNumber(resultSet.getInt("region_number"));
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
        return city;
    }
}
