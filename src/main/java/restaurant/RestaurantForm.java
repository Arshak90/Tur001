package restaurant;

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
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by gev on 19.02.2017.
 */
public class RestaurantForm implements Serializable, Form {
    private Root root;
    private List<Restaurant> restaurants;
    private Restaurant restaurant;
    private Part file;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<Restaurant> getRestaurants() {
        if (this.restaurants == null) {
            this.restaurants = getRoot().getRestaurantDao().getAll();
        }
        return restaurants.stream().sorted(Comparator.comparing(Restaurant::getRate).reversed()).collect(Collectors.toList());
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void edit(Integer id) {
        this.setRestaurant(getRoot().getRestaurantDao().getById(id));
        Util.getBean("restaurant", Restaurant.class).setId(restaurant.getId());
    }

    public void addNew() {
        this.restaurant = new Restaurant();
        this.file = null;
    }

    public void delete(Integer id) {
        if (this.getRestaurant().getId() != null) {
            if (this.getRestaurant().getId().equals(id)) {
                this.restaurant = new Restaurant();
            }
        }
        this.restaurants = null;
        if (getRoot().getRestaurantDao().getById(id).getPhotoWay() != null) {
            deleteOldFile(id);
        }
        if (this.getRoot().getRestaurantDao().delete(id)) {
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
            this.restaurant.setPhotoWay(imageWay);
        }

        if (this.restaurant.getId() != null) {
            if (file != null) {
                deleteOldFile(this.restaurant.getId());
            }
            if (getRoot().getRestaurantDao().update(this.restaurant)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                facesContext.addMessage(null, facesMessage);
            }
        } else {
            if (getRoot().getRestaurantDao().insert(this.restaurant)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                for (Restaurant restaurant : root.getRestaurantDao().getAll()) {
                    if (restaurant.getName().equals(this.restaurant.getName())) {
                        this.setRestaurant(restaurant);
                        Util.getBean("restaurant", Restaurant.class).setId(restaurant.getId());
                    }
                }
                facesContext.addMessage(null, facesMessage);
            }
        }
        this.restaurants = null;
        this.reloadPage();
    }

    public Integer total(){
        return getRoot().getRestaurantDao().getAll().size() ;
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
        Restaurant oldRest = getRoot().getRestaurantDao().getById(id);
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
