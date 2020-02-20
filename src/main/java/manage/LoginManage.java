/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Custvend;
import entities.User;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import sessionsBeans.LoginFacade;
import sessionsBeans.ProductFacade;


/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class LoginManage implements Serializable {
    @NotNull(message = "Username can't be null")
    private String username;
    @NotNull(message = "Password can't be null")
    private String pass;
    private String name;

    User u;

    @EJB
    LoginFacade loginFacade;

    @EJB
    ProductFacade productFacade;

    public String valid() throws NoSuchAlgorithmException {
        Custvend u = loginFacade.getUser(username, pass);

        if (u != null) {

            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", u.getRoleid().getRoleid());
            session.setAttribute("name", u.getFname());
            session.setAttribute("Custvend", u);


            if (u.getRoleid().getRoleid() == 2) {
                return "index.xhtml?faces-redirect=true";
            } else if (u.getRoleid().getRoleid() == 1 || u.getRoleid().getRoleid() == 3) {
                return "main.xhtml?faces-redirect=true";
            } else {
                return "login.xhtml?faces-redirect=true";
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or Password is invalid or the user is not active"));
            return "";
        }


    }

    public String getNam() {
        return SessionUtils.getName();
    }

    public Custvend getCustvend() {
        return SessionUtils.getCustvend();
    }

    public String getSes() {
        return SessionUtils.getUserName();
    }

    public String getRole() {
        return SessionUtils.getRole();
    }

    public String doLogout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();

        return "login.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
