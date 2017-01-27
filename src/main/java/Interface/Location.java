package Interface;

import java.util.List;

/**
 * Created by arshak.askaryan on 2/5/2016.
 */
public abstract class  Location {
    public abstract String getName();
    public abstract Integer getId();
    public abstract Integer getParentId();
    public abstract List<Location> getChilds();

}
