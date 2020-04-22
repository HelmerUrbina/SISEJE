package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface TipoDecretosDAO {

    public List getListaTipoDecretos();

    public BeanComun getTipoDecreto(BeanComun objBeanComun);

    public int iduTipoDecretos(BeanComun objBeanComun, String usuario);

}
