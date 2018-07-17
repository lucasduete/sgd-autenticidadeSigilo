package io.github.lucasduete.sgd.segurancaSigilo;

import io.github.lucasduete.sgd.segurancaSigilo.controllers.MessageController;
import io.github.lucasduete.sgd.segurancaSigilo.entities.User;

public class Main {

    public static void main(String[] args) throws Exception {
        User user1 = new User("Lucas");
        User user2 = new User("Mayara");

        new MessageController().sendMessage(user1, user2, "a");
    }
}
