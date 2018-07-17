package io.github.lucasduete.sgd.segurancaSigilo.controllers;

import io.github.lucasduete.sgd.segurancaSigilo.CryptoAlgorithm;
import io.github.lucasduete.sgd.segurancaSigilo.cryptoAlgorithms.AesKeyGenerator;
import io.github.lucasduete.sgd.segurancaSigilo.implementantions.AesCryptoAlgorithm;
import io.github.lucasduete.sgd.segurancaSigilo.implementantions.RsaCryptoAlgorithm;

import java.security.Key;

public class CriptoController {

    private final Key AES_KEY;

    public CriptoController() {
        this.AES_KEY = AesKeyGenerator.generateKey();
    }

    public byte[] encrypt(byte[] plainText, Key key) throws Exception {

        System.out.println("plain text: " + plainText.length);

        CryptoAlgorithm cryptoAlgorithm;

        cryptoAlgorithm = new AesCryptoAlgorithm();
        byte[] desCryptText = cryptoAlgorithm.crypt(plainText, AES_KEY);
        System.out.println("passou pelo AES: " + desCryptText.length);


        cryptoAlgorithm = new RsaCryptoAlgorithm();
        byte[] crypt = cryptoAlgorithm.crypt(desCryptText, key);

        System.out.println("Passou pelo RSA: " + crypt.length);
        return crypt;
    }

    public byte[] decrypt(byte[] cifredText, Key key) throws Exception {

        CryptoAlgorithm cryptoAlgorithm;

        cryptoAlgorithm = new RsaCryptoAlgorithm();
        byte[] rsaDecyptText = cryptoAlgorithm.decrypt(cifredText, key);

        cryptoAlgorithm = new AesCryptoAlgorithm();
        return cryptoAlgorithm.decrypt(rsaDecyptText, AES_KEY);
    }
}
