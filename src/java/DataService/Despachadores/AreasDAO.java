package DataService.Despachadores;

import BusinessServices.Beans.BeanArea;
import java.util.List;

public interface AreasDAO {

    public List getListaAreas(String codigo);

    public BeanArea getArea(BeanArea objBeanComun);

    public int iduAreas(BeanArea objBeanArea, String usuario);

}
