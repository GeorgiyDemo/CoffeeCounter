package com.demka.coffeecounter.db.tables;

import androidx.room.PrimaryKey;

public class BaseEntity {
    @PrimaryKey(autoGenerate = true)
    public Long id;
}
