package com.demka.coffeecounter.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.demka.coffeecounter.db.groupitems.CaffeineDateGroupItem;
import com.demka.coffeecounter.db.groupitems.CoffeeTypeCountGroupItem;
import com.demka.coffeecounter.db.relations.RecordWithCoffee;

import java.util.List;

@Dao
public interface RecordDao {


    @Query("SELECT * FROM record;")
    List<Record> getAllRecords();

    @Transaction
    @Query("SELECT * FROM record ORDER BY time DESC;")
    List<RecordWithCoffee> getAllRecordsWithCoffee();

    @Query("SELECT coffee.name, SUM(amount) as count FROM record INNER JOIN coffee ON record.coffeeId=coffee.id GROUP by coffee.name;")
    List<CoffeeTypeCountGroupItem> getCoffeeTypeCountGroupItems();

    @Query("SELECT SUM(coffee.mg*record.amount) as caffeine, strftime('%d.%m.%Y', datetime(record.time, 'unixepoch', 'localtime')) as date FROM record INNER JOIN coffee ON record.coffeeId=coffee.id  GROUP BY date ORDER BY record.time;")
    List<CaffeineDateGroupItem> getCaffeineDateGroupItem();

    @Insert()
    void insertRecord(Record... records);

    @Delete
    void deleteRecord(Record record);
}
