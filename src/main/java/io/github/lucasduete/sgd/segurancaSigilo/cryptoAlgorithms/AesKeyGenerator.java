package io.github.lucasduete.sgd.segurancaSigilo.cryptoAlgorithms;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AesKeyGenerator {

    private static Key key = null;

    public static final Key generateKey() {
        if (key == null) {
            KeyGenerator keyGenerator = null;
            try {
                keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(256);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
                return null;
            }

            byte[] tempKey = keyGenerator.generateKey().getEncoded();
            key = new SecretKeySpec(tempKey, "AES");
        }

        return key;
    }

}
