package Core;

import Core.Models.Month;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/26/2017.
 */
public class Util {

    public static <T> T getBean(String beanName, Class<T> className) {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        return (T) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, beanName);
    }

    public static List<Month> getMonths(){
        List<Month> months = new ArrayList<>();
        months.add(new Month(1,"Հունվար",1));
        months.add(new Month(2,"Փետրվար",1));
        months.add(new Month(3,"Մարտ",1));
        months.add(new Month(4,"Ապրիլ",2));
        months.add(new Month(5,"Մայիս",2));
        months.add(new Month(6,"Հունիս",2));
        months.add(new Month(7,"Հուլիս",3));
        months.add(new Month(8,"Օգոստոս",3));
        months.add(new Month(9,"Սեպտեմբեր",3));
        months.add(new Month(10,"Հոկտեմբեր",4));
        months.add(new Month(11,"Նոյեմբեր",4));
        months.add(new Month(12,"Դեկտեմբեր",4));
        return months;

    }
}
