package org.t01.gdp.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VerificationCode {
    private static Map<String, String> codeRecord = new HashMap<>();

    public static String createCode(String phoneNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        codeRecord.put(phoneNumber, stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static boolean checkCode(String phoneNumber, String code) {
        if (codeRecord.containsKey(phoneNumber) && code.equals(codeRecord.get(phoneNumber))) {
            codeRecord.remove(phoneNumber);
            return true;
        }
        return false;
    }
}
