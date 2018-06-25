/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Subscribe;
import helpers.DateTime;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import sessionsBeans.SubscribeFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class SubscribeManage implements Serializable{
    
    @NotNull(message = "Email can't be null")  
    private String email;
   
    
    
    @EJB
    SubscribeFacade subscribeFacade;
    
    public String addSubscriber(){
         Subscribe subscribe = new Subscribe();
        subscribe.setEmail(email);
        subscribe.setInsdate(DateTime.getNowDateTime());
        if( subscribeFacade.insertSubscriberToDB(subscribe) ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Εγγραφήκατε επιτυχώς στο newsletter. Σύντομα θα λάβετε νέα από εμάς"));
        }
        return "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
   
}
