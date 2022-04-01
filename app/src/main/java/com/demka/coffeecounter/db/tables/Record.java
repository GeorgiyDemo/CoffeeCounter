package com.demka.coffeecounter.db.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "record")
public class Record {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public Long coffeeId;

    public Long amount;

    public Long time;
}
