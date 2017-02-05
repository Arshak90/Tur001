package Core.Dao;

import Core.Interface.Dao;
import Core.Models.Country;

import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class CountryDao implements Dao<Country> {

    @Override
    public List<Country> getAll() {
        return null;
    }

    @Override
    public Country getById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(Country item) {
        return false;
    }

    @Override
    public boolean update(Country item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
