package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanJuzgados;
import DataService.Despachadores.JuzgadosDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JuzgadosDAOImpl implements JuzgadosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanJuzgados objBnJuzgado;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public JuzgadosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaJuzgados() {
        lista = new LinkedList<>();
        sql = "SELECT NJUZGADO_CODIGO AS CODIGO, UTIL.FUN_TIPO_JUZGADOS(NTIPO_JUZGADO_CODIGO) AS TIPO_JUZGADOS, "
                + "VJUZGADO_NOMBRE AS NOMBRE, VJUZGADO_DIRECCION AS DIRECCION, VJUZGADO_TELEFONO AS TELEFONO, "
                + "UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_JUZGADOS "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnJuzgado = new BeanJuzgados();
                objBnJuzgado.setCodigo(objResultSet.getString("CODIGO"));
                objBnJuzgado.setTipoJuzgados(objResultSet.getString("TIPO_JUZGADOS"));
                objBnJuzgado.setNombre(objResultSet.getString("NOMBRE"));
                objBnJuzgado.setDireccion(objResultSet.getString("DIRECCION"));
                objBnJuzgado.setTelefono(objResultSet.getString("TELEFONO"));
                objBnJuzgado.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnJuzgado);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaJuzgados() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public BeanJuzgados getJuzgado(BeanJuzgados objBeanJuzgado) {
        sql = "SELECT NTIPO_JUZGADO_CODIGO AS TIPO_JUZGADOS, VJUZGADO_NOMBRE AS NOMBRE, "
                + "VJUZGADO_DIRECCION AS DIRECCION, VJUZGADO_TELEFONO AS TELEFONO, "
                + "CDEPARTAMENTO_CODIGO, CESTADO_CODIGO AS ESTADO "
                + "FROM SISEJE_JUZGADOS WHERE "
                + "NJUZGADO_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanJuzgado.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanJuzgado.setTipoJuzgados(objResultSet.getString("TIPO_JUZGADOS"));
                objBeanJuzgado.setNombre(objResultSet.getString("NOMBRE"));
                objBeanJuzgado.setDireccion(objResultSet.getString("DIRECCION"));
                objBeanJuzgado.setTelefono(objResultSet.getString("TELEFONO"));
                objBeanJuzgado.setDepartamento(objResultSet.getString("CDEPARTAMENTO_CODIGO"));
                objBeanJuzgado.setEstado(objResultSet.getString("ESTADO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getJuzgado(objBeanJuzgado) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return objBeanJuzgado;
    }

    @Override
    public int iduJuzgados(BeanJuzgados objBeanJuzgado, String usuario) {
        sql = "{CALL SP_IDU_JUZGADOS(?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanJuzgado.getCodigo());
            cs.setString(2, objBeanJuzgado.getTipoJuzgados());
            cs.setString(3, objBeanJuzgado.getNombre());
            cs.setString(4, objBeanJuzgado.getDireccion());
            cs.setString(5, objBeanJuzgado.getTelefono());
            cs.setString(6, objBeanJuzgado.getDepartamento());
            cs.setString(7, objBeanJuzgado.getEstado());
            cs.setString(8, usuario);
            cs.setString(9, objBeanJuzgado.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduJuzgados : " + e.getMessage());
            return 0;
        }
        return s;
    }
}
