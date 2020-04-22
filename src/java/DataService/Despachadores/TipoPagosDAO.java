package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface TipoPagosDAO {

    public List getTipoPagos();

    public BeanComun getTIpoPagos(BeanComun objBeanComun);

    public int iduTipoPagos(BeanComun objBeanComun, String usuario);

}
