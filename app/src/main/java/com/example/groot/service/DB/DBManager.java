package com.example.groot.service.DB;

import java.util.HashMap;

public class DBManager {

    private static HashMap<String, String> DB = new HashMap<>();
    private static final String key = "userId";
    private static final String isAdmin = "admin";

    public static boolean login(String userId, String check) {
        DB.put(key, userId);
        DB.put(isAdmin, check);

        return getIsAdmin();
    }

    public static String getUserInfo() {
        if (!DB.containsKey(key)) {
            throw new RuntimeException();
        }

        return DB.get(key);
    }

    public static boolean getIsAdmin() {
        if (!DB.containsKey(isAdmin)) {
            throw new RuntimeException("check 없습니다.");
        }

        return DB.get(isAdmin).equals("1") ? true : false;
    }

    public static String logout() {
        return DB.remove(key);
    }
}
