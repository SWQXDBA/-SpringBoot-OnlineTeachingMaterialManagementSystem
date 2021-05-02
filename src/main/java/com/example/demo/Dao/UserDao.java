package com.example.demo.Dao;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaSpecificationExecutor<User>, JpaRepository<User, Long> {
    public int countUserByUserName(String Name);

    public User getUserByUserNameAndPassWord(String UserName, String PassWord);

    public User getUserByUserId(Long UserId);
}
