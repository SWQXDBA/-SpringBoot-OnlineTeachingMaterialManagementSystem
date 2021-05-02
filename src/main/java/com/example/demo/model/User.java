package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class User {
    private String userName;
    private String passWord;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private boolean isAdmin;

    @OneToMany(mappedBy = "user", targetEntity = Book.class)
    private Set<Book> books = new HashSet<>();

    public User() {
    }

    public User(String userName, String passWord, Long userId, boolean isAdmin) {
        this.userName = userName;
        this.passWord = passWord;
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

}
