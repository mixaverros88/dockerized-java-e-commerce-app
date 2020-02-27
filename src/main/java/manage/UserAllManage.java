package manage;

import entities.Custvend;
import entities.User;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import sessionsBeans.UserAllFacade;

@ManagedBean
@RequestScoped
public class UserAllManage implements Serializable {

    final static Logger logger = Logger.getLogger(UserAllManage.class);

    private List<Custvend> users;
    private int deleteMessage;
    private int id;

    @EJB
    UserAllFacade userAllFacade;

    @PostConstruct
    public void init() {
        if (logger.isDebugEnabled()) {  logger.debug("Init User Manage"); }
    }

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String editUser() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String fiel_at = params.get("action");

        Custvend u = userAllFacade.searchWithat(fiel_at);

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
