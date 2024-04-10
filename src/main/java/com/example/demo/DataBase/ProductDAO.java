package com.example.demo.DataBase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDAO extends CrudRepository<Product, Integer> {
    List<Product> findAllByCategory(String category);


}


