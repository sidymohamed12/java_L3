package com.dette.core.database;

import java.util.List;

import com.dette.core.Repository;
import com.dette.core.database.implement.DataSourceImpl;

public class RepositoryBD<T> extends DataSourceImpl implements Repository<T> {

    @Override
    public void insert(T value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public List<T> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

}
