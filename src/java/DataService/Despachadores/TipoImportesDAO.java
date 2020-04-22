
package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface TipoImportesDAO {
    
    public List getTipoImportes();

    public BeanComun getEstado(BeanComun objBeanComun);

    public int iduEstado(BeanComun objBeanComun, String usuario);
    
}
