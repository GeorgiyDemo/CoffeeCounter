package com.demka.coffeecounter.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface RecordDao {


    @Query("SELECT * FROM record;")
    List<Record> getAllRecords();

    @Insert()
    void insertRecord(Record... records);

    @Delete
    void deleteRecord(Record record);
}
