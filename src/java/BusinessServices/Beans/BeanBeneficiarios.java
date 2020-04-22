package BusinessServices.Beans;

import java.io.Serializable;
import java.sql.Date;

public class BeanBeneficiarios implements Serializable {

    private String Mode;
    private String TipoDocumento;
    private String Documento;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Nombres;
    private String RazonSocial;
    private String Verificado;
    private String Banco;
    private String Cuenta;
    private String CCI;
    private Date FechaNacimiento;

    public String getMode() {
        return Mode;
    }

    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String TipoDocumento) {
        this.TipoDocumento = TipoDocumento;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getVerificado() {
        return Verificado;
    }

    public void setVerificado(String Verificado) {
        this.Verificado = Verificado;
    }

    public String getBanco() {
        return Banco;
    }

    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String Cuenta) {
        this.Cuenta = Cuenta;
    }

    public String getCCI() {
        return CCI;
    }

    public void setCCI(String CCI) {
        this.CCI = CCI;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

}
