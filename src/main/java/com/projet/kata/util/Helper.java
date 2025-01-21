package com.projet.kata.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;

public class Helper {

    public static final String ADMIN_EMAIL = "admin@admin.com";

    public static void ensureAdminAccess() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!ADMIN_EMAIL.equals(currentUserEmail)) {
            throw new IllegalStateException("Access denied: Only admin can perform this operation");
        }
    }

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
