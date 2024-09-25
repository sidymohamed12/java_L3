package com.dette.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dette.core.database.implement.DataSourceImpl;
import com.dette.entities.Client;

public abstract class RepositoryBDImpl<T> extends DataSourceImpl implements Repository<T> {
    protected String tableName;

    public abstract Client converToObjet(ResultSet rs) throws SQLException;

}
