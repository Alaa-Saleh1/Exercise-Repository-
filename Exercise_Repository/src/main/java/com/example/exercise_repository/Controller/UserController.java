package com.example.exercise_repository.Controller;

import com.example.exercise_repository.ApiResponse.ApiResponse;
import com.example.exercise_repository.Model.User;
import com.example.exercise_repository.Repository.UserRepository;
import com.example.exercise_repository.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-system")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        List<User> users= userService.getAllUsers();
        return ResponseEntity.status(200).body(users);

    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.AddUser(user);
        return ResponseEntity.status(201).body(new ApiResponse("User added successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.UpdateUser(id, user);
        return ResponseEntity.status(201).body(new ApiResponse("User updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.DeleteUser(id);
        return ResponseEntity.status(201).body(new ApiResponse("User deleted successfully"));
    }

    @GetMapping("/login/username/{username}/password/{password}")
    public ResponseEntity<?> loginUser(@PathVariable String username, @PathVariable String password) {
        userService.checkLogin(username, password);
        return ResponseEntity.status(200).body(new ApiResponse("User logged in successfully"));
    }




    @GetMapping("/get-user-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get-user-role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getUserByRole(role);
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/get-user-age/{age}")
    public ResponseEntity<?> getUserByAge(@PathVariable Integer age) {
        List<User> users = userService.getUserByAge(age);
        return ResponseEntity.status(200).body(users);
    }

}
