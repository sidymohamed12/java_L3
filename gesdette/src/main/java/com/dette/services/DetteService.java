package com.dette.services;

import java.util.List;

import com.dette.core.Service;
import com.dette.entities.Dette;

public class DetteService implements Service<Dette> {

    @Override
    public void create(Dette value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public List<Dette> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Dette getBy(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }

    @Override
    public int count() {
        // return repository.count();
        return 0;
    }

}
