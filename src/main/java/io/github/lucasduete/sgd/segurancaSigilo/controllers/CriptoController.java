package io.github.lucasduete.sgd.segurancaSigilo.controllers;

import io.github.lucasduete.sgd.segurancaSigilo.CryptoAlgorithm;
import io.github.lucasduete.sgd.segurancaSigilo.cryptoAlgorithms.AesKeyGenerator;
import io.github.lucasduete.sgd.segurancaSigilo.cryptoAlgorithms.RsaKeyGenerator;
import io.github.lucasduete.sgd.segurancaSigilo.implementantions.AesCryptoAlgorithm;
import io.github.lucasduete.sgd.segurancaSigilo.implementantions.RsaCryptoAlgorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.util.Arrays;

public class CriptoController {

    private final Key AES_KEY;
    private final int KEY_SIZE;
    private final int BLOCK_SIZE;

    public CriptoController() {
        this.AES_KEY = AesKeyGenerator.generateKey();
        this.KEY_SIZE = RsaKeyGenerator.getKeySize();
        this.BLOCK_SIZE = RsaKeyGenerator.getBlockSize();
    }

    public byte[] encrypt(byte[] plainText, Key key) throws Exception {

        CryptoAlgorithm cryptoAlgorithm;

        cryptoAlgorithm = new RsaCryptoAlgorithm();
        byte[] crypt;

        if (plainText.length <= BLOCK_SIZE)
            crypt = cryptoAlgorithm.crypt(plainText, key);
        else {
            byte[] part1 = cryptoAlgorithm.crypt(Arrays.copyOfRange(plainText, 0, BLOCK_SIZE), key);
            byte[] part2 = cryptoAlgorithm.crypt(Arrays.copyOfRange(plainText, BLOCK_SIZE, plainText.length), key);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(part1);
            outputStream.write(part2);

            crypt = outputStream.toByteArray();
        }

        System.out.println("Passou pelo RSA: " + crypt.length);
        return crypt;
    }

    public byte[] decrypt(byte[] cifredText, Key key) throws Exception {

        CryptoAlgorithm cryptoAlgorithm = new RsaCryptoAlgorithm();

        byte[] decrypt;

        if (cifredText.length <= KEY_SIZE)
            decrypt = cryptoAlgorithm.decrypt(cifredText, key);
        else {
            byte[] part1 = cryptoAlgorithm.decrypt(Arrays.copyOfRange(cifredText, 0, KEY_SIZE), key);
            byte[] part2 = cryptoAlgorithm.decrypt(Arrays.copyOfRange(cifredText, KEY_SIZE, cifredText.length), key);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(part1);
            outputStream.write(part2);

            decrypt = outputStream.toByteArray();
        }

        return decrypt;
    }
}
