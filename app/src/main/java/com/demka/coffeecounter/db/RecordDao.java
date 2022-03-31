package com.demka.coffeecounter.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.demka.coffeecounter.db.relations.RecordWithCoffee;

import java.util.List;

@Dao
public interface RecordDao {


    @Query("SELECT * FROM record;")
    List<Record> getAllRecords();

    @Transaction
    @Query("SELECT * FROM record;")
    List<RecordWithCoffee> getAllRecordsWithCoffee();

    @Query("SELECT coffee.name, SUM (amount) as count FROM record INNER JOIN coffee ON record.coffeeId=coffee.id GROUP by coffee.name;")
    List<GroupCoffeeItem> getCoffeeGroupItems();

    @Insert()
    void insertRecord(Record... records);

    @Delete
    void deleteRecord(Record record);
}
