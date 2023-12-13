package com.ndthang.quanlykhohang.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ndthang.quanlykhohang.entities.User;

import java.util.List;

@Dao
public interface IUserDAO {
    @Insert
    void insertUser(User... user);
    @Delete
    void deleteUser(User user);
    @Query("SELECT * FROM user")
    List<User> getListUser();
}
