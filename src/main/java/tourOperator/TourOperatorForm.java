package tourOperator;

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
public class TourOperatorForm implements Serializable, Form {

    private Root root;
    private List<TourOperator> tourOperators;
    private TourOperator tourOperator;
    private Part file;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<TourOperator> getTourOperators() {
        if(this.tourOperators == null){
            this.tourOperators = getRoot().getTourOperatorDao().getAll();
        }
        return tourOperators;
    }

    public void setTourOperators(List<TourOperator> tourOperators) {
        this.tourOperators = tourOperators;
    }

    public TourOperator getTourOperator() {
        return tourOperator;
    }

    public void setTourOperator(TourOperator tourOperator) {
        this.tourOperator = tourOperator;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void edit(Integer id) {
        this.setTourOperator(getRoot().getTourOperatorDao().getById(id));
        Util.getBean("tourOperator", TourOperator.class).setId(tourOperator.getId());
    }

    public void addNew() {
        this.tourOperator = new TourOperator();
        this.file = null;
    }

    public void delete(Integer id) {
        if (this.getTourOperator().getId() != null) {
            if (this.getTourOperator().getId().equals(id)) {
                this.tourOperator = new TourOperator();
            }
        }
        this.tourOperators = null;
        if (getRoot().getTourOperatorDao().getById(id).getPhotoWay() != null) {
            deleteOldFile(id);
        }
        if (this.getRoot().getTourOperatorDao().delete(id)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
        }
    }

    public void save() {
        if (file != null) {
            String imageWay = "";
            try {
                imageWay = Util.getBean("fileUpload", FileUpload.class).upload(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.tourOperator.setPhotoWay(imageWay);
        }

        if (this.tourOperator.getId() != null) {
            if (file != null) {
                deleteOldFile(this.tourOperator.getId());
            }
            if (getRoot().getTourOperatorDao().update(this.tourOperator)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                facesContext.addMessage(null, facesMessage);
            }
        } else {
            if (getRoot().getTourOperatorDao().insert(this.tourOperator)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                for (TourOperator tourOperator : root.getTourOperatorDao().getAll()) {
                    if (tourOperator.getName().equals(this.tourOperator.getName())) {
                        this.setTourOperator(tourOperator);
                        Util.getBean("tourOperator", TourOperator.class).setId(tourOperator.getId());
                    }
                }
                facesContext.addMessage(null, facesMessage);
            }
        }
        this.tourOperators = null;
        this.reloadPage();
    }

    public Integer total(){
        return getRoot().getTourOperatorDao().getAll().size() ;
    }

    private void reloadPage(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
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
        TourOperator oldTour = getRoot().getTourOperatorDao().getById(id);
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
}
