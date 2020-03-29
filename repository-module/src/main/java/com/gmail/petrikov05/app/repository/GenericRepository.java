package com.gmail.petrikov05.app.repository;

import java.util.List;

public interface GenericRepository<I, T> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(I id);

    List<T> findAll();

    List<T> findWithPagination(Integer startPosition, Integer numberByPage);

    Long getQuantityRow();

}
