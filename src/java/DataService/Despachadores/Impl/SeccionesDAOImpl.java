package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanComun;
import DataService.Despachadores.SeccionesDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SeccionesDAOImpl implements SeccionesDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanComun objBnComun;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public SeccionesDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaSecciones() {
        lista = new LinkedList<>();
        sql = "SELECT CSECCION_CODIGO AS CODIGO, VSECCION_DESCRIPCION AS DESCRIPCION, "
                + "VSECCION_ABREVIATURA AS ABREVIATURA, UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_SECCIONES "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBnComun.setAbreviatura(objResultSet.getString("ABREVIATURA"));
                objBnComun.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaSecciones() : " + e.getMessage());
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
    public BeanComun getSeccion(BeanComun objBeanComun) {
        sql = "SELECT VSECCION_DESCRIPCION AS DESCRIPCION, "
                + "VSECCION_ABREVIATURA AS ABREVIATURA, CESTADO_CODIGO AS ESTADO "
                + "FROM SISEJE_SECCIONES WHERE "
                + "CSECCION_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanComun.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBeanComun.setAbreviatura(objResultSet.getString("ABREVIATURA"));
                objBeanComun.setEstado(objResultSet.getString("ESTADO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getSeccion(objBeanComun) : " + e.getMessage());
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
        return objBeanComun;
    }

    @Override
    public int iduSecciones(BeanComun objBeanComun, String usuario) {
        sql = "{CALL SP_IDU_SECCIONES(?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanComun.getCodigo());
            cs.setString(2, objBeanComun.getDescripcion());
            cs.setString(3, objBeanComun.getAbreviatura());
            cs.setString(4, objBeanComun.getEstado());
            cs.setString(5, usuario);
            cs.setString(6, objBeanComun.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduSecciones : " + e.getMessage());
            return 0;
        }
        return s;
    }
}
