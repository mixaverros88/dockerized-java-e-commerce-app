/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import helpers.MailSender;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;





/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class ContactManage implements Serializable{
//    @Size(min=10, max=10, message="O αριθμός τηλεφώνου θα πρέπει να είναι 10 ψηφία")
    @NotNull(message = "Συμπληρώστε το ονοματεπώνυμο σας")  
    private String name;
    @NotNull(message = "Συμπληρώστε τον αριθμό τηλεφώνου")  
    private String phoneNumber;
    @NotNull(message = "Συμπληρώστε το email σας")  
    private String email;
    @NotNull(message = "Συμπληρώστε το μηνυμα σας")  
    private String message;

    public String sentContactEmail() {

        if(MailSender.send("mverros@kathimerini.gr","Φόρμα Επικοινωνίας","<h3>Στοιχεία Αποστολέα</h3><table><tr><td>Email</td><td>"+getEmail()+"</td></tr><tr><td>Όνομα</td><td>"+getName()+"</td></tr><tr><td>Τηλέφωνο</td><td>"+getPhoneNumber()+"</td></tr><tr><td>Μήνυμα</td><td>"+getMessage()+"</td></tr></table>")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("To email σας εστάλει επιτυχώς. Σύντομα θα επικοινωνήσουμε μαζί σας"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("To email σας ΔΕΝ εστάλει επιτυχώς. Παρακαλώ προσπαθήστε ξανά"));
        }
        
        
       return "";
    }   
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
