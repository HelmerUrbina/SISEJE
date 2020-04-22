package DataService.Despachadores;

import BusinessServices.Beans.BeanComun;
import java.util.List;

public interface TipoJuzgadosDAO {

    public List getListaTipoJuzgados();

    public BeanComun getTipoJuzgado(BeanComun objBeanComun);

    public int iduTipoJuzgados(BeanComun objBeanComun, String usuario);

}
