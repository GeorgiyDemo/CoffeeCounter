package com.demka.coffeecounter.models;

import lombok.Data;

@Data
public class CoffeeModel extends BaseDbModel{
    String name;
    Double mg;
    String imagePath;
}
