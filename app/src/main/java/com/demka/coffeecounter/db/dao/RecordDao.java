package com.demka.coffeecounter.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.demka.coffeecounter.db.groupitems.CaffeineDateGroupItem;
import com.demka.coffeecounter.db.groupitems.CoffeeTypeCountGroupItem;
import com.demka.coffeecounter.db.groupitems.CupDateGroupItem;
import com.demka.coffeecounter.db.relations.RecordWithCoffee;
import com.demka.coffeecounter.db.tables.Record;

import java.util.List;

@Dao
public interface RecordDao {


    @Query("SELECT * FROM record;")
    List<Record> getAllRecords();

    @Transaction
    @Query("SELECT * FROM record ORDER BY time DESC;")
    List<RecordWithCoffee> getAllRecordsWithCoffee();

    @Transaction
    @Query("SELECT * FROM record WHERE id=:id;")
    RecordWithCoffee getRecordWithCoffeeById(String id);

    @Query("SELECT coffee.name, SUM(amount) as count FROM record INNER JOIN coffee ON record.coffeeId=coffee.id GROUP by coffee.name;")
    List<CoffeeTypeCountGroupItem> getCoffeeTypeCountGroupItems();


    @Query("SELECT SUM(coffee.mg*record.amount) as caffeine, strftime('%d.%m.%Y', datetime(record.time, 'unixepoch', 'localtime')) as date FROM record INNER JOIN coffee ON record.coffeeId=coffee.id  GROUP BY date ORDER BY record.time;")
    List<CaffeineDateGroupItem> getCaffeineDateGroupItem();

    @Query("SELECT SUM(record.amount) as count, strftime('%d.%m.%Y', datetime(record.time, 'unixepoch', 'localtime')) as date FROM record  GROUP BY date ORDER BY record.time;")
    List<CupDateGroupItem> getCupDateGroupItem();

    @Insert()
    void insertRecord(Record... records);

    @Delete
    void deleteRecord(Record record);
}
