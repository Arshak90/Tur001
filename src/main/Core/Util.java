import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 * Created by arshak.askaryan on 1/26/2017.
 */
public class Util {

    public static <T> T getBean(String beanName, Class<T> className) {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        return (T) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, beanName);
    }
}
