package io.github.lucasduete.sgd.segurancaSigilo.cryptoAlgorithms;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RsaKeyGenerator {

    private static final int KEYSIZE = 4096;

    public static final KeyPair generateKeypair() {

        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }

        keyPairGenerator.initialize(KEYSIZE);
//        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

   public static final int getBlockSize() {
        return (KEYSIZE/8) - 11;
    }

    public static final int getKeySize() {
        return KEYSIZE/8;
    }
}
