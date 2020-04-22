package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanArea;
import DataService.Despachadores.AreasDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AreasDAOImpl implements AreasDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanArea objBnArea;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public AreasDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaAreas(String codigo) {
        lista = new LinkedList<>();
        sql = "SELECT NAREA_CODIGO AS CODIGO, VAREA_DESCRIPCION AS DESCRIPCION, "
                + "VAREA_ABREVIATURA AS ABREVIATURA, UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_AREAS WHERE "
                + "CSECCION_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, codigo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnArea = new BeanArea();
                objBnArea.setCodigo(objResultSet.getString("CODIGO"));
                objBnArea.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBnArea.setAbreviatura(objResultSet.getString("ABREVIATURA"));
                objBnArea.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnArea);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaAreas() : " + e.getMessage());
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
    public BeanArea getArea(BeanArea objBeanArea) {
        sql = "SELECT VAREA_DESCRIPCION AS DESCRIPCION, "
                + "VAREA_ABREVIATURA AS ABREVIATURA, CESTADO_CODIGO AS ESTADO "
                + "FROM SISEJE_AREAS WHERE "
                + "CSECCION_CODIGO=? AND "
                + "NAREA_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanArea.getSeccion());
            objPreparedStatement.setString(2, objBeanArea.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanArea.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBeanArea.setAbreviatura(objResultSet.getString("ABREVIATURA"));
                objBeanArea.setEstado(objResultSet.getString("ESTADO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getArea(objBeanArea) : " + e.getMessage());
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
        return objBeanArea;
    }

    @Override
    public int iduAreas(BeanArea objBeanArea, String usuario) {
        sql = "{CALL SP_IDU_AREAS(?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanArea.getSeccion());
            cs.setString(2, objBeanArea.getCodigo());
            cs.setString(3, objBeanArea.getDescripcion());
            cs.setString(4, objBeanArea.getAbreviatura());
            cs.setString(5, objBeanArea.getEstado());
            cs.setString(6, usuario);
            cs.setString(7, objBeanArea.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduAreas : " + e.getMessage());
            return 0;
        }
        return s;
    }
}
