/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.mindrot.jbcrypt.BCrypt;









/**
 *
 * @author AdG
 */
public class PasswordUtils {
    // Method to hash a password using BCrypt
    public  String hashPassword(String plainTextPassword) {
        // BCrypt generates a salt and appends it to the hash, so no need to manage salt explicitly
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Method to check if a password matches its BCrypt hash
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        // BCrypt automatically extracts the salt from the hashedPassword
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    } 
}
