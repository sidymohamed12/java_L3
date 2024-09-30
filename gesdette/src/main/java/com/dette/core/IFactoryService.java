package com.dette.core;

public interface IFactoryService<T> {
    Service<T> createService();
}
