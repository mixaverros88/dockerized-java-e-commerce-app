/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Photos;
import helpers.DateTime;
import helpers.SlugName;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import sessionsBeans.UploadImageFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class UploadImageController implements Serializable {

    private List<Photos> photos;
    
    @NotNull(message = "Επιλέξτε ένα αρχείο")  
    private String filename;

    private String path ="C:/Users/user/Documents/NetBeansProjects/PrimeFaces/web/resources/images/";
    
    @NotNull(message = "At can't be null")      
    private Part file;    
    
    @EJB
    UploadImageFacade uploadImageFacade;
    
    public String doUpload() throws IOException {
   


        filename =getFilename(file);
        if(filename==null){
            return "";
        }
        //if the filenameis allready exists
        File f = new File(path+filename);
        if(f.exists() && !f.isDirectory()) { 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το αρχείο υπάρχει. Αλλάξτε το όνομα του αρχείου"));
            return "";
        }else{
            file.write(path+getFilename(file));
            Photos ph = new Photos();
            ph.setUrl(filename);
            String mimeType = new MimetypesFileTypeMap().getContentType(f);
            if (!"image/png".equals(mimeType) && !"image/jpg".equals(mimeType) && !"image/jpeg".equals(mimeType)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Μη αποδεχτή μορφή αρχείου. Αποδεκτές Μορφές Αρχείων: PNG JPG JPEG"));
                return "";
            }
            ph.setMIMEtype(mimeType);
            uploadImageFacade.insertFilePathToDB(ph);
        }

       
        return "/photosAll.xhtml?faces-redirect=true";
    }
    
    private static  String getFilename(Part part){
        
        for(String cd : part.getHeader("content-disposition").split(";")){
            if(cd.trim().startsWith("filename")){
                String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
                filename= SlugName.convertToSlugName(filename);
                return DateTime.getDateTime()+filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
            }
        }
        return null;
    }
    
    public void deleteImage(String filename, int photosid){
      	try{
            uploadImageFacade.deletePhotoFromDatabase(photosid);
            File fd = new File(path+filename);
        	
            if(fd.delete()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το αρχείο "+file.getName() + " διαγράφτηκε επιτυχώς!"));
            }else{
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το αρχείο "+file.getName() + " ΔΕΝ διαγράφτηκε επιτυχώς!"));
            }
            
    	}catch(Exception e){
            e.printStackTrace();	
        }

    }
    
    public List<Photos> getPhotos() {
        return photos=uploadImageFacade.getAllfiles();
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
}
