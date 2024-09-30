package com.dette.core;

public interface IFactoryRepo<T> {
    Repository<T> createRepository();
}
