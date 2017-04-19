package touristSights;

import Core.FileUpload;
import Core.Interface.Form;
import Core.Root;
import Core.Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

/**
 * Created by gev on 19.02.2017.
 */
public class TouristSightForm implements Serializable, Form {

    private Root root;
    private List<TouristSight> touristSights;
    private TouristSight touristSight;
    private Part file;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<TouristSight> getTouristSights() {
        if(this.touristSights == null){
            this.touristSights = getRoot().getTouristSightDao().getAll();
        }
        return touristSights;
    }

    public void setTouristSights(List<TouristSight> touristSights) {
        this.touristSights = touristSights;
    }

    public TouristSight getTouristSight() {
        return touristSight;
    }

    public void setTouristSight(TouristSight touristSight) {
        this.touristSight = touristSight;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    @Override
    public void save() {
        if (file != null) {
            String imageWay = "";
            try {
                imageWay = Util.getBean("fileUpload", FileUpload.class).upload(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.touristSight.setPhotoWay(imageWay);
        }
        if (this.touristSight.getId() != null) {
            if (file != null) {
                deleteOldFile(this.touristSight.getId());
            }
            if (getRoot().getTouristSightDao().update(this.touristSight)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                facesContext.addMessage(null, facesMessage);
            }
        } else {
            if (getRoot().getTouristSightDao().insert(this.touristSight)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                for (TouristSight touristSight : root.getTouristSightDao().getAll()) {
                    if (touristSight.getName().equals(this.touristSight.getName())) {
                        this.setTouristSight(touristSight);
                        Util.getBean("touristSight", TouristSight.class).setId(touristSight.getId());
                    }
                }
                facesContext.addMessage(null, facesMessage);
            }
        }
        this.touristSights = null;
        this.reloadPage();
    }

    public Integer total(){
        return getRoot().getTouristSightDao().getAll().size() ;
    }

    private void reloadPage(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNew() {
        this.touristSight = new TouristSight();
        this.file = null;
    }

    @Override
    public void delete(Integer id) {
        if (this.getTouristSight().getId() != null) {
            if (this.getTouristSight().getId().equals(id)) {
                this.touristSight = new TouristSight();
            }
        }
        this.touristSight = null;
        if (getRoot().getTouristSightDao().getById(id).getPhotoWay() != null) {
            deleteOldFile(id);
        }
        if (this.getRoot().getTouristSightDao().delete(id)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
        }
    }

    private boolean deleteOldFile(Integer id) {
        Properties prop = new Properties();
        String url = "";
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
            url = prop.getProperty("fileUploadUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        TouristSight oldTour = getRoot().getTouristSightDao().getById(id);
        if (oldTour.getPhotoWay() == null) {
            return true;
        }
        File file = new File(url + oldTour.getPhotoWay());
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void edit(Integer id) {
        this.setTouristSight(getRoot().getTouristSightDao().getById(id));
        Util.getBean("touristSight", TouristSight.class).setId(touristSight.getId());
    }
}
