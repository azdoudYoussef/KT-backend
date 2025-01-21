package com.projet.kata.util;

public class Helper {

    public static long checkIdFormat(String id) {
        long productId;
        try {
            productId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid product ID format");
        }
        return productId;
    }

}
