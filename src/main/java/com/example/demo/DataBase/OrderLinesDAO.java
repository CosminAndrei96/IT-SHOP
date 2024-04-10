package com.example.demo.DataBase;

import org.springframework.data.repository.CrudRepository;

public interface OrderLinesDAO extends CrudRepository<OrderLines, Integer> {
}
