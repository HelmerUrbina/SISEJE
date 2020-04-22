package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface SeccionesDAO {

    public List getListaSecciones();

    public BeanComun getSeccion(BeanComun objBeanComun);

    public int iduSecciones(BeanComun objBeanComun, String usuario);

}
