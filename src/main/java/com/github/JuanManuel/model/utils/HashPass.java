package com.github.JuanManuel.model.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPass {

    public static String hashPassword(String password) {
        try {
            // Crea un objeto MessageDigest para SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Aplica el hash al password y obtiene los bytes
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            // Convierte los bytes en una cadena hexadecimal
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Lanza una excepción si no se encuentra el algoritmo
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
