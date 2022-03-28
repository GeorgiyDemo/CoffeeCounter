package com.demka.coffeecounter.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.demka.coffeecounter.db.Coffee;
import com.demka.coffeecounter.db.Record;

import java.util.List;



public class RecordWithCoffee {
    @Embedded
    public Record record;
    @Relation(
            parentColumn = "coffeeId",
            entityColumn = "id"
    )
    public Coffee coffee;
}