package com.demka.coffeecounter.db;

import android.content.ContentValues;

import java.util.Arrays;
import java.util.List;

public class CoffeeData {

    public static int length = 22;

    private static List<String> name = Arrays.asList(
            "Affogato", "Americano", "Black coffee with milk and sugar",
            "Black coffee with milk", "Black coffee", "Black eye",
            "Caffe latte", "Cappuccino", "Cha phe sua da",
            "Cold brew", "Dripped eye", "Espresso macchiato",
            "Green coffee", "Iced coffee", "Irish coffee",
            "Latte macchiato", "Lazy eye", "Mocha",
            "Nitrous coffee", "Red eye", "Single espresso",
            "Turkish coffee"
    );

    private static List<Double> caffeine = Arrays.asList(
            75.0, 112.0, 147.5,
            147.5, 147.5, 223.0,
            128.0, 128.0, 150.0,
            200.0, 112.0, 212.0,
            33.5, 40.0, 120.0,
            128.0, 112.0, 125.0,
            162.5, 112.0, 212.0,
            84.54);

    private static List<String> imagePath = Arrays.asList(
            "affogato", "americano", "black_coffee_with_milk_and_sugar",
            "black_coffee_with_milk", "black_coffee", "black_eye",
            "caffe_latte", "cappuccino", "cha_phe_sua_da",
            "cold_brew", "dripped_eye", "espresso_macchiato",
            "green_coffee", "iced_coffee", "irish_coffee",
            "latte_macchiato", "lazy_eye", "mocha",
            "nitrous_coffee", "red_eye", "single_double_espresso",
            "turkish_coffee"
    );

    public static ContentValues getCoffeeItem(int i) {
        ContentValues values = new ContentValues();
        values.put("name", name.get(i));
        values.put("mg", caffeine.get(i));
        values.put("image_path", imagePath.get(i));
        return values;
    }
}
