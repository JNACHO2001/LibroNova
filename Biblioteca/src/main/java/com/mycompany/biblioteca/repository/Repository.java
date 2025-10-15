package com.mycompany.biblioteca.repository;

import java.util.List;

public interface Repository<T, ID> {

    T create(T t);

    T searchById(ID id);

    List<T> searchAll();

    T update(T t);

    void delete(ID id);

}
