package customValidator;

import Core.Root;
import Core.Util;
import restaurant.Restaurant;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by gev on 20.02.2017.
 */
@FacesValidator("RestaurantNameUniqueValidator")
public class RestaurantNameUniqueValidator implements Validator{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Root root = Util.getBean("root", Root.class);
        Restaurant restaurant = Util.getBean("restaurant", Restaurant.class);
        for (Restaurant item : root.getRestaurantDao().getAll()) {
            if (item.getName().equals(o) && !restaurant.getId().equals(item.getId())) {
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Անվանումը գոյություն ունի");
                throw new ValidatorException(fmsg);
            }
        }
    }
}
