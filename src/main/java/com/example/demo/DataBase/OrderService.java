package com.example.demo.DataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;

    public List<Order> getOrdersByUserId(int id){
        return orderDAO.findAllByUserId(id);
    }
}
