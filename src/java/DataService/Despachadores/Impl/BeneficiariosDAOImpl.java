package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanBeneficiarios;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import DataService.Despachadores.BeneficiariosDAO;

public class BeneficiariosDAOImpl implements BeneficiariosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanBeneficiarios objBnBeneficiario;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public BeneficiariosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getBeneficiarios(String tipoDocumento, String nroDocumento) {
        lista = new LinkedList<>();
        sql = "SELECT VBENEFICIARIO_DOCUMENTO AS DOCUMENTO, VBENEFICIARIO_PATERNO AS PATERNO, "
                + "VBENEFICIARIO_MATERNO AS MATERNO, VBENEFICIARIO_NOMBRES AS NOMBRES, "
                + "DBENEFICIARIO_NACIMIENTO AS FECHA_NACIMIENTO, UPPER(VBENEFICIARIO_RAZON_SOCIAL) AS RAZON_SOCIAL "
                + "FROM SISEJE_BENEFICIARIO WHERE "
                + "NBENEFICIARIO_TIPO_DOCUMENTO LIKE ? AND "
                + "VBENEFICIARIO_DOCUMENTO LIKE ? "
                + "ORDER BY PATERNO, MATERNO, NOMBRES";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, tipoDocumento.replaceAll("0", "%"));
            objPreparedStatement.setString(2, "%" + nroDocumento + "%");
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnBeneficiario = new BeanBeneficiarios();
                objBnBeneficiario.setDocumento(objResultSet.getString("DOCUMENTO"));
                objBnBeneficiario.setApellidoPaterno(objResultSet.getString("PATERNO"));
                objBnBeneficiario.setApellidoMaterno(objResultSet.getString("MATERNO"));
                objBnBeneficiario.setNombres(objResultSet.getString("NOMBRES"));
                objBnBeneficiario.setFechaNacimiento(objResultSet.getDate("FECHA_NACIMIENTO"));
                objBnBeneficiario.setRazonSocial(objResultSet.getString("RAZON_SOCIAL"));
                lista.add(objBnBeneficiario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficiarios() : " + e.getMessage());
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
    public List getBeneficiariosCuentas(String nroDocumento) {
        lista = new LinkedList<>();
        sql = "SELECT NBENEFICIARIO_CUENTA_CODIGO AS CODIGO, UTIL.FUN_BANCO(NVL(NBANCO_CODIGO,0)) AS BANCO, "
                + "VBENEFICIARIO_CUENTA_NUMERO AS CUENTA, VBENEFICIARIO_CUENTA_CCI AS CCI "
                + "FROM SISEJE_BENEFICIARIO_CUENTA WHERE "
                + "VBENEFICIARIO_DOCUMENTO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, nroDocumento);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnBeneficiario = new BeanBeneficiarios();
                objBnBeneficiario.setCuenta(objResultSet.getString("CODIGO"));
                objBnBeneficiario.setBanco(objResultSet.getString("BANCO"));
                objBnBeneficiario.setTipoDocumento(objResultSet.getString("CUENTA"));
                objBnBeneficiario.setCCI(objResultSet.getString("CCI"));
                lista.add(objBnBeneficiario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficiariosCuentas('" + nroDocumento + "') : " + e.getMessage());
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
    public BeanBeneficiarios getBeneficiario(BeanBeneficiarios objBeanBeneficiario) {
        sql = "SELECT NBENEFICIARIO_TIPO_DOCUMENTO, VBENEFICIARIO_PATERNO, VBENEFICIARIO_MATERNO, "
                + "VBENEFICIARIO_NOMBRES, DBENEFICIARIO_NACIMIENTO, VBENEFICIARIO_RAZON_SOCIAL, VBENEFICIARIO_RUC "
                + "FROM SISEJE_BENEFICIARIO WHERE "
                + "VBENEFICIARIO_DOCUMENTO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanBeneficiario.getDocumento());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanBeneficiario.setTipoDocumento(objResultSet.getString("NBENEFICIARIO_TIPO_DOCUMENTO"));
                objBeanBeneficiario.setApellidoPaterno(objResultSet.getString("VBENEFICIARIO_PATERNO"));
                objBeanBeneficiario.setApellidoMaterno(objResultSet.getString("VBENEFICIARIO_MATERNO"));
                objBeanBeneficiario.setNombres(objResultSet.getString("VBENEFICIARIO_NOMBRES"));
                objBeanBeneficiario.setFechaNacimiento(objResultSet.getDate("DBENEFICIARIO_NACIMIENTO"));
                objBeanBeneficiario.setRazonSocial(objResultSet.getString("VBENEFICIARIO_RAZON_SOCIAL"));
                objBeanBeneficiario.setCCI(objResultSet.getString("VBENEFICIARIO_RUC"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficiario(objBeanBeneficiario) : " + e.getMessage());
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
        return objBeanBeneficiario;
    }

    @Override
    public BeanBeneficiarios getBeneficiarioCuenta(BeanBeneficiarios objBeanBeneficiario) {
        sql = "SELECT NVL(NBANCO_CODIGO,0) AS BANCO, NVL(VBENEFICIARIO_CUENTA_NUMERO,'') AS CUENTA, NVL(VBENEFICIARIO_CUENTA_CCI ,' ') AS CCI "
                + "FROM SISEJE_BENEFICIARIO_CUENTA WHERE "
                + "VBENEFICIARIO_DOCUMENTO=? AND "
                + "NBENEFICIARIO_CUENTA_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanBeneficiario.getDocumento());
            objPreparedStatement.setString(2, objBeanBeneficiario.getCuenta());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanBeneficiario.setBanco(objResultSet.getString("BANCO"));
                objBeanBeneficiario.setApellidoPaterno(objResultSet.getString("CUENTA"));
                objBeanBeneficiario.setCCI(objResultSet.getString("CCI"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficiarioCuenta(objBeanBeneficiario) : " + e.getMessage());
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
        return objBeanBeneficiario;
    }

    @Override
    public int iduBeneficiario(BeanBeneficiarios objBeanBeneficiario, String usuario) {
        sql = "{CALL SP_IDU_BENEFICIARIO(?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanBeneficiario.getTipoDocumento());
            cs.setString(2, objBeanBeneficiario.getDocumento());
            cs.setString(3, objBeanBeneficiario.getApellidoPaterno());
            cs.setString(4, objBeanBeneficiario.getApellidoMaterno());
            cs.setString(5, objBeanBeneficiario.getNombres());
            cs.setDate(6, objBeanBeneficiario.getFechaNacimiento());
            cs.setString(7, objBeanBeneficiario.getRazonSocial());
            cs.setString(8, objBeanBeneficiario.getCCI());
            cs.setString(9, usuario);
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduBeneficiario : " + e.getMessage());
            return 0;
        }
        return s;
    }

    @Override
    public int iduBeneficiarioCuenta(BeanBeneficiarios objBeanBeneficiario, String usuario) {
        sql = "{CALL SP_IDU_BENEFICIARIO_CUENTA(?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanBeneficiario.getTipoDocumento());
            cs.setString(2, objBeanBeneficiario.getDocumento());
            cs.setString(3, objBeanBeneficiario.getCuenta());
            cs.setString(4, objBeanBeneficiario.getBanco());
            cs.setString(5, objBeanBeneficiario.getApellidoPaterno());
            cs.setString(6, objBeanBeneficiario.getCCI());
            cs.setString(7, usuario);
            cs.setString(8, objBeanBeneficiario.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduBeneficiarioCuenta : " + e.getMessage());
            return 0;
        }
        return s;
    }

}
