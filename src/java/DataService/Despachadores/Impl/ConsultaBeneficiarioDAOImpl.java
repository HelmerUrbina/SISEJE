package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanConsultaBeneficiario;
import BusinessServices.Beans.BeanSentencias;
import DataService.Despachadores.ConsultaBeneficiarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConsultaBeneficiarioDAOImpl implements ConsultaBeneficiarioDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanConsultaBeneficiario objBnBeneficiario;
    private PreparedStatement objPreparedStatement;

    public ConsultaBeneficiarioDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaBuscaBeneficiario(String beneficiario, String dni) {
        String add = "DOCUMENTO LIKE '%" + dni.trim() + "%' ";
        if (beneficiario.length() > 0) {
            add = "UPPER(BENEFICIARIO) LIKE UPPER('" + beneficiario.replaceAll(" ", "%") + "%') ";
        }
        lista = new LinkedList<>();
        sql = "SELECT DOCUMENTO, BENEFICIARIO "
                + "FROM V_BENEFICIARIO WHERE "
                + add
                + "ORDER BY BENEFICIARIO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnBeneficiario = new BeanConsultaBeneficiario();
                objBnBeneficiario.setDNI(objResultSet.getString("DOCUMENTO"));
                objBnBeneficiario.setBeneficiario(objResultSet.getString("BENEFICIARIO"));
                lista.add(objBnBeneficiario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaBuscaBeneficiario() : " + e.getMessage());
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
    public List getListaSentencias(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        lista = new LinkedList<>();
        sql = "SELECT RESOL.CPERSONAL_CIP AS CIP, RESOL.NSENTENCIA_CODIGO AS SENTENCIA, RESOL.NRESOLUCION_CODIGO AS RESOLUCION, "
                + "UTIL.FUN_AREA('04', TO_NUMBER(RESOL.CSENTENCIA_TIPO)) AS TIPO_SENTENCIA, RESOL.CSENTENCIA_TIPO AS TIPO,"
                + "UTIL.FUN_NOMBRE_PERSONAL(BENE.CPERSONAL_CIP) AS DEMANDADO, RESOL.VRESOLUCION_EXPEDIENTE AS EXPEDIENTE, "
                + "NVL(UTIL.FUN_MESA_PARTE_DOCUMENTO(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO),'SIN DOCUMENTO') AS MESA_PARTES, "
                + "CASE WHEN RESOL.NTIPO_PAGO_CODIGO IN (1,2) THEN TO_CHAR(RESOL.NRESOLUCION_PORCENTAJE,'FM999,999,999,999.009')||' %' "
                + "WHEN RESOL.NTIPO_PAGO_CODIGO IN (3,4) THEN UTIL.FUN_ABREVIATURA_TIPO_PAGO(RESOL.NTIPO_PAGO_CODIGO)||' S/ '|| TO_CHAR(NVL(RESOL.NRESOLUCION_MONTO,0),'FM999,999,999,999.009') "
                + "ELSE ' ' END AS FORMA_PAGO, UTIL.FUN_DESCRIPCION_ESTADO(RESOL.CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_RESOLUCIONES_BENEFICIAR BENE INNER JOIN SISEJE_RESOLUCIONES RESOL ON ( "
                + "BENE.CSENTENCIA_TIPO=RESOL.CSENTENCIA_TIPO AND "
                + "BENE.CPERSONAL_CIP=RESOL.CPERSONAL_CIP AND "
                + "BENE.NSENTENCIA_CODIGO=RESOL.NSENTENCIA_CODIGO AND "
                + "BENE.NRESOLUCION_CODIGO=RESOL.NRESOLUCION_CODIGO) WHERE "
                + "BENE.VBENEFICIARIO_DOCUMENTO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanConsultaBeneficiario.getDNI());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnBeneficiario = new BeanConsultaBeneficiario();
                objBnBeneficiario.setCIP(objResultSet.getString("CIP"));
                objBnBeneficiario.setSentencia(objResultSet.getInt("SENTENCIA"));
                objBnBeneficiario.setResolucion(objResultSet.getString("RESOLUCION"));
                objBnBeneficiario.setTipo(objResultSet.getString("TIPO"));
                objBnBeneficiario.setMes(objResultSet.getString("TIPO_SENTENCIA"));
                objBnBeneficiario.setPersonal(objResultSet.getString("DEMANDADO"));
                objBnBeneficiario.setCuenta(objResultSet.getString("EXPEDIENTE"));
                objBnBeneficiario.setPeriodo(objResultSet.getString("MESA_PARTES"));
                objBnBeneficiario.setDNI(objResultSet.getString("FORMA_PAGO"));
                objBnBeneficiario.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnBeneficiario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaSentencias() : " + e.getMessage());
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
    public List getListaPagoResoluciones(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        lista = new LinkedList<>();
        sql = "SELECT CPERIODO_CODIGO AS PERIODO, UTIL.FUN_NOMBRE_MES(CMES_CODIGO) AS MES, "
                + "UTIL.FUN_NOMBRE_REMUNERACION(UTIL.FUN_TIPO_PERSONAL(CPERSONAL_CIP), CTIPO_REMUNERACION_CODIGO) AS CONCEPTO, "
                + "UTIL.FUN_BANCO(NBANCO_CODIGO) AS BANCO, VRESOLUCION_MOVIMIENTO_CUENTA AS CUENTA, "
                + "CASE WHEN NTIPO_PAGO_CODIGO IN (1,2) THEN TO_CHAR(NRESOLUCION_MOVIMIENTO_MONTO,'FM999,999,999,999.009')||' %' "
                + "WHEN NTIPO_PAGO_CODIGO IN (3,4) THEN UTIL.FUN_ABREVIATURA_TIPO_PAGO(NTIPO_PAGO_CODIGO)||' S/ '|| TO_CHAR(NVL(NRESOLUCION_MOVIMIENTO_MONTO,0),'FM999,999,999,999.009') "
                + "ELSE ' ' END AS FORMA_PAGO, NVL(NRESOLUCION_MOVIMIENTO_PAGO,0) AS GESTIONADO "
                + "FROM SISEJE_RESOLUCIONES_MOVIMIENTO WHERE "
                + "TRIM(VRESOLUCION_MOVIMIENTO_DOCUMEN)=? AND "
                + "CPERSONAL_CIP=? AND "
                + "NSENTENCIA_CODIGO=? AND "
                + "NRESOLUCION_CODIGO=? AND "
                + "NRESOLUCION_MOVIMIENTO_MONTO>0 "
                + "ORDER BY 1,TO_NUMBER(CMES_CODIGO), CTIPO_REMUNERACION_CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanConsultaBeneficiario.getDNI());
            objPreparedStatement.setString(2, objBeanConsultaBeneficiario.getCIP());
            objPreparedStatement.setInt(3, objBeanConsultaBeneficiario.getSentencia());
            objPreparedStatement.setString(4, objBeanConsultaBeneficiario.getResolucion());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnBeneficiario = new BeanConsultaBeneficiario();
                objBnBeneficiario.setPeriodo(objResultSet.getString("PERIODO"));
                objBnBeneficiario.setMes(objResultSet.getString("MES"));
                objBnBeneficiario.setEstado(objResultSet.getString("CONCEPTO"));
                objBnBeneficiario.setCuenta(objResultSet.getString("CUENTA"));
                objBnBeneficiario.setBanco(objResultSet.getString("BANCO"));
                objBnBeneficiario.setFormaPago(objResultSet.getString("FORMA_PAGO"));
                objBnBeneficiario.setMontoProcesado(objResultSet.getDouble("GESTIONADO"));
                lista.add(objBnBeneficiario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaPagoResoluciones() : " + e.getMessage());
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
    public List getListaResolucionesDetalle(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        lista = new LinkedList<>();
        sql = "SELECT REMU.CTIPO_REMUNERACION_CODIGO AS CODIGO, REMU.VTIPO_REMUNERACION_DESCRIPCION AS DESCRIPCION, "
                + "NVL(DETA.NRESOLUCION_DETALLE_MONTO,0) AS MONTO "
                + "FROM SISEJE_TIPO_REMUNERACION REMU LEFT OUTER JOIN SISEJE_RESOLUCIONES_DETALLE DETA ON "
                + "(REMU.CTIPO_REMUNERACION_CODIGO=DETA.CTIPO_REMUNERACION_CODIGO AND "
                + "CPERSONAL_CIP=? AND "
                + "NSENTENCIA_CODIGO=? AND "
                + "NRESOLUCION_CODIGO=?) WHERE "
                + "REMU.NTIPO_PERSONAL_CODIGO=UTIL.FUN_TIPO_PERSONAL(?) AND "
                + "TO_NUMBER(REMU.CSENTENCIA_TIPO) IN (0,?) AND "
                + "REMU.CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanConsultaBeneficiario.getCIP());
            objPreparedStatement.setInt(2, objBeanConsultaBeneficiario.getSentencia());
            objPreparedStatement.setString(3, objBeanConsultaBeneficiario.getResolucion());
            objPreparedStatement.setString(4, objBeanConsultaBeneficiario.getCIP());
            objPreparedStatement.setString(5, objBeanConsultaBeneficiario.getTipo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnBeneficiario = new BeanConsultaBeneficiario();
                objBnBeneficiario.setEstado(objResultSet.getString("CODIGO"));
                objBnBeneficiario.setFormaPago(objResultSet.getString("DESCRIPCION"));
                objBnBeneficiario.setMonto(objResultSet.getDouble("MONTO"));
                lista.add(objBnBeneficiario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaResolucionesDetalle() : " + e.getMessage());
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
    public List getListaResolucionesMovimientos(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getListaResolucionesProceso(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BeanConsultaBeneficiario getSentencias(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BeanConsultaBeneficiario getResoluciones(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BeanConsultaBeneficiario getResolucionesDetalle(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        sql = "SELECT NTIPO_PAGO_CODIGO, NRESOLUCION_CUOTAS, NRESOLUCION_MONTO, NRESOLUCION_PORCENTAJE, NRESOLUCION_TOTAL, CTIPO_MONEDA_CODIGO "
                + "FROM SISEJE_RESOLUCIONES WHERE "
                + "CPERSONAL_CIP='" + objBeanConsultaBeneficiario.getCIP() + "' AND "
                + "NSENTENCIA_CODIGO='" + objBeanConsultaBeneficiario.getSentencia() + "' AND "
                + "NRESOLUCION_CODIGO='" + objBeanConsultaBeneficiario.getResolucion() + "' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanConsultaBeneficiario.setFormaPago(objResultSet.getString("NTIPO_PAGO_CODIGO"));
                objBeanConsultaBeneficiario.setCuotas(objResultSet.getInt("NRESOLUCION_CUOTAS"));
                objBeanConsultaBeneficiario.setMonto(objResultSet.getDouble("NRESOLUCION_MONTO"));
                objBeanConsultaBeneficiario.setPorcentaje(objResultSet.getDouble("NRESOLUCION_PORCENTAJE"));
                objBeanConsultaBeneficiario.setMontoGenerado(objResultSet.getDouble("NRESOLUCION_TOTAL"));
                objBeanConsultaBeneficiario.setCuenta(objResultSet.getString("CTIPO_MONEDA_CODIGO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getResolucionesDetalle(objBeanSentencias) : " + e.getMessage());
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
        return objBeanConsultaBeneficiario;

    }

    @Override
    public BeanConsultaBeneficiario getBeneficiario(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        sql = "SELECT DOCUMENTO, BENEFICIARIO "
                + "FROM V_BENEFICIARIO WHERE "
                + "DOCUMENTO='" + objBeanConsultaBeneficiario.getDNI() + "' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanConsultaBeneficiario.setDNI(objResultSet.getString("DOCUMENTO"));
                objBeanConsultaBeneficiario.setBeneficiario(objResultSet.getString("BENEFICIARIO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficiario(objBeanSentencias) : " + e.getMessage());
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
        return objBeanConsultaBeneficiario;
    }

    @Override
    public BeanConsultaBeneficiario getMesaPartes(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJuzgadoSentencia(BeanConsultaBeneficiario objBeanConsultaBeneficiario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
