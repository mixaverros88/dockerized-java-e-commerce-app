/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * @author user
 */
@Named(value = "userEditManage")
@SessionScoped
public class userEditManage implements Serializable {

    /**
     * Creates a new instance of userEditManage
     */
    public userEditManage() {
    }

}
