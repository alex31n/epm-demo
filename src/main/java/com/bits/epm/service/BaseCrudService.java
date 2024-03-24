package com.bits.epm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface BaseCrudService <T>{

    public Page<T> findAll(Specification<T> spec, Pageable pageable);

    public T findById(long id);

    public T create(T obj);

    public T update(long id, T obj);


    public void deleteById(long id);
}
