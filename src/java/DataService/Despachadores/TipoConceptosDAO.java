package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface TipoConceptosDAO {

    public List getListaTipoConceptos();

    public BeanComun getTipoConcepto(BeanComun objBeanComun);

    public int iduTipoConceptos(BeanComun objBeanComun, String usuario);

}
