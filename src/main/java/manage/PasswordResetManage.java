/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Custvend;
import helpers.MailSender;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import sessionsBeans.PasswordResetFacade;

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class PasswordResetManage implements Serializable {

    @NotNull(message = "Παρακαλώ συμπληρώστε το email σας.")
    private String email;

    @EJB
    PasswordResetFacade passwordResetFacade;

    public String valid() throws NoSuchAlgorithmException {
        Custvend u = passwordResetFacade.validateEmail(email);
        if (u != null) {

            MailSender mailSend = new MailSender();
            MailSender.send(u.getEmail(), "ezikos.gr Αλλαγή Password", "<h3>Το νέο σας password είναι το " + passwordResetFacade.changeUserPass(email, mailSend.generatePassword()) + "</h3><br/> Για να συνδεθείτε πατήστε <a href=\"/java-e-commerce/web/login.xhtml\">εδώ</a>");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ο καινούργιος κωδικός στάθηκε στο email:" + email + " "));

            return "";
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το email:" + email + " δεν βρέθηκε."));

        return "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
