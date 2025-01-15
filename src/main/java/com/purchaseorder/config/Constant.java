package com.purchaseorder.config;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Constant {
    private static final AtomicInteger userIdCounter = new AtomicInteger();

    public static int generateUserId() {
        return userIdCounter.incrementAndGet();
    }

    public static int generateItemId() {
        return ThreadLocalRandom.current().nextInt(1000000, 10000000);
    }

    public static int generatePoHId() {
        return ThreadLocalRandom.current().nextInt(1000000, 10000000);
    }

    public static int generatePoDId() {
        return ThreadLocalRandom.current().nextInt(1000000, 10000000);
    }

    // Response Messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String ITEM_NOT_FOUND = "Item not found";
    public static final String PO_NOT_FOUND = "PO not found";
    public static final String USER_DELETED_SUCCESS = "User deleted successfully";
    public static final String ITEM_DELETED_SUCCESS = "Item deleted successfully";
    public static final String PO_DELETED_SUCCESS = "PO deleted successfully";
    public static final String POD_NOT_FOUND = "Purchase Order Detail not found";
    public static final String POD_DELETED_SUCCESS = "Purchase Order Detail deleted successfully";
    public static final String POH_ID_OR_ITEM_NOT_EXIST = "Purchase Order ID or Item Id is not Exist";
}


