package com.dette.repository.bd;

import java.util.List;

import com.dette.core.Repository;
import com.dette.entities.Dette;

public class DetteRepositoryBD implements Repository<Dette> {

    @Override
    public void insert(Dette value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public List<Dette> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    // @Override
    public Dette selectBy(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

}
