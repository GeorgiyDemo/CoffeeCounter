package com.demka.coffeecounter.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.demka.coffeecounter.db.relations.CoffeeWithRecord;
import com.demka.coffeecounter.db.tables.Coffee;

import java.util.List;

@Dao
public interface CoffeeDao {

    @Query("SELECT * FROM coffee;")
    List<Coffee> getAllCoffee();

    @Transaction
    @Query("SELECT * FROM coffee;")
    List<CoffeeWithRecord> getAllCoffeeWithRecords();

    @Insert()
    void insertCoffee(Coffee... coffees);

    @Delete
    void deleteCoffee(Coffee coffee);
}
