package DataService.Despachadores;

import BusinessServices.Beans.BeanUsuario;
import java.util.List;

public interface UsuarioDAO {

    public BeanUsuario autentica(String usuario, String password);

    public List getModulos(String usuario);

    public List getMenu(String usuario);

    public List getModulos();

    public List getMenu();

    public List getOpciones(String usuario);

    public List getListaUsuarios();

    public BeanUsuario getUsuario(BeanUsuario objBeanUsuario);

    public BeanUsuario recuperarPassword(String usuario, String correo);

    public String obtenerPassword(String usuario);

    public int iduUsuario(BeanUsuario objBeanUsuario, String usuario);

    public int iduOpciones(BeanUsuario objBeanUsuario, String usuario);

}
