package BusinessServices.Beans;

import java.io.Serializable;

public class BeanTipoRemuneracion implements Serializable {

    private String Mode;
    private String TipoPersonal;
    private String Codigo;
    private String Descripcion;
    private String Tipo;
    private String Estado;

    public String getMode() {
        return Mode;
    }

    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    public String getTipoPersonal() {
        return TipoPersonal;
    }

    public void setTipoPersonal(String TipoPersonal) {
        this.TipoPersonal = TipoPersonal;
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

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

}
