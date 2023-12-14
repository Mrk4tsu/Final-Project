package com.ndthang.quanlykhohang.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ndthang.quanlykhohang.entities.Product;

import java.util.List;

@Dao
public interface IProductDAO {
    @Query("SELECT COUNT(*) FROM product")
    int countProduct();
    @Insert
    void insertProduct(Product product);
    @Update
    void updateProduct(Product product);
    @Delete
    void deleteProduct(Product product);
    @Query("DELETE FROM product")
    void deleteAllProduct();
    @Query("SELECT * FROM product")
    List<Product> getListProduct();
    @Query("SELECT * FROM product WHERE name= :productName")
    List<Product> productsExist(String productName);
    @Query("SELECT * FROM product WHERE name LIKE '%'||:name||'%'")
    List<Product> searchProduct(String name);
}
