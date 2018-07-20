package io.github.lucasduete.sgd.segurancaSigilo;

import io.github.lucasduete.sgd.segurancaSigilo.controllers.MessageController;
import io.github.lucasduete.sgd.segurancaSigilo.entities.User;

public class Main {

    public static void main(String[] args) throws Exception {
        User user1 = new User("Marcos");
        User user2 = new User("Maria");

        new MessageController().sendMessage(user1, user2, "Hello World");
    }
}
