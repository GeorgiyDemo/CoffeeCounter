package com.demka.coffeecounter.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="record")
public class Record {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public Long coffeeId;

    @ColumnInfo
    public Long amount;

    @ColumnInfo
    public Long time;
}
