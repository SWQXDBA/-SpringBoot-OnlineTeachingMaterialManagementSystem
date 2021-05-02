package com.example.demo.Dao;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaSpecificationExecutor<Book>, JpaRepository<Book, Long> {


    List<Book> findAll();

    List<Book> getBookByUser(User User);
}
