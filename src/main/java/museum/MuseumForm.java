package museum;

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
 * Created by gev on 24.03.2017.
 */
public class MuseumForm implements Serializable, Form {
    private Root root;
    private List<Museum> museums;
    private Museum museum;
    private Part file;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<Museum> getMuseums() {
        if (this.museums == null) {
            this.museums = getRoot().getMuseumDao().getAll();
        }
        return museums;
    }

    public void setMuseums(List<Museum> museums) {
        this.museums = museums;
    }

    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void edit(Integer id) {
        this.setMuseum(getRoot().getMuseumDao().getById(id));
        Util.getBean("museum", Museum.class).setId(museum.getId());
    }

    public void addNew() {
        this.museum = new Museum();
        this.file = null;
    }

    public void delete(Integer id) {
        if (this.getMuseum().getId() != null) {
            if (this.getMuseum().getId().equals(id)) {
                this.museum = new Museum();
            }
        }
        this.museums = null;
        if (getRoot().getMuseumDao().getById(id).getPhotoWay() != null) {
            deleteOldFile(id);
        }
        if (this.getRoot().getMuseumDao().delete(id)) {
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
            this.museum.setPhotoWay(imageWay);
        }

        if (this.museum.getId() != null) {
            if (file != null) {
                deleteOldFile(this.museum.getId());
            }
            if (getRoot().getMuseumDao().update(this.museum)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                facesContext.addMessage(null, facesMessage);
            }
        } else {
            if (getRoot().getMuseumDao().insert(this.museum)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                for (Museum museum : root.getMuseumDao().getAll()) {
                    if (museum.getName().equals(this.museum.getName())) {
                        this.setMuseum(museum);
                        Util.getBean("museum", Museum.class).setId(museum.getId());
                    }
                }
                facesContext.addMessage(null, facesMessage);
            }
        }
        this.museums = null;
        this.reloadPage();
    }

    public Integer total(){
        return getRoot().getMuseumDao().getAll().size() ;
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
        Museum oldRest = getRoot().getMuseumDao().getById(id);
        if (oldRest.getPhotoWay() == null) {
            return true;
        }
        File file = new File(url + oldRest.getPhotoWay());
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
