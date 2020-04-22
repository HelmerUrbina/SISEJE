package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanTipoRemuneracion;
import DataService.Despachadores.TipoRemuneracionDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TipoRemuneracionDAOImpl implements TipoRemuneracionDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanTipoRemuneracion objBnTipoRemuneracion;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public TipoRemuneracionDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaTipoRemuneracion(String tipoRemuneracion) {
        lista = new LinkedList<>();
        sql = "SELECT CASE NTIPO_PERSONAL_CODIGO WHEN 1 THEN 'MILITAR' WHEN 2 THEN 'TROPA' WHEN 3 THEN 'CIVIL' ELSE 'SIN ESPECIFICAR' END AS TIPO_PERSONAL,"
                + "CTIPO_REMUNERACION_CODIGO AS CODIGO, VTIPO_REMUNERACION_DESCRIPCION AS DESCRIPCION, "
                + "CASE CSENTENCIA_TIPO WHEN '00' THEN 'TODOS' WHEN '02' THEN 'ALIMENTOS' WHEN '03' THEN 'RESPONSABILIDAD' ELSE ' 'END AS TIPO, "
                + "UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_TIPO_REMUNERACION WHERE "
                + "NTIPO_PERSONAL_CODIGO=? "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, tipoRemuneracion);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnTipoRemuneracion = new BeanTipoRemuneracion();
                objBnTipoRemuneracion.setTipoPersonal(objResultSet.getString("TIPO_PERSONAL"));
                objBnTipoRemuneracion.setCodigo(objResultSet.getString("CODIGO"));
                objBnTipoRemuneracion.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBnTipoRemuneracion.setTipo(objResultSet.getString("TIPO"));
                objBnTipoRemuneracion.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnTipoRemuneracion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaTipoRemuneracion('" + tipoRemuneracion + "') : " + e.getMessage());
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
    public BeanTipoRemuneracion getTipoRemuneracion(BeanTipoRemuneracion objBeanTipoRemuneracion) {
        sql = "SELECT VTIPO_REMUNERACION_DESCRIPCION AS DESCRIPCION, "
                + "CSENTENCIA_TIPO AS TIPO, CESTADO_CODIGO AS ESTADO "
                + "FROM SISEJE_TIPO_REMUNERACION WHERE "
                + "NTIPO_PERSONAL_CODIGO=? AND "
                + "CTIPO_REMUNERACION_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanTipoRemuneracion.getTipoPersonal());
            objPreparedStatement.setString(2, objBeanTipoRemuneracion.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanTipoRemuneracion.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBeanTipoRemuneracion.setTipo(objResultSet.getString("TIPO"));
                objBeanTipoRemuneracion.setEstado(objResultSet.getString("ESTADO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoRemuneracion(objBeanTipoRemuneracion) : " + e.getMessage());
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
        return objBeanTipoRemuneracion;
    }

    @Override
    public int iduTipoRemuneracion(BeanTipoRemuneracion objBeanTipoRemuneracion, String usuario) {
        sql = "{CALL SP_IDU_TIPO_REMUNERACION(?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanTipoRemuneracion.getTipoPersonal());
            cs.setString(2, objBeanTipoRemuneracion.getCodigo());
            cs.setString(3, objBeanTipoRemuneracion.getDescripcion());
            cs.setString(4, objBeanTipoRemuneracion.getTipo());
            cs.setString(5, objBeanTipoRemuneracion.getEstado());
            cs.setString(6, usuario);
            cs.setString(7, objBeanTipoRemuneracion.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduTipoRemuneracion : " + e.getMessage());
            return 0;
        }
        return s;
    }
}
