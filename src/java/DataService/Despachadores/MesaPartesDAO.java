package DataService.Despachadores;

import BusinessServices.Beans.BeanMesaPartes;
import java.util.List;

public interface MesaPartesDAO {

    public List getListaMesaPartes(BeanMesaPartes objBeanMesaParte);

    public List getConsultaMesaPartes(BeanMesaPartes objBeanMesaParte);

    public BeanMesaPartes getMesaPartes(BeanMesaPartes objBeanMesaParte);

    public String getNumeroDocumento(BeanMesaPartes objBnMesaParte);

    public int iduMesaPartes(BeanMesaPartes objBeanMesaParte, String usuario);

}
