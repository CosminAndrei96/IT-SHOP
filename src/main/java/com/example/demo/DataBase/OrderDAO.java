package com.example.demo.DataBase;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDAO extends CrudRepository<Order, Integer> {
    List<Order> findAllByUserId(int id);
}
