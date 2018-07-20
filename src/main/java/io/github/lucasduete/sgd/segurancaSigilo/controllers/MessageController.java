package io.github.lucasduete.sgd.segurancaSigilo.controllers;

import io.github.lucasduete.sgd.segurancaSigilo.entities.User;

import java.util.Base64;

import static io.github.lucasduete.sgd.segurancaSigilo.utils.Base64Management.decodeText;
import static io.github.lucasduete.sgd.segurancaSigilo.utils.Base64Management.encodeText;

public class MessageController {

    private CriptoController criptoController;

    public MessageController() {
        this.criptoController = new CriptoController();
    }

    public boolean sendMessage(User sender, User receiver, String message) {

        try {
            byte[] temp1 = criptoController.encrypt(message.getBytes("UTF8"), sender.getPrivateKey());
            byte[] cifred = criptoController.encrypt(temp1, receiver.getPublicKey());

            receiver.receiveMessage(sender, encodeText(cifred));

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean receiveMessage(User sender, User receiver, String message) {

        System.out.println("Chegou mensagem");

        try {
            byte[] temp1 = criptoController.decrypt(decodeText(message), receiver.getPrivateKey());
            byte[] plainText = criptoController.decrypt(temp1, sender.getPublicKey());

            System.out.println(new String(plainText));

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

}
