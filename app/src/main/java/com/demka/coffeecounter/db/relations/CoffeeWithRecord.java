package com.demka.coffeecounter.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.demka.coffeecounter.db.tables.Coffee;
import com.demka.coffeecounter.db.tables.Record;

import java.util.List;


public class CoffeeWithRecord {
    @Embedded
    public Coffee coffee;
    @Relation(
            parentColumn = "id",
            entityColumn = "coffeeId"
    )
    public List<Record> records;
}