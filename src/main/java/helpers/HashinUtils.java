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

    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HashinUtils.class);

    public String hashPass(String password) {
        if(logger.isDebugEnabled()){ logger.debug("Hash Pass"); }
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean checkHash(String userPassword, String dbPassword) {
        if(logger.isDebugEnabled()){ logger.debug("check Hash"); }
        return BCrypt.checkpw(userPassword, dbPassword);
    }
}
