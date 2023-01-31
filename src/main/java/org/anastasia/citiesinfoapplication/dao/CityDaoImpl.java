package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.exception.SQLProcessingException;
import org.anastasia.citiesinfoapplication.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class CityDaoImpl implements CityDao {
    private static final String FIND_ALL = "select * from city;";
    private static final String DELETE_ALL = "delete from city;";
    private static final String FIND_BY_REGION_CODE = "select * from city where region_code = ?;";
    private static final String SAVE_CITY = "insert into city (name, region_code) values(?, ?);";
    private static final String DELETE_BY_REGION_CODE = "delete from city where region_code = ?;";
    private static final String UPDATE_BY_REGION_CODE = "update city set name = ? where region_code = ?;";

    private final DataSource dataSource;

    @Autowired
    public CityDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public City save(City city) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CITY)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getRegionCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
        return city;
    }

    @Override
    public Optional<City> findByRegionCode(String regionCode) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_REGION_CODE)) {
            preparedStatement.setString(1, regionCode);
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
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
    }

    @Override
    public void deleteByRegionCode(String regionCode) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_REGION_CODE)) {
            preparedStatement.setString(1, regionCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
    }

    @Override
    public City update(String regionCode, City city) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_REGION_CODE)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, regionCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
        return city;
    }

    private City buildCity(ResultSet resultSet) {
        City city = new City();
        try {
            city.setName(resultSet.getString("name"));
            city.setRegionCode(resultSet.getString("region_code"));
        } catch (SQLException e) {
            throw new SQLProcessingException(e.getMessage());
        }
        return city;
    }
}
