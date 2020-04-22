package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface TipoDocumentosDAO {

    public List getListaTipoDocumentos();

    public BeanComun getTipoDocumento(BeanComun objBeanComun);

    public int iduTipoDocumento(BeanComun objBeanComun, String usuario);

}
