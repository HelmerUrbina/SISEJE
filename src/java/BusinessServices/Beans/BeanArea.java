package BusinessServices.Beans;

import java.io.Serializable;

public class BeanArea implements Serializable {

    private String Mode;
    private String Seccion;
    private String Codigo;
    private String Descripcion;
    private String Abreviatura;
    private String Estado;

    public String getMode() {
        return Mode;
    }

    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String Seccion) {
        this.Seccion = Seccion;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getAbreviatura() {
        return Abreviatura;
    }

    public void setAbreviatura(String Abreviatura) {
        this.Abreviatura = Abreviatura;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

}
