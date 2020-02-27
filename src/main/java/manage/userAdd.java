package manage;

import entities.Role;
import entities.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import org.apache.log4j.Logger;
import sessionsBeans.UserAddFacade;

@ManagedBean
@RequestScoped
public class userAdd implements Serializable {

    final static Logger logger = Logger.getLogger(userAdd.class);

    @NotNull(message = "At can't be null")
    private int at;
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "Surname can't be null")
    private String surname;
    @NotNull(message = "Username can't be null")
    private String username;
    @NotNull(message = "Password can't be null")
    private String password;
    @NotNull(message = "Role can't be null")
    private Role rr;
    private List<Role> roles;
    private User user;
    private String passwordCheck;

    //kaloume sessions beans
    @EJB
    UserAddFacade userAddFacade;

    //θα είναι η πρώτη μέθοδος που θα τρέξει όταν παέι να δημιουργιθεί το bean
    @PostConstruct
    void init() {
        if (logger.isDebugEnabled()) {  logger.debug("Init Add User"); }
        roles = userAddFacade.findRole();
        user = userAddFacade.getUserToEdit(1);
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public void insertUser() {
        User u;
        boolean mr = false;
        if (password.equals(passwordCheck)) {
            u = new User();
            u.setAt(Integer.toString(at));
            u.setName(name);
            u.setSurname(surname);
            u.setUsername(username);
            u.setRole(rr);
            u.setPass(password);
            //u.setPass(password);
            System.out.println(u.toString());
            mr = userAddFacade.insertUserToDB(u);
        }
        if (mr) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DATA OK"));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRr() {
        return rr;
    }

    public void setRr(Role rr) {
        this.rr = rr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String pass;

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

}
