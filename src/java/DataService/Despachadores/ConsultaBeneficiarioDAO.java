package DataService.Despachadores;

import BusinessServices.Beans.BeanConsultaBeneficiario;
import java.util.List;

public interface ConsultaBeneficiarioDAO {

    public List getListaBuscaBeneficiario(String beneficiario, String dni);

    public List getListaSentencias(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public List getListaPagoResoluciones(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public List getListaResolucionesDetalle(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public List getListaResolucionesMovimientos(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public List getListaResolucionesProceso(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public BeanConsultaBeneficiario getSentencias(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public BeanConsultaBeneficiario getResoluciones(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public BeanConsultaBeneficiario getResolucionesDetalle(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public BeanConsultaBeneficiario getBeneficiario(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public BeanConsultaBeneficiario getMesaPartes(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

    public String getJuzgadoSentencia(BeanConsultaBeneficiario objBeanConsultaBeneficiario);

}
