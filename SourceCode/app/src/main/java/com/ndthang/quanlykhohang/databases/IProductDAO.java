package com.ndthang.quanlykhohang.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ndthang.quanlykhohang.entities.Product;

import java.util.List;

@Dao
public interface IProductDAO {
    @Insert
    void insertProduct(Product product);
    @Delete
    void deleteProduct(Product product);
    @Query("SELECT * FROM product")
    List<Product> getListProduct();
}
