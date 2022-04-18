package com.demka.coffeecounter.db.tables;

import androidx.room.Entity;

@Entity(tableName = "record")
public class Record extends BaseEntity {

    public Long coffeeId;

    public Long amount;

    public Long time;
}
