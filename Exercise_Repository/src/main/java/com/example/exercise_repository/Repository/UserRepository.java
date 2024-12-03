package com.example.exercise_repository.Repository;

import com.example.exercise_repository.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(int id);

    User findUserByEmail(String email);

    @Query("select u from User u where u.username=?1")
    User getUserByUsername(String username);

    @Query("select u from User u where u.role=?1")
    List<User> getUsersByRole(String role);



    @Query("select u from User u where u.age>=?1")
    List<User> getUsersByAge(Integer age);

}
