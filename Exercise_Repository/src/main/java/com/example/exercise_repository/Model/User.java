package com.example.exercise_repository.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4, message = "name must be more than 4 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 4, message = "name must be more than 4 characters")
    @Column(columnDefinition = "varchar(18) not null unique")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;


    @Email (message = "email must be valid format")
    @NotEmpty(message = "email cannot be empty")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "user|admin",message = "role must be 'user' or 'admin' only")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotNull(message = "age cannot be null")
    @Positive(message = "age must be integer")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
