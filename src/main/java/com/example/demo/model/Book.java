package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookname;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "Book_to_user", referencedColumnName = "userId")
    private User user;

    private String author;
    private String remarks;
    private boolean isBorrowed;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String body;


}
