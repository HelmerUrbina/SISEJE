package DataService.Despachadores;

import java.util.List;

public interface CombosDAO {

    public List getModulos();

    public List getPeriodos();

    public List getTipoDocumentos();

    public List getTipoConceptos();

    public List getTipoRemuneracion();

    public List getTipoPagos();

    public List getSecciones();

    public List getAreas(String codigo);

    public List getPrioridad();

    public List getTipoJuzgados();

    public List getTipoDecretos();

    public List getDepartamentos();

    public List getJuzgados();

    public List getJuzgados(String tipoJuzgado);

    public List getJuzgados(String tipoJuzgado, String departamento);

    public List getBancos();

    public List getLugar(String departamento);

    public List getMesaPartes(String periodo, Integer area);

    public List getAreaResolucion();

    public List getTipoMonedas();

    public List getConceptosSentencia(String periodo, String mes, String tipo);

    public List getTipoPersonalPlanilla(String periodo, String mes, String tipo, String tipoPersonal);

    public List getTipoPersonalPlanillaMCPP(String periodo, String mes, String tipo, String tipoPersonal);

    public String getBeneficiario(String codigo);

    public String getBeneficiarioCuenta(String dni, String banco);

    public String getBeneficiarioSentencia(String CIP, String sentencia);

    public String getRazonSocial(String codigo);

}
