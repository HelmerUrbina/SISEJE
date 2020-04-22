package BusinessServices.Beans;

import java.io.Serializable;

public class BeanReporte implements Serializable {
    
    private String Mode;
    private String Codigo;
    private String Codigo2;
    private String Tipo;
    private String Reporte;
    private String Periodo;
    private String Mes;
    private String CIP;
    private String Sentencia;
    private String Resolucion;

    public String getMode() {
        return Mode;
    }

    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCodigo2() {
        return Codigo2;
    }

    public void setCodigo2(String Codigo2) {
        this.Codigo2 = Codigo2;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getReporte() {
        return Reporte;
    }

    public void setReporte(String Reporte) {
        this.Reporte = Reporte;
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

    public String getCIP() {
        return CIP;
    }

    public void setCIP(String CIP) {
        this.CIP = CIP;
    }

    public String getSentencia() {
        return Sentencia;
    }

    public void setSentencia(String Sentencia) {
        this.Sentencia = Sentencia;
    }

    public String getResolucion() {
        return Resolucion;
    }

    public void setResolucion(String Resolucion) {
        this.Resolucion = Resolucion;
    }

}
