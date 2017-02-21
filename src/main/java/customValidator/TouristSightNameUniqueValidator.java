package customValidator;

import Core.Root;
import Core.Util;
import touristSights.TouristSight;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by gev on 20.02.2017.
 */
@FacesValidator("TouristSightNameUniqueValidator")
public class TouristSightNameUniqueValidator implements Validator{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Root root = Util.getBean("root", Root.class);
        TouristSight tourOperator = Util.getBean("touristSight", TouristSight.class);
        for (TouristSight item : root.getTouristSightDao().getAll()) {
            if (item.getName().equals(o) && !tourOperator.getId().equals(item.getId())) {
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Անվանումը գոյություն ունի");
                throw new ValidatorException(fmsg);
            }
        }
    }
}
