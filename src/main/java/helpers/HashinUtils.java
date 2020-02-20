/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import org.mindrot.jbcrypt.BCrypt;

/**
 * <h1>Hash Utils</h1>
 *
 * @author Mike-George Verros
 * @version 1.0
 */
public class HashinUtils {
    public String hashPass(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean checkHash(String userPassword, String dbPassword) {
        return BCrypt.checkpw(userPassword, dbPassword);
    }
}
