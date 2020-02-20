/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Custvend;
import entities.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sessionsBeans.UserAllFacade;

/**
 * @author user
 */
@ManagedBean
@RequestScoped
public class UserAllManage implements Serializable {

    private List<Custvend> users;
    private int deleteMessage;
    private int id;


    @EJB
    UserAllFacade userAllFacade;


//    @PostConstruct
//    public void init() {
//        users = userAllFacade.getAllUsers();
//    }


    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String editUser() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String fiel_at = params.get("action");

        User u = userAllFacade.searchWithat(fiel_at);
        User u2 = userAllFacade.searchWithat(fiel_at);
        System.out.println("USER@" + u2);
        //u.setAt("12");

        sessionMap.put("editCategory", u);
        return "/userEdit.xhtml?faces-redirect=true";
    }

    public List<Custvend> getAllUserData() {
        return users = userAllFacade.getAllUsers();
    }

    public void deleteUser(int at) {
        deleteMessage = userAllFacade.deleteUser(at);
        getAllUserData();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(int deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public List<Custvend> getUsers() {
        return users;
    }

    public void setUsers(List<Custvend> users) {
        this.users = users;
    }

}
