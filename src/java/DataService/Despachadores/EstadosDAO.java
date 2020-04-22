package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface EstadosDAO {

    public List getEstados();

    public BeanComun getEstado(BeanComun objBeanComun);

    public int iduEstado(BeanComun objBeanComun, String usuario);

}
