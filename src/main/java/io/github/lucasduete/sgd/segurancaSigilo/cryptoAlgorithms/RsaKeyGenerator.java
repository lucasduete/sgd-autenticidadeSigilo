package io.github.lucasduete.sgd.segurancaSigilo.cryptoAlgorithms;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RsaKeyGenerator {

    public static final KeyPair generateKeypair() {

        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }

        keyPairGenerator.initialize(2048);
//        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }
}
