package DataService.Despachadores;

import BusinessServices.Beans.BeanSentencias;
import java.util.List;

public interface SentenciasDAO {

    public List getListaBuscaPersonal(String personal, String cip);

    public List getListaSentencias(BeanSentencias objBeanSentencias);

    public List getListaResoluciones(BeanSentencias objBeanSentencias);

    public List getListaResolucionesActivas(BeanSentencias objBeanSentencias);

    public List getListaResolucionesDetalle(BeanSentencias objBeanSentencias);

    public List getListaResolucionesMovimientos(BeanSentencias objBeanSentencias);

    public List getListaResolucionesProceso(BeanSentencias objBeanSentencias);

    public List getListaResolucionesMovimientoValidacion(BeanSentencias objBeanSentencias);

    public List getListaResolucionesPlanillaMCPP(BeanSentencias objBeanSentencias);

    public BeanSentencias getPersonal(BeanSentencias objBeanSentencias);

    public BeanSentencias getSentencias(BeanSentencias objBeanSentencias);

    public BeanSentencias getResoluciones(BeanSentencias objBeanSentencias);

    public BeanSentencias getResolucionesDetalle(BeanSentencias objBeanSentencias);

    public BeanSentencias getBeneficiario(BeanSentencias objBeanSentencias);

    public BeanSentencias getMesaPartes(BeanSentencias objBeanSentencias, String periodo, String correlativo);

    public String getJuzgadoSentencia(BeanSentencias objBeanSentencias);

    public int iduSentencias(BeanSentencias objBeanSentencias, String usuario);

    public int iduResoluciones(BeanSentencias objBeanSentencias, String usuario);

    public int iduResolucionesBeneficiario(BeanSentencias objBeanSentencias, String usuario);

    public int iduResolucionesDetalle(BeanSentencias objBeanSentencias, String usuario);

    public int iduCerrarResoluciones(BeanSentencias objBeanSentencias, String usuario);

    public String iduResolucionesPlanilla(BeanSentencias objBeanSentencias, String usuario);

    public String iduResolucionesMovimientos(BeanSentencias objBeanSentencias, String usuario);

    public String iduResolucionesProcesoDescuentos(BeanSentencias objBeanSentencias, String usuario);

    public String iduResolucionesProceso(BeanSentencias objBeanSentencias, String usuario);

}
