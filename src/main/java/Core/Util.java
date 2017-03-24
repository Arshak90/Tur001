package Core;

import Core.Models.Month;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<Integer, Double> initMap(){
        Map<Integer, Double> doubleMap = new HashMap<>();
        doubleMap.put(1,0.0);
        doubleMap.put(2,0.0);
        doubleMap.put(3,0.0);
        doubleMap.put(4,0.0);
        doubleMap.put(5,0.0);
        doubleMap.put(6,0.0);
        doubleMap.put(7,0.0);
        doubleMap.put(8,0.0);
        doubleMap.put(9,0.0);
        doubleMap.put(10,0.0);
        doubleMap.put(11,0.0);
        doubleMap.put(12,0.0);
        return doubleMap;
    }

    public static Map<Integer, Integer> initMapForTurCount(){
        Map<Integer, Integer> doubleMap = new HashMap<>();
        doubleMap.put(1,0);
        doubleMap.put(2,0);
        doubleMap.put(3,0);
        doubleMap.put(4,0);
        doubleMap.put(5,0);
        doubleMap.put(6,0);
        doubleMap.put(7,0);
        doubleMap.put(8,0);
        doubleMap.put(9,0);
        doubleMap.put(10,0);
        doubleMap.put(11,0);
        doubleMap.put(12,0);
        return doubleMap;
    }
}
