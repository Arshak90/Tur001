package customValidator;

import Core.Root;
import Core.Util;
import tourOperator.TourOperator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by gev on 20.02.2017.
 */
@FacesValidator("TourOperatorNameUniqueValidator")
public class TourOperatorNameUniqueValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Root root = Util.getBean("root", Root.class);
        TourOperator tourOperator = Util.getBean("tourOperator", TourOperator.class);
        for (TourOperator item : root.getTourOperatorDao().getAll()) {
            if (item.getName().equals(o) && !tourOperator.getId().equals(item.getId())) {
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Անվանումը գոյություն ունի");
                throw new ValidatorException(fmsg);
            }
        }
    }
}
