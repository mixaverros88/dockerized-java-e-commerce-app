package manage;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;

@ManagedBean
@RequestScoped
public class CreateUser {

    final static Logger logger = Logger.getLogger(CreateUser.class);

    @NotNull(message = "Name can't be null")
    private String name;

    @PostConstruct
    void init() {
        if(logger.isDebugEnabled()){ logger.debug("Init Create A User"); }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 
