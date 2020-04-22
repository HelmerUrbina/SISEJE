package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface BancosDAO {

    public List getBancos();

    public BeanComun getBanco(BeanComun objBeanComun);

    public int iduBancos(BeanComun objBeanComun, String usuario);

}
