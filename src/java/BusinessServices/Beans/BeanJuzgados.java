package BusinessServices.Beans;

import java.io.Serializable;

public class BeanJuzgados implements Serializable{

    private String Mode;
    private String Codigo;
    private String TipoJuzgados;
    private String Nombre;
    private String Departamento;
    private String Direccion;
    private String Telefono;
    private String Estado;

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

    public String getTipoJuzgados() {
        return TipoJuzgados;
    }

    public void setTipoJuzgados(String TipoJuzgados) {
        this.TipoJuzgados = TipoJuzgados;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

}
