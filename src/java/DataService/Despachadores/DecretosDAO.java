package DataService.Despachadores;

import BusinessServices.Beans.BeanMesaPartes;
import java.util.List;

public interface DecretosDAO {

    public List getListaDecretosPendientes(BeanMesaPartes objBeanDecreto);

    public List getListaDecretosDecretados(BeanMesaPartes objBeanDecreto);

    public List getListaConsultaDecretosPendientes(BeanMesaPartes objBeanDecreto);

    public List getListaConsultaDecretosRespondidos(BeanMesaPartes objBeanDecreto);

    public List getListaSeguimientoDecretos(BeanMesaPartes objBeanDecreto);

    public BeanMesaPartes getDecretos(BeanMesaPartes objBeanDecreto);

    public BeanMesaPartes getDecretosRespuesta(BeanMesaPartes objBeanDecreto);

    public String getDecretosAreasDetalle(BeanMesaPartes objBeanDecreto);

    public String getDecretosTipoDecretos(BeanMesaPartes objBeanDecreto);

    public String getDecretosTipoRemuneracion(BeanMesaPartes objBeanDecreto);

    public int iduDecretos(BeanMesaPartes objBeanDecreto, String usuario);

    public int iduDecretosTipoDecretos(BeanMesaPartes objBeanDecreto, String usuario);

    public int iduDecretosTipoRemuneracion(BeanMesaPartes objBeanDecreto, String usuario);

    public int iduDecretosRespuesta(BeanMesaPartes objBeanDecreto, String usuario);

}
