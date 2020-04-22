package BusinessServices.Beans;

import java.io.Serializable;
import java.sql.Date;

public class BeanConsultaBeneficiario implements Serializable {

    private String Mode;
    private String CIP;
    private String Personal;
    private String DNI;
    private String Beneficiario;
    private String Resolucion;
    private String Periodo;
    private String Mes;
    private String Banco;
    private String Cuenta;
    private String Estado;
    private String FormaPago;
    private String Tipo;
    private Date Fecha;
    private Double MontoProcesado;
    private Double MontoGenerado;
    private Double Porcentaje;
    private Double Monto;
    private Integer Sentencia;
    private Integer Cuotas;

    public String getMode() {
        return Mode;
    }

    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    public String getCIP() {
        return CIP;
    }

    public void setCIP(String CIP) {
        this.CIP = CIP;
    }

    public String getPersonal() {
        return Personal;
    }

    public void setPersonal(String Personal) {
        this.Personal = Personal;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getBeneficiario() {
        return Beneficiario;
    }

    public void setBeneficiario(String Beneficiario) {
        this.Beneficiario = Beneficiario;
    }

    public String getResolucion() {
        return Resolucion;
    }

    public void setResolucion(String Resolucion) {
        this.Resolucion = Resolucion;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String Periodo) {
        this.Periodo = Periodo;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String Mes) {
        this.Mes = Mes;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String FormaPago) {
        this.FormaPago = FormaPago;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Double getMontoProcesado() {
        return MontoProcesado;
    }

    public void setMontoProcesado(Double MontoProcesado) {
        this.MontoProcesado = MontoProcesado;
    }

    public Double getMontoGenerado() {
        return MontoGenerado;
    }

    public void setMontoGenerado(Double MontoGenerado) {
        this.MontoGenerado = MontoGenerado;
    }

    public Double getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(Double Porcentaje) {
        this.Porcentaje = Porcentaje;
    }

    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double Monto) {
        this.Monto = Monto;
    }

    public Integer getSentencia() {
        return Sentencia;
    }

    public void setSentencia(Integer Sentencia) {
        this.Sentencia = Sentencia;
    }

    public Integer getCuotas() {
        return Cuotas;
    }

    public void setCuotas(Integer Cuotas) {
        this.Cuotas = Cuotas;
    }

}
