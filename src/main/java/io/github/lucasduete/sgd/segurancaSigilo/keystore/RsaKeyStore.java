package br.edu.ifpb.sgd.cryptoAlgorithm.keystore;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;

public class RsaKeyStore {

    private static final String PASSWORD = "password";
    private static final String PUBLICKEY_ALIAS = "public-key";
    private static final String PRIVATEKEY_ALIAS = "private-key";
    private static final String KEYSTORAGE_NAME = "keystorage.rsa";

    public void generateKeyStore() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        keyStore.load(null, PASSWORD.toCharArray());

        KeyStore.SecretKeyEntry publicKeyEntry = new KeyStore.SecretKeyEntry((SecretKey) keyPair.getPublic());
        KeyStore.SecretKeyEntry privateKeyEntry = new KeyStore.SecretKeyEntry((SecretKey) keyPair.getPrivate());

        keyStore.setEntry(PUBLICKEY_ALIAS, publicKeyEntry, new KeyStore.PasswordProtection(PASSWORD.toCharArray()));
        keyStore.setEntry(PRIVATEKEY_ALIAS, privateKeyEntry, new KeyStore.PasswordProtection(PASSWORD.toCharArray()));



        try(OutputStream keyStoreData = new FileOutputStream(KEYSTORAGE_NAME)){
            keyStore.store(keyStoreData, PASSWORD.toCharArray());
        }
    }

    public Key getPublicKey() throws Exception {
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) getKeyStore().getEntry(PUBLICKEY_ALIAS, new KeyStore.PasswordProtection(PASSWORD.toCharArray()));

        return privateKeyEntry.getPrivateKey();
    }

    public Key getPrivateKey() throws Exception {
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) getKeyStore().getEntry(PRIVATEKEY_ALIAS, new KeyStore.PasswordProtection(PASSWORD.toCharArray()));

        return privateKeyEntry.getPrivateKey();
    }

    private KeyStore getKeyStore() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        try(InputStream keyStoreData = new FileInputStream(KEYSTORAGE_NAME)){
            keyStore.load(keyStoreData, PASSWORD.toCharArray());
        }

        return keyStore;
    }
}
