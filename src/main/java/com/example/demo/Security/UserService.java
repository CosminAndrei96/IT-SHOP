package com.example.demo.Security;

import com.example.demo.DataBase.User;
import com.example.demo.DataBase.UserDAO;
import com.example.demo.Service.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    @Autowired
    UserDAO userDAO; //injected

    public void registerUser(String fullName, String userName, String phoneNumber, String email, String password1, String password2, String adress, String gender) throws UserException {
        if (!password1.equals(password2)) {
            throw new UserException("The passwords are not the same");
        }

        User user = new User();
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(password1);
        user.setAdress(adress);
        user.setGender(gender);
        userDAO.save(user);

    }

    public List<User> loginUser(String email, String password) throws UserException {
        List<User> userList = userDAO.findAllByEmail(email);

        if (userList.isEmpty()) {
            throw new UserException("Please fill the fields!");
        }

        User user = userList.get(0);
        if (!user.getPassword().equals(password)) {
            throw new UserException("The email or password is incorrect");
        }
        return userList;
    }

    public String getNameOfUser(int userSesionId) {
        return userDAO.findById(userSesionId).get().getFullName();
    }

    public String getAdress(int userSesionId) {
        return userDAO.findById(userSesionId).get().getAdress();
    }

}
