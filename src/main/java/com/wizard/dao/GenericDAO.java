package com.wizard.dao;

import java.util.Optional;

public interface GenericDAO<T> {

    T create(T obj);

    Optional<T> findById(Long id);

    T update(T obj);

    void delete(T obj);

}
