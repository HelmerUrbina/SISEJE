package DataService.Despachadores;

import BusinessServices.Beans.BeanBeneficiarios;
import java.util.List;

public interface BeneficiariosDAO {

    public List getBeneficiarios(String tipoDocumento, String nroDocumento);

    public List getBeneficiariosCuentas(String nroDocumento);

    public BeanBeneficiarios getBeneficiario(BeanBeneficiarios objBeanBeneficiario);

    public BeanBeneficiarios getBeneficiarioCuenta(BeanBeneficiarios objBeanBeneficiario);

    public int iduBeneficiario(BeanBeneficiarios objBeanBeneficiario, String usuario);

    public int iduBeneficiarioCuenta(BeanBeneficiarios objBeanBeneficiario, String usuario);

}
