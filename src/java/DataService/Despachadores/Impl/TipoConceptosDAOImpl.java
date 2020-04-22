package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanComun;
import DataService.Despachadores.TipoConceptosDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TipoConceptosDAOImpl implements TipoConceptosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanComun objBnComun;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public TipoConceptosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaTipoConceptos() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_CONCEPTO_CODIGO AS CODIGO, VTIPO_CONCEPTO_DESCRIPCION AS DESCRIPCION, "
                + "VTIPO_CONCEPTO_ABREVIATURA AS ABREVIATURA, UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_TIPO_CONCEPTOS "
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
            System.out.println("Error al obtener getListaTipoConceptos() : " + e.getMessage());
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
    public BeanComun getTipoConcepto(BeanComun objBeanComun) {
        sql = "SELECT VTIPO_CONCEPTO_DESCRIPCION AS DESCRIPCION, "
                + "VTIPO_CONCEPTO_ABREVIATURA AS ABREVIATURA, "
                + "CESTADO_CODIGO AS ESTADO "
                + "FROM SISEJE_TIPO_CONCEPTOS WHERE "
                + "NTIPO_CONCEPTO_CODIGO=?";
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
            System.out.println("Error al obtener getTipoConcepto(objBeanComun) : " + e.getMessage());
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
    public int iduTipoConceptos(BeanComun objBeanComun, String usuario) {
        sql = "{CALL SP_IDU_TIPO_CONCEPTOS(?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanComun.getCodigo());
            cs.setString(2, objBeanComun.getDescripcion());
            cs.setString(3, objBeanComun.getAbreviatura());
            cs.setString(4, objBeanComun.getEstado());
            cs.setString(5, usuario);
            cs.setString(6, objBeanComun.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduTipoConceptos : " + e.getMessage());
            return 0;
        }
        return s;
    }
}
