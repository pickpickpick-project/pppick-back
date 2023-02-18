package com.pickx3.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDGenerateUtil {

    private final static int length = 11;

    public static String makeShortUUID(){
        UUID uuid = UUID.randomUUID();
        return parseToShortUUID(uuid.toString());
    }

    public static String  parseToShortUUID(String uuid){
        int word = ByteBuffer.wrap(uuid.getBytes()).getInt();
        return Integer.toString(word, length);
    }
}
