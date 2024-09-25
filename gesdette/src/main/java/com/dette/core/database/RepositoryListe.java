package com.dette.core.database;

import java.util.ArrayList;
import java.util.List;

import com.dette.core.Repository;

public class RepositoryListe<T> implements Repository<T> {

    protected List<T> listes = new ArrayList<>();

    @Override
    public void insert(T value) {
        listes.add(value);
    }

    @Override
    public List<T> selectAll() {
        return listes;
    }

    @Override
    public int count() {
        return listes.size();
    }

    // config tout ce qui est specifique
}
