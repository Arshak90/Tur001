package portfolio;

import Core.Interface.Dao;

import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioDao implements Dao<Portfolio>{
    @Override
    public List<Portfolio> getAll() {
        return null;
    }

    @Override
    public Portfolio getById(Integer id) {
        return null;
    }

    @Override
    public void insert(Portfolio item) {

    }

    @Override
    public boolean update(Portfolio item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
