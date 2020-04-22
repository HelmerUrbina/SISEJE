package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface PeriodosDAO {

    public List getListaPeriodos();

    public BeanComun getPeriodo(BeanComun objBeanComun);

    public int iduPeriodos(BeanComun objBeanComun, String usuario);

}
