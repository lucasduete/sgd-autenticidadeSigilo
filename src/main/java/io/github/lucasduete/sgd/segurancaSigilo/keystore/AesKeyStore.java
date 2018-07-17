package br.edu.ifpb.sgd.cryptoAlgorithm.keystore;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;

public class AesKeyStore {

    private static final String KEYSTORAGE_NAME = "keystorage.aes";
    private static final String PASSWORD = "password";
    private static final String KEY_ALIAS = "main";

    public void generateKeyStore() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);

        byte[] key = keyGenerator.generateKey().getEncoded();
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        KeyStore.SecretKeyEntry keyEntry = new KeyStore.SecretKeyEntry(keySpec);

        keyStore.load(null, PASSWORD.toCharArray());

        keyStore.setEntry(KEY_ALIAS, keyEntry, new KeyStore.PasswordProtection(PASSWORD.toCharArray()));

        try(OutputStream keyStoreData = new FileOutputStream(KEYSTORAGE_NAME)){
            keyStore.store(keyStoreData, PASSWORD.toCharArray());
        }
    }

    public Key getKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        try(InputStream keyStoreData = new FileInputStream(KEYSTORAGE_NAME)){
            keyStore.load(keyStoreData, PASSWORD.toCharArray());
        }

//        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(KEY_ALIAS, new KeyStore.PasswordProtection(PASSWORD.toCharArray()));
        keyStore.getEntry(KEY_ALIAS, new KeyStore.PasswordProtection(PASSWORD.toCharArray())).getAttributes().forEach(System.out::println);

        return null;
    }
}
