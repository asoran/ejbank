package com.ejbank.repositories;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Repository<T> {

    T getById(int id);

    List<T> getAll();

    default List<T> getByFilter(Predicate<? super T> filter) {
        return getAll().stream().filter(filter).collect(Collectors.toList());
    }

    T add(T element);

    T remove(T element);

    T update(T element);
}
