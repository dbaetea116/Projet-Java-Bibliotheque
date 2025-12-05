package com.example.javaproject;

import org.mindrot.jbcrypt.BCrypt;

public class HashedPasswordBCrypt {

    public static void main(String[] args) {
        String passwordHash = "jaso";

        String passwordHashed = BCrypt.hashpw(passwordHash, BCrypt.gensalt());

        System.out.println("Mot de passe en clair : " + passwordHash);
        System.out.println("-----------------------------------------------------");
        System.out.println("Le Hachage BCrypt valide à insérer dans la DB est :");
        System.out.println(passwordHashed);
        System.out.println("-----------------------------------------------------");

        boolean match = BCrypt.checkpw(passwordHash, passwordHashed);
        System.out.println("Vérification (doit être true) : " + match);
    }
}