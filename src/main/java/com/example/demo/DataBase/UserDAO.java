package com.example.demo.DataBase;


import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserDAO extends CrudRepository<User, Integer> {

    public List<User> findAllByEmail(String email);
}
