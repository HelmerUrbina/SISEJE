package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface PrioridadDAO {

    public List getListaPrioridad();

    public BeanComun getPrioridad(BeanComun objBeanComun);

    public int iduPrioridad(BeanComun objBeanComun, String usuario);

}
