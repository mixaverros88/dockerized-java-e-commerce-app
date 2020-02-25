package sessionsBeans;

import entities.Photos;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "photosConverter")
public class PhotosConverter implements Converter {

    final static Logger logger = Logger.getLogger(PhotosConverter.class);
    @EJB
    private UploadImageFacade uploadImageFacade;

    public PhotosConverter() { }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        return uploadImageFacade.returnOnePhoto(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = (value instanceof Photos) ? ((Photos) value).getPhotosid().toString() : null;
        System.out.println(result);
        return result;
    }
}
