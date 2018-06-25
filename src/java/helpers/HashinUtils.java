/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author user
 */
public class HashinUtils {
    public String hashPass(String password) throws NoSuchAlgorithmException{
        String generatedSecurePasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
       
        return generatedSecurePasswordHash;
    }
    
    public boolean checkHash(String userPassword, String dbPassword){
        return BCrypt.checkpw(userPassword, dbPassword);
    }
}
