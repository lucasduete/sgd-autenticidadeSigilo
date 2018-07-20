package io.github.lucasduete.sgd.segurancaSigilo.entities;

import io.github.lucasduete.sgd.segurancaSigilo.controllers.MessageController;
import io.github.lucasduete.sgd.segurancaSigilo.cryptoAlgorithms.RsaKeyGenerator;

import java.security.Key;
import java.security.KeyPair;

public class User {

    private String nome;
    private KeyPair keyPair;

    {
        keyPair = RsaKeyGenerator.generateKeypair();
    }

    public User() {

    }

    public User(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Key getPublicKey() {
        return this.keyPair.getPublic();
    }

    public Key getPrivateKey() {
        return this.keyPair.getPrivate();
    }

    public void receiveMessage(User sender, String message) {
        String mensagem = new MessageController().receiveMessage(sender, this, message);

        System.out.println(String.format("O usuário %s recebeu: \n%s", this.nome, mensagem));
    }

}
