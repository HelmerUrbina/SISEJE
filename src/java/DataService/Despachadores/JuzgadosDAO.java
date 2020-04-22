package DataService.Despachadores;

import BusinessServices.Beans.BeanJuzgados;
import java.util.List;

public interface JuzgadosDAO {

    public List getListaJuzgados();

    public BeanJuzgados getJuzgado(BeanJuzgados objBeanJuzgado);

    public int iduJuzgados(BeanJuzgados objBeanJuzgado, String usuario);

}
