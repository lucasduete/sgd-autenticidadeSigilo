package io.github.lucasduete.sgd.segurancaSigilo.utils;

import java.util.Base64;

public class Base64Management {

    public static String encodeText(byte[] stringByte) {
        return new String(Base64.getEncoder().encodeToString(stringByte));
    }

    public static byte[] decodeText(String stringEncoded) {
        return Base64.getDecoder().decode(stringEncoded);
    }
}
