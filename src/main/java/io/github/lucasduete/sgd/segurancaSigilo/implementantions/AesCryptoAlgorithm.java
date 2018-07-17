package io.github.lucasduete.sgd.segurancaSigilo.implementantions;

import io.github.lucasduete.sgd.segurancaSigilo.CryptoAlgorithm;

import javax.crypto.Cipher;
import java.security.Key;

public class AesCryptoAlgorithm implements CryptoAlgorithm {

    public byte[] crypt(byte[] plainText, Key key) throws Exception {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cifredText, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(cifredText);
    }
}
