package Dao;

import Interface.Dao;
import Models.City;

import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class CityDao implements Dao<City> {

    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public City getById(Integer id) {
        return null;
    }

    @Override
    public void insert(City item) {

    }

    @Override
    public boolean update(City item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}