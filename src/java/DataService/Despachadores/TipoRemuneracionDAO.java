package DataService.Despachadores;

import BusinessServices.Beans.BeanTipoRemuneracion;
import java.util.List;

public interface TipoRemuneracionDAO {

    public List getListaTipoRemuneracion(String tipoRemuneracion);

    public BeanTipoRemuneracion getTipoRemuneracion(BeanTipoRemuneracion objBeanTipoRemuneracion);

    public int iduTipoRemuneracion(BeanTipoRemuneracion objBeanTipoRemuneracion, String usuario);

}
