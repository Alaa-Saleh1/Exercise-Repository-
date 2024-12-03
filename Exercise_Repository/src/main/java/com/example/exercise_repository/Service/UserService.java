package com.example.exercise_repository.Service;

import com.example.exercise_repository.ApiResponse.ApiException;
import com.example.exercise_repository.Model.User;
import com.example.exercise_repository.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void AddUser(User user) {
        userRepository.save(user);
    }

    public void UpdateUser(Integer id , User user) {
        User oldUser = userRepository.findUserById(id);

        if(oldUser == null) {
            throw new ApiException("user not found");
        }
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());
        userRepository.save(oldUser);
    }


    public void DeleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        if(user == null) {
            throw new ApiException("user not found");
        }
        userRepository.delete(user);
    }


    public void checkLogin(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if(user == null) {
            throw new ApiException("username or password incorrect");
        }
        if(!user.getPassword().equals(password)) {
            throw new ApiException("username or password incorrect");
        }
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new ApiException("user not found");
        }
        return user;
    }

    public List<User> getUserByRole(String role) {
        List<User> users = userRepository.getUsersByRole(role);
        if(users == null) {
            throw new ApiException("user not found");
        }
        return users;
    }

    public List<User> getUserByAge(Integer age) {
        List<User> users = userRepository.getUsersByAge(age);
        if(users == null) {
            throw new ApiException("user not found");
        }
        return users;
    }

}
