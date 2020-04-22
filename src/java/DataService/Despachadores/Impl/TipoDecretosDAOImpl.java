package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanComun;
import DataService.Despachadores.TipoDecretosDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TipoDecretosDAOImpl implements TipoDecretosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanComun objBnComun;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public TipoDecretosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaTipoDecretos() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_DECRETO_CODIGO AS CODIGO, VTIPO_DECRETO_DESCRIPCION AS DESCRIPCION, "
                + "UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_TIPO_DECRETOS "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBnComun.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaTipoDecretos() : " + e.getMessage());
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
    public BeanComun getTipoDecreto(BeanComun objBeanComun) {
        sql = "SELECT VTIPO_DECRETO_DESCRIPCION AS DESCRIPCION, CESTADO_CODIGO AS ESTADO "
                + "FROM SISEJE_TIPO_DECRETOS WHERE "
                + "NTIPO_DECRETO_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanComun.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBeanComun.setEstado(objResultSet.getString("ESTADO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoDecreto(objBeanComun) : " + e.getMessage());
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
    public int iduTipoDecretos(BeanComun objBeanComun, String usuario) {
        sql = "{CALL SP_IDU_TIPO_DECRETOS(?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanComun.getCodigo());
            cs.setString(2, objBeanComun.getDescripcion());
            cs.setString(3, objBeanComun.getEstado());
            cs.setString(4, usuario);
            cs.setString(5, objBeanComun.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduTipoDecretos : " + e.getMessage());
            return 0;
        }
        return s;
    }
}
