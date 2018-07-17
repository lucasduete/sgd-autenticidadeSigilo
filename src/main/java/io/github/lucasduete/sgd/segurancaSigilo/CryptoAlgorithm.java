package io.github.lucasduete.sgd.segurancaSigilo;

import java.security.Key;

public interface CryptoAlgorithm {

    public byte[] crypt(byte[] plainText, Key key) throws Exception;
    public byte[] decrypt(byte[] cifredText, Key key) throws Exception;

}
