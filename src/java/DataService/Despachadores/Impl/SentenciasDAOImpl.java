package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanSentencias;
import DataService.Despachadores.SentenciasDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SentenciasDAOImpl implements SentenciasDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanSentencias objBnSentencias;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public SentenciasDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaBuscaPersonal(String personal, String cip) {
        String add = "CIP LIKE '%" + cip.trim() + "%' ";
        if (personal.length() > 0) {
            add = "UPPER(PERSONAL) LIKE UPPER('" + personal.replaceAll(" ", "%") + "%') ";
        }
        lista = new LinkedList<>();
        sql = "SELECT CIP, DNI, PERSONAL, GRADO, SITUACION "
                + "FROM V_PERSONAL WHERE "
                + add
                + "ORDER BY PERSONAL";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setCIP(objResultSet.getString("CIP"));
                objBnSentencias.setDNI(objResultSet.getString("DNI"));
                objBnSentencias.setPersonal(objResultSet.getString("PERSONAL"));
                objBnSentencias.setGrado(objResultSet.getString("GRADO"));
                objBnSentencias.setSituacion(objResultSet.getString("SITUACION"));
                lista.add(objBnSentencias);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaBuscaPersonal() : " + e.getMessage());
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
    public List getListaSentencias(BeanSentencias objBeanSentencias) {
        lista = new LinkedList<>();
        sql = "SELECT NSENTENCIA_CODIGO AS CODIGO, UTIL.FUN_AREA('04', TO_NUMBER(CSENTENCIA_TIPO)) AS AREA, "
                + "UPPER(NVL(VSENTENCIA_MOTIVO,' ')) AS MOTIVO, DSENTENCIA_FECHA_INICIO AS FECHA, "
                + "UTIL.FUN_SENTENCIA_BENEFICIARIO(CPERSONAL_CIP, NSENTENCIA_CODIGO) AS BENEFICIARIO, "
                + "UTIL.FUN_SENTENCIA_EXPEDIENTE(CPERSONAL_CIP, NSENTENCIA_CODIGO) AS EXPEDIENTE, "
                + "UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_SENTENCIAS WHERE "
                + "CPERSONAL_CIP='" + objBeanSentencias.getCIP() + "' "
                + "ORDER BY ESTADO, AREA, CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setSentencia(objResultSet.getInt("CODIGO"));
                objBnSentencias.setUnidad(objResultSet.getString("AREA"));
                objBnSentencias.setOficio(objResultSet.getString("MOTIVO"));
                objBnSentencias.setFecha(objResultSet.getDate("FECHA"));
                objBnSentencias.setJuzgado(objResultSet.getString("EXPEDIENTE"));
                objBnSentencias.setJuez(objResultSet.getString("BENEFICIARIO"));
                objBnSentencias.setSituacion(objResultSet.getString("ESTADO"));
                lista.add(objBnSentencias);
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
    public List getListaResoluciones(BeanSentencias objBeanSentencias) {
        lista = new LinkedList<>();
        sql = "SELECT NRESOLUCION_CODIGO AS RESOLUCION, "
                + "NVL(UTIL.FUN_MESA_PARTE_DOCUMENTO(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO),'SIN DOCUMENTO') AS MESA_PARTES, "
                + "VRESOLUCION_NUMERO AS NUMERO_RESOLUCION, VRESOLUCION_EXPEDIENTE AS EXPEDIENTE, DRESOLUCION_FECHA_EXPEDIENTE AS FECHA, "
                + "CASE WHEN NTIPO_PAGO_CODIGO IN (1,2,5) THEN TO_CHAR(NRESOLUCION_PORCENTAJE,'FM999,999,999,999.009')||' %' "
                + "WHEN NTIPO_PAGO_CODIGO IN (3,4) THEN UTIL.FUN_ABREVIATURA_TIPO_PAGO(NTIPO_PAGO_CODIGO)||' S/ '|| TO_CHAR(NVL(NRESOLUCION_MONTO,0),'FM999,999,999,999.009')  ELSE "
                + "' ' END AS FORMA_PAGO, NTIPO_PAGO_CODIGO, UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_RESOLUCIONES WHERE "
                + "CPERSONAL_CIP='" + objBeanSentencias.getCIP() + "' AND "
                + "NSENTENCIA_CODIGO='" + objBeanSentencias.getSentencia() + "' "
                + "ORDER BY ESTADO, NMESA_PARTES_CORRELATIVO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setResolucion(objResultSet.getInt("RESOLUCION"));
                objBnSentencias.setMesaPartes(objResultSet.getString("MESA_PARTES"));
                objBnSentencias.setNumeroResolucion(objResultSet.getString("NUMERO_RESOLUCION"));
                objBnSentencias.setExpediente(objResultSet.getString("EXPEDIENTE"));
                objBnSentencias.setGrado(objResultSet.getString("FORMA_PAGO"));
                objBnSentencias.setTipoPago(objResultSet.getString("NTIPO_PAGO_CODIGO"));
                objBnSentencias.setFecha(objResultSet.getDate("FECHA"));
                objBnSentencias.setSituacion(objResultSet.getString("ESTADO"));
                lista.add(objBnSentencias);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaResoluciones() : " + e.getMessage());
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
    public List getListaResolucionesActivas(BeanSentencias objBeanSentencias) {
        lista = new LinkedList<>();
        sql = "SELECT CPERSONAL_CIP AS CIP, NSENTENCIA_CODIGO AS SENTENCIA, NRESOLUCION_CODIGO AS RESOLUCION, "
                + "UTIL.FUN_NOMBRE_PERSONAL(CPERSONAL_CIP) AS PERSONAL, UTIL.FUN_AREA('04', TO_NUMBER(CSENTENCIA_TIPO)) AS AREA, "
                + "NVL(UTIL.FUN_MESA_PARTE_DOCUMENTO(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO),'SIN DOCUMENTO') AS MESA_PARTES, "
                + "VRESOLUCION_NUMERO AS NUMERO_RESOLUCION, VRESOLUCION_EXPEDIENTE AS EXPEDIENTE,  "
                + "CASE WHEN NTIPO_PAGO_CODIGO IN (1,2,5) THEN TO_CHAR(NRESOLUCION_PORCENTAJE,'FM999,999,999,999.009')||' %' "
                + "WHEN NTIPO_PAGO_CODIGO IN (3,4) THEN UTIL.FUN_ABREVIATURA_TIPO_PAGO(NTIPO_PAGO_CODIGO)||' S/ '|| TO_CHAR(NVL(NRESOLUCION_MONTO,0),'FM999,999,999,999.009')  ELSE "
                + "' ' END AS FORMA_PAGO, NTIPO_PAGO_CODIGO, UTIL.FUN_SENTENCIA_BENEFICIARIO(CPERSONAL_CIP, NSENTENCIA_CODIGO) AS DEMANDADO "
                + "FROM SISEJE_RESOLUCIONES WHERE "
                + "CESTADO_CODIGO='AC' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setCIP(objResultSet.getString("CIP"));
                objBnSentencias.setSentencia(objResultSet.getInt("SENTENCIA"));
                objBnSentencias.setResolucion(objResultSet.getInt("RESOLUCION"));
                objBnSentencias.setPersonal(objResultSet.getString("PERSONAL"));
                objBnSentencias.setUnidad(objResultSet.getString("AREA"));
                objBnSentencias.setMesaPartes(objResultSet.getString("MESA_PARTES"));
                objBnSentencias.setNumeroResolucion(objResultSet.getString("NUMERO_RESOLUCION"));
                objBnSentencias.setExpediente(objResultSet.getString("EXPEDIENTE"));
                objBnSentencias.setGrado(objResultSet.getString("FORMA_PAGO"));
                objBnSentencias.setTipoPago(objResultSet.getString("NTIPO_PAGO_CODIGO"));
                objBnSentencias.setSituacion(objResultSet.getString("DEMANDADO"));
                lista.add(objBnSentencias);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaResolucionesActivas() : " + e.getMessage());
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
    public List getListaResolucionesDetalle(BeanSentencias objBeanSentencias) {
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
            objPreparedStatement.setString(1, objBeanSentencias.getCIP());
            objPreparedStatement.setInt(2, objBeanSentencias.getSentencia());
            objPreparedStatement.setInt(3, objBeanSentencias.getResolucion());
            objPreparedStatement.setString(4, objBeanSentencias.getCIP());
            objPreparedStatement.setString(5, objBeanSentencias.getTipo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setTipoRemuneracion(objResultSet.getString("CODIGO"));
                objBnSentencias.setOficio(objResultSet.getString("DESCRIPCION"));
                objBnSentencias.setMonto(objResultSet.getDouble("MONTO"));
                lista.add(objBnSentencias);
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
    public List getListaResolucionesMovimientos(BeanSentencias objBeanSentencias) {
        lista = new LinkedList<>();
        sql = "SELECT 1 AS NUMERO, CASE WHEN COUNT(*)=0 THEN 'DESCUENTOS SIN PROCESAR' ELSE 'DESCUENTOS PROCESADOS' END AS DESCRIPCION "
                + "FROM SISEJE_RESOLUCIONES_MOVIMIENTO WHERE "
                + "CPERIODO_CODIGO='" + objBeanSentencias.getPeriodo() + "' AND CMES_CODIGO='" + objBeanSentencias.getMes() + "' AND TO_NUMBER(CSENTENCIA_TIPO)='" + Utiles.Utiles.checkNum(objBeanSentencias.getTipo()) + "' "
                + "UNION ALL "
                + "SELECT 2 AS NUMERO, CASE WHEN NVL(SUM(NRESOLUCION_MOVIMIENTO_PAGO),0)=0 THEN 'DESCUENTOS SIN AFECTAR' ELSE 'DESCUENTOS AFECTADOS' END AS DESCRIPCION "
                + "FROM SISEJE_RESOLUCIONES_MOVIMIENTO WHERE "
                + "CPERIODO_CODIGO='" + objBeanSentencias.getPeriodo() + "' AND CMES_CODIGO='" + objBeanSentencias.getMes() + "' AND TO_NUMBER(CSENTENCIA_TIPO)='" + Utiles.Utiles.checkNum(objBeanSentencias.getTipo()) + "' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setCuotas(objResultSet.getInt("NUMERO"));
                objBnSentencias.setTipoRemuneracion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnSentencias);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaResolucionesMovimientos() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getListaResolucionesProceso(BeanSentencias objBeanSentencias) {
        lista = new LinkedList<>();
        sql = "SELECT MOV.CPERIODO_CODIGO, MOV.CMES_CODIGO, "
                + "CASE MOV.CSENTENCIA_TIPO WHEN '02' THEN '8089' WHEN '03' THEN '9310' ELSE '0000' END AS CRESOLUCION_PROCESO_DESCUENTO, "
                + "MOV.CTIPO_REMUNERACION_CODIGO, MOV.CPERSONAL_CIP, "
                + "SUM(CASE WHEN MOV.NTIPO_PAGO_CODIGO IN (1,2,5) THEN NVL(NRESOLUCION_MOVIMIENTO_MONTO,0) ELSE 0 END) AS PORCENTAJE, "
                + "SUM(CASE WHEN MOV.NTIPO_PAGO_CODIGO IN (3,4) THEN NVL(NRESOLUCION_MOVIMIENTO_MONTO,0) ELSE 0 END) AS MONTO, "
                + "MOV.NSENTENCIA_CODIGO||'-'||MOV.NRESOLUCION_CODIGO AS IDENTIFICADOR "
                + "FROM SISEJE_RESOLUCIONES_MOVIMIENTO MOV INNER JOIN SISEJE_RESOLUCIONES RESOL ON (RESOL.CPERSONAL_CIP=MOV.CPERSONAL_CIP AND "
                + "RESOL.NSENTENCIA_CODIGO=MOV.NSENTENCIA_CODIGO AND RESOL.NRESOLUCION_CODIGO=MOV.NRESOLUCION_CODIGO) WHERE  "
                + "MOV.CPERIODO_CODIGO=? AND "
                + "MOV.CMES_CODIGO=? AND "
                + "MOV.CSENTENCIA_TIPO=?  AND "
                + "NRESOLUCION_MOVIMIENTO_MONTO>0 "
                + "GROUP BY MOV.CPERIODO_CODIGO, MOV.CMES_CODIGO, MOV.CSENTENCIA_TIPO, "
                + "MOV.CPERSONAL_CIP,MOV.NSENTENCIA_CODIGO,MOV.NRESOLUCION_CODIGO, MOV.CTIPO_REMUNERACION_CODIGO "
                + "ORDER BY MOV.CPERSONAL_CIP,MOV.NSENTENCIA_CODIGO,MOV.NRESOLUCION_CODIGO, MOV.CTIPO_REMUNERACION_CODIGO";
        /*sql = "SELECT CPERIODO_CODIGO, CMES_CODIGO, CTIPO_REMUNERACION_CODIGO, "
                + "CRESOLUCION_PROCESO_DESCUENTO, "
                + "CPERSONAL_CIP, SUM(NRESOLUCION_PROCESO_PORCENTAJE) AS PORCENTAJE, "
                + "SUM(NRESOLUCION_PROCESO_MONTO) AS MONTO "
                + "FROM SISEJE_RESOLUCIONES_PROCESO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CSENTENCIA_TIPO=? "
                + "GROUP BY CPERIODO_CODIGO, CMES_CODIGO, CRESOLUCION_PROCESO_DESCUENTO, "
                + "CPERSONAL_CIP, CTIPO_REMUNERACION_CODIGO "
                + "ORDER BY CRESOLUCION_PROCESO_DESCUENTO, CPERSONAL_CIP, CTIPO_REMUNERACION_CODIGO";
         */
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanSentencias.getPeriodo());
            objPreparedStatement.setString(2, objBeanSentencias.getMes());
            objPreparedStatement.setString(3, objBeanSentencias.getTipo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setPeriodo(objResultSet.getString("CPERIODO_CODIGO"));
                objBnSentencias.setMes(objResultSet.getString("CMES_CODIGO"));
                objBnSentencias.setExpediente(objResultSet.getString("CRESOLUCION_PROCESO_DESCUENTO"));
                objBnSentencias.setTipoRemuneracion(objResultSet.getString("CTIPO_REMUNERACION_CODIGO"));
                objBnSentencias.setCIP(objResultSet.getString("CPERSONAL_CIP"));
                objBnSentencias.setPorcentaje(objResultSet.getDouble("PORCENTAJE"));
                objBnSentencias.setMonto(objResultSet.getDouble("MONTO"));
                objBnSentencias.setDNI(objResultSet.getString("IDENTIFICADOR"));
                lista.add(objBnSentencias);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaResolucionesProceso() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getListaResolucionesMovimientoValidacion(BeanSentencias objBeanSentencias) {
        lista = new LinkedList<>();
        /*sql="SELECT CPERIODO_CODIGO, CMES_CODIGO, CSENTENCIA_TIPO,   PLANILLA_MCPP, \n" +
"                COD_ADM, NOMBRE, DNI_DEMANDADO, DESC_CORTA, \n" +
"                TIPO, DNI_DEMAN, CTANUMERO, BENEFICIARIO, TIPO_PAGO, COD_BANCO, BANCO, \n" +
"                SUM(IMPORTE) IMPORTE, MES, SITUACION, COD_DISTRIBUCION, DISTRIBUCION \n" +
"                FROM V_RESOLUCION_MOVIMIENTO WHERE \n" +
"                CPERIODO_CODIGO='2020' AND \n" +
"                CMES_CODIGO='08' AND \n" +
"                CSENTENCIA_TIPO='03' \n" +
"                GROUP BY CPERIODO_CODIGO, CMES_CODIGO, CSENTENCIA_TIPO,   PLANILLA_MCPP, \n" +
"                COD_ADM, NOMBRE, DNI_DEMANDADO, DESC_CORTA, \n" +
"                TIPO, DNI_DEMAN, CTANUMERO, BENEFICIARIO, TIPO_PAGO, COD_BANCO, BANCO, \n" +
"                MES, SITUACION, COD_DISTRIBUCION, DISTRIBUCION\n" +
"                ORDER BY COD_ADM, DNI_DEMAN";
        */
        sql = "SELECT CPERIODO_CODIGO, CMES_CODIGO, CSENTENCIA_TIPO, SENTENCIA, RESOLUCION, PLANILLA_MCPP, "
                + "NUMERO, COD_ADM, NOMBRE, DNI_DEMANDADO, RAZON_SOCIAL, DESC_CORTA, "
                + "TIPO, DNI_DEMAN, CTANUMERO, BENEFICIARIO, TIPO_PAGO, COD_BANCO, BANCO, "
                + "IMPORTE, MES, SITUACION, COD_DISTRIBUCION, DISTRIBUCION "
                + "FROM V_RESOLUCION_MOVIMIENTO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CSENTENCIA_TIPO=? "
                + "ORDER BY COD_ADM, DNI_DEMAN";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanSentencias.getPeriodo());
            objPreparedStatement.setString(2, objBeanSentencias.getMes());
            objPreparedStatement.setString(3, objBeanSentencias.getTipo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setPeriodo(objResultSet.getString("CPERIODO_CODIGO"));
                objBnSentencias.setMes(objResultSet.getString("CMES_CODIGO"));
                objBnSentencias.setArma(objResultSet.getString("CSENTENCIA_TIPO"));
                objBnSentencias.setSentencia(objResultSet.getInt("SENTENCIA"));
                objBnSentencias.setResolucion(objResultSet.getInt("RESOLUCION"));
                objBnSentencias.setCuotas(objResultSet.getInt("PLANILLA_MCPP"));
                objBnSentencias.setUnidad(objResultSet.getString("NUMERO"));
                objBnSentencias.setCIP(objResultSet.getString("COD_ADM"));
                objBnSentencias.setPersonal(objResultSet.getString("NOMBRE"));
                objBnSentencias.setJuez(objResultSet.getString("DNI_DEMANDADO"));
                objBnSentencias.setJuzgado(objResultSet.getString("RAZON_SOCIAL"));
                objBnSentencias.setTipoRemuneracion(objResultSet.getString("DESC_CORTA"));
                objBnSentencias.setTipo(objResultSet.getString("TIPO"));
                objBnSentencias.setExpediente(objResultSet.getString("DNI_DEMAN"));
                objBnSentencias.setNumeroResolucion(objResultSet.getString("CTANUMERO"));
                objBnSentencias.setBeneficiario(objResultSet.getString("BENEFICIARIO"));
                objBnSentencias.setTipoPago(objResultSet.getString("TIPO_PAGO"));
                objBnSentencias.setOficio(objResultSet.getString("COD_BANCO"));
                objBnSentencias.setBanco(objResultSet.getString("BANCO"));
                objBnSentencias.setMonto(objResultSet.getDouble("IMPORTE"));
                objBnSentencias.setSituacion(objResultSet.getString("SITUACION"));
                objBnSentencias.setMesaPartes(objResultSet.getString("COD_DISTRIBUCION"));
                objBnSentencias.setGrado(objResultSet.getString("DISTRIBUCION"));
                lista.add(objBnSentencias);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaResolucionesMovimientoValidacion() : " + e.getMessage());
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
    public List getListaResolucionesPlanillaMCPP(BeanSentencias objBeanSentencias) {
        lista = new LinkedList<>();
        sql = "SELECT COD_ADM, SENTENCIA, RESOLUCION, NOMBRE, DNI_DEMANDADO, RAZON_SOCIAL, DESC_CORTA, "
                + "TIPO, DNI_DEMAN, CTANUMERO, BENEFICIARIO, TIPO_PAGO, BANCO, IMPORTE, MES, SITUACION, DISTRIBUCION "
                + "FROM V_RESOLUCION_MOVIMIENTO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CSENTENCIA_TIPO=?  AND "
                + "COD_DISTRIBUCION=? AND "
                + "PLANILLA_MCPP=? "
                + "ORDER BY COD_ADM, DNI_DEMAN";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanSentencias.getPeriodo());
            objPreparedStatement.setString(2, objBeanSentencias.getMes());
            objPreparedStatement.setString(3, objBeanSentencias.getTipo());
            objPreparedStatement.setString(4, objBeanSentencias.getTipoRemuneracion());
            objPreparedStatement.setString(5, objBeanSentencias.getUnidad());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnSentencias = new BeanSentencias();
                objBnSentencias.setCIP(objResultSet.getString("COD_ADM"));
                objBnSentencias.setSentencia(objResultSet.getInt("SENTENCIA"));
                objBnSentencias.setResolucion(objResultSet.getInt("RESOLUCION"));
                objBnSentencias.setPersonal(objResultSet.getString("NOMBRE"));
                objBnSentencias.setJuez(objResultSet.getString("DNI_DEMANDADO"));
                objBnSentencias.setJuzgado(objResultSet.getString("RAZON_SOCIAL"));
                objBnSentencias.setTipoRemuneracion(objResultSet.getString("DESC_CORTA"));
                objBnSentencias.setTipo(objResultSet.getString("TIPO"));
                objBnSentencias.setExpediente(objResultSet.getString("DNI_DEMAN"));
                objBnSentencias.setNumeroResolucion(objResultSet.getString("CTANUMERO"));
                objBnSentencias.setArma(objResultSet.getString("BENEFICIARIO"));
                objBnSentencias.setTipoPago(objResultSet.getString("TIPO_PAGO"));
                objBnSentencias.setOficio(objResultSet.getString("BANCO"));
                objBnSentencias.setMonto(objResultSet.getDouble("IMPORTE"));
                objBnSentencias.setSituacion(objResultSet.getString("SITUACION"));
                objBnSentencias.setGrado(objResultSet.getString("DISTRIBUCION"));
                lista.add(objBnSentencias);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaResolucionesPlanillaMCPP() : " + e.getMessage());
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
    public BeanSentencias getPersonal(BeanSentencias objBeanSentencias) {
        sql = "SELECT CIP, DNI, PERSONAL, GRADO, ARMA, UNIDAD, SITUACION "
                + "FROM V_PERSONAL WHERE "
                + "CIP='" + objBeanSentencias.getCIP() + "' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanSentencias.setCIP(objResultSet.getString("CIP"));
                objBeanSentencias.setDNI(objResultSet.getString("DNI"));
                objBeanSentencias.setPersonal(objResultSet.getString("PERSONAL"));
                objBeanSentencias.setGrado(objResultSet.getString("GRADO"));
                objBeanSentencias.setArma(objResultSet.getString("ARMA"));
                objBeanSentencias.setUnidad(objResultSet.getString("UNIDAD"));
                objBeanSentencias.setSituacion(objResultSet.getString("SITUACION"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPersonal(objBeanSentencias) : " + e.getMessage());
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
        return objBeanSentencias;
    }

    @Override
    public BeanSentencias getSentencias(BeanSentencias objBeanSentencias) {
        sql = "SELECT VSENTENCIA_MOTIVO AS MOTIVO, DSENTENCIA_FECHA_INICIO AS FECHA "
                + "FROM SISEJE_SENTENCIAS WHERE "
                + "CPERSONAL_CIP='" + objBeanSentencias.getCIP() + "' AND "
                + "NSENTENCIA_CODIGO='" + objBeanSentencias.getSentencia() + "' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanSentencias.setOficio(objResultSet.getString("MOTIVO"));
                objBeanSentencias.setFecha(objResultSet.getDate("FECHA"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getSentencias(objBeanSentencias) : " + e.getMessage());
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
        return objBeanSentencias;
    }

    @Override
    public BeanSentencias getResoluciones(BeanSentencias objBeanSentencias) {
        sql = "SELECT CPERIODO_CODIGO, NMESA_PARTES_CORRELATIVO||'-'||NMESA_PARTES_DECRETO AS CORRELATIVO, "
                + "NMESA_PARTES_CORRELATIVO||'-'||NMESA_PARTES_DECRETO||':'||UTIL.FUN_MESA_PARTE_DOCUMENTO(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO) AS MESA_PARTES,  "
                + "VRESOLUCION_NUMERO, VRESOLUCION_EXPEDIENTE, DRESOLUCION_FECHA_EXPEDIENTE, VRESOLUCION_OFICIO, DRESOLUCION_FECHA_OFICIO, "
                + "VRESOLUCION_JUEZ, NJUZGADO_CODIGO "
                + "FROM SISEJE_RESOLUCIONES WHERE "
                + "CPERSONAL_CIP='" + objBeanSentencias.getCIP() + "' AND "
                + "NSENTENCIA_CODIGO='" + objBeanSentencias.getSentencia() + "' AND "
                + "NRESOLUCION_CODIGO='" + objBeanSentencias.getResolucion() + "' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanSentencias.setPeriodo(objResultSet.getString("CPERIODO_CODIGO"));
                objBeanSentencias.setMesaPartes(objResultSet.getString("CORRELATIVO"));
                objBeanSentencias.setMes(objResultSet.getString("MESA_PARTES"));
                objBeanSentencias.setNumeroResolucion(objResultSet.getString("VRESOLUCION_NUMERO"));
                objBeanSentencias.setExpediente(objResultSet.getString("VRESOLUCION_EXPEDIENTE"));
                objBeanSentencias.setFecha(objResultSet.getDate("DRESOLUCION_FECHA_EXPEDIENTE"));
                objBeanSentencias.setOficio(objResultSet.getString("VRESOLUCION_OFICIO"));
                objBeanSentencias.setFechaFin(objResultSet.getDate("DRESOLUCION_FECHA_OFICIO"));
                objBeanSentencias.setJuez(objResultSet.getString("VRESOLUCION_JUEZ"));
                objBeanSentencias.setJuzgado(objResultSet.getString("NJUZGADO_CODIGO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getResoluciones(objBeanSentencias) : " + e.getMessage());
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
        return objBeanSentencias;
    }

    @Override
    public BeanSentencias getResolucionesDetalle(BeanSentencias objBeanSentencias) {
        sql = "SELECT NTIPO_PAGO_CODIGO, NRESOLUCION_CUOTAS, NRESOLUCION_MONTO, NRESOLUCION_PORCENTAJE, NRESOLUCION_TOTAL, CTIPO_MONEDA_CODIGO "
                + "FROM SISEJE_RESOLUCIONES WHERE "
                + "CPERSONAL_CIP='" + objBeanSentencias.getCIP() + "' AND "
                + "NSENTENCIA_CODIGO='" + objBeanSentencias.getSentencia() + "' AND "
                + "NRESOLUCION_CODIGO='" + objBeanSentencias.getResolucion() + "' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanSentencias.setTipoPago(objResultSet.getString("NTIPO_PAGO_CODIGO"));
                objBeanSentencias.setCuotas(objResultSet.getInt("NRESOLUCION_CUOTAS"));
                objBeanSentencias.setMonto(objResultSet.getDouble("NRESOLUCION_MONTO"));
                objBeanSentencias.setPorcentaje(objResultSet.getDouble("NRESOLUCION_PORCENTAJE"));
                objBeanSentencias.setTotal(objResultSet.getDouble("NRESOLUCION_TOTAL"));
                objBeanSentencias.setTipoMoneda(objResultSet.getString("CTIPO_MONEDA_CODIGO"));
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
        return objBeanSentencias;
    }

    @Override
    public BeanSentencias getBeneficiario(BeanSentencias objBeanSentencias) {
        sql = "SELECT TRIM(RESOL.NBENEFICIARIO_TIPO_DOCUMENTO) AS TIPO_DOCUMENTO, RESOL.VBENEFICIARIO_DOCUMENTO AS DOCUMENTO, "
                + "NVL(RESOL.CRESOLUCION_BENEFICIARIO_TIPO, '0') AS TIPO, "
                + "CUENTA.NBANCO_CODIGO, NVL(CUENTA.VBENEFICIARIO_CUENTA_NUMERO,'') AS CUENTA, NVL(CUENTA.VBENEFICIARIO_CUENTA_CCI,'') AS CCI "
                + "FROM SISEJE_RESOLUCIONES_BENEFICIAR RESOL LEFT OUTER JOIN SISEJE_BENEFICIARIO_CUENTA CUENTA ON ("
                + "RESOL.VBENEFICIARIO_DOCUMENTO=CUENTA.VBENEFICIARIO_DOCUMENTO AND "
                + "RESOL.NBENEFICIARIO_CUENTA_CODIGO=CUENTA.NBENEFICIARIO_CUENTA_CODIGO) WHERE "
                + "RESOL.CPERSONAL_CIP='" + objBeanSentencias.getCIP() + "' AND "
                + "RESOL.NSENTENCIA_CODIGO='" + objBeanSentencias.getSentencia() + "' AND "
                + "RESOL.NRESOLUCION_CODIGO='" + objBeanSentencias.getResolucion() + "' AND "
                + "RESOL.CESTADO_CODIGO='AC' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            objBeanSentencias.setTipoRemuneracion("0");
            objBeanSentencias.setOficio("");
            objBeanSentencias.setTipoPago("0");
            objBeanSentencias.setGrado("0");
            objBeanSentencias.setUnidad("");
            objBeanSentencias.setJuez("");
            if (objResultSet.next()) {
                objBeanSentencias.setTipoRemuneracion(objResultSet.getString("TIPO_DOCUMENTO"));
                objBeanSentencias.setOficio(objResultSet.getString("DOCUMENTO"));
                objBeanSentencias.setTipoPago(objResultSet.getString("TIPO"));
                objBeanSentencias.setGrado(objResultSet.getString("NBANCO_CODIGO"));
                objBeanSentencias.setUnidad(objResultSet.getString("CUENTA"));
                objBeanSentencias.setJuez(objResultSet.getString("CCI"));
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
        return objBeanSentencias;
    }

    @Override
    public BeanSentencias getMesaPartes(BeanSentencias objBeanSentencias, String periodo, String correlativo) {
        sql = "SELECT "
                + "VMESA_PARTES_DOCUMENTO||' '||VMESA_PARTES_INDICATIVO AS DOCUMENTO, "
                + "DMESA_PARTES_FECHA AS FECHA, NJUZGADO_CODIGO AS JUZGADO, VMESA_PARTES_POST_FIRMA AS JUEZ, "
                + "UTIL.FUN_DECRETO_OBSERVACION(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO) AS OBSERVACION, "
                + "VMESA_PARTES_DIGITAL AS ARCHIVO "
                + "FROM SISEJE_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND  "
                + "CMESA_PARTES_TIPO='I' AND  "
                + "NMESA_PARTES_CORRELATIVO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, correlativo.substring(0, correlativo.lastIndexOf("-")));
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanSentencias.setOficio(objResultSet.getString("DOCUMENTO"));
                objBeanSentencias.setFecha(objResultSet.getDate("FECHA"));
                objBeanSentencias.setJuzgado(objResultSet.getString("JUZGADO"));
                objBeanSentencias.setJuez(objResultSet.getString("JUEZ"));
                objBeanSentencias.setGrado(objResultSet.getString("OBSERVACION"));
                objBeanSentencias.setArma(objResultSet.getString("ARCHIVO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMesaPartes(objBeanSentencias) : " + e.getMessage());
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
        return objBeanSentencias;
    }

    @Override
    public String getJuzgadoSentencia(BeanSentencias objBeanSentencias) {
        String result = "";
        sql = "SELECT VRESOLUCION_JUZGADO AS JUZGADO "
                + "FROM SISEJE_RESOLUCIONES WHERE "
                + "CPERSONAL_CIP='" + objBeanSentencias.getCIP() + "' AND "
                + "NSENTENCIA_CODIGO='" + objBeanSentencias.getSentencia() + "' AND "
                + "NRESOLUCION_CODIGO='" + objBeanSentencias.getResolucion() + "' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("JUZGADO");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getJuzgadoSentencia(objBeanSentencias) : " + e.getMessage());
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
        return result;
    }

    @Override
    public int iduSentencias(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_SENTENCIAS(?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getCIP());
            cs.setString(2, objBeanSentencias.getTipo());
            cs.setInt(3, objBeanSentencias.getSentencia());
            cs.setString(4, objBeanSentencias.getOficio());
            cs.setDate(5, objBeanSentencias.getFecha());
            cs.setString(6, usuario);
            cs.setString(7, objBeanSentencias.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduSentencias : " + e.getMessage());
            return 0;
        }
        return s;
    }

    @Override
    public int iduResoluciones(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_RESOLUCIONES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getCIP());
            cs.setString(2, objBeanSentencias.getTipo());
            cs.setInt(3, objBeanSentencias.getSentencia());
            cs.setInt(4, objBeanSentencias.getResolucion());
            cs.setString(5, objBeanSentencias.getPeriodo());
            cs.setString(6, "I");
            cs.setString(7, objBeanSentencias.getMesaPartes().trim());
            cs.setString(8, objBeanSentencias.getExpediente());
            cs.setDate(9, objBeanSentencias.getFecha());
            cs.setString(10, objBeanSentencias.getOficio());
            cs.setDate(11, objBeanSentencias.getFechaFin());
            cs.setString(12, objBeanSentencias.getJuez());
            cs.setString(13, objBeanSentencias.getJuzgado());
            cs.setString(14, usuario);
            cs.setString(15, objBeanSentencias.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduResoluciones : " + e.getMessage());
            return 0;
        }
        return s;
    }

    @Override
    public int iduResolucionesBeneficiario(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_RESOLUCIONES_BENEFICIAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getCIP());
            cs.setString(2, objBeanSentencias.getTipo());
            cs.setInt(3, objBeanSentencias.getSentencia());
            cs.setInt(4, objBeanSentencias.getResolucion());
            cs.setString(5, objBeanSentencias.getNumeroResolucion());
            cs.setString(6, objBeanSentencias.getTipoRemuneracion());
            cs.setString(7, objBeanSentencias.getExpediente());
            cs.setString(8, objBeanSentencias.getOficio());
            cs.setString(9, objBeanSentencias.getJuez());
            cs.setString(10, objBeanSentencias.getSituacion());
            cs.setDate(11, objBeanSentencias.getFecha());
            cs.setString(12, objBeanSentencias.getPersonal());
            cs.setString(13, objBeanSentencias.getMes());
            cs.setString(14, objBeanSentencias.getJuzgado());
            cs.setString(15, objBeanSentencias.getTipoPago());
            cs.setString(16, objBeanSentencias.getArma());
            cs.setString(17, usuario);
            cs.setString(18, objBeanSentencias.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduResolucionesBeneficiario : " + e.getMessage());
            return 0;
        }
        return s;
    }

    @Override
    public int iduResolucionesDetalle(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_RESOLUCIONES_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getCIP());
            cs.setString(2, objBeanSentencias.getTipo());
            cs.setInt(3, objBeanSentencias.getSentencia());
            cs.setInt(4, objBeanSentencias.getResolucion());
            cs.setString(5, objBeanSentencias.getTipoPago());
            cs.setString(6, objBeanSentencias.getTipoMoneda());
            cs.setDouble(7, objBeanSentencias.getPorcentaje());
            cs.setInt(8, objBeanSentencias.getCuotas());
            cs.setDouble(9, objBeanSentencias.getRemuneracion());
            cs.setDouble(10, objBeanSentencias.getTotal());
            cs.setString(11, objBeanSentencias.getTipoRemuneracion());
            cs.setDouble(12, objBeanSentencias.getMonto());
            cs.setString(13, usuario);
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduResolucionesDetalle : " + e.getMessage());
            return 0;
        }
        return s;
    }

    @Override
    public int iduCerrarResoluciones(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_RESOLUCIONES_CERRAR(?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getCIP());
            cs.setInt(2, objBeanSentencias.getSentencia());
            cs.setInt(3, objBeanSentencias.getResolucion());
            cs.setString(4, usuario);
            cs.setString(5, objBeanSentencias.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduCerrarResoluciones : " + e.getMessage());
            return 0;
        }
        return s;
    }

    @Override
    public String iduResolucionesPlanilla(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_RESOLUCIONES_PLANILLA(?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getPeriodo());
            cs.setString(2, objBeanSentencias.getMes());
            cs.setString(3, objBeanSentencias.getCIP());
            cs.setString(4, objBeanSentencias.getTipo());
            cs.setInt(5, objBeanSentencias.getSentencia());
            cs.setInt(6, objBeanSentencias.getResolucion());
            cs.setString(7, objBeanSentencias.getTipoRemuneracion());
            cs.setInt(8, objBeanSentencias.getCuotas());
            cs.setString(9, usuario);
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduResolucionesPlanilla : " + e.getMessage());
            return e.getMessage();
        }
        return "GUARDO";
    }

    @Override
    public String iduResolucionesMovimientos(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_RESOLUCIONES_MOVIMIENTO(?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getPeriodo());
            cs.setString(2, objBeanSentencias.getMes());
            cs.setString(3, objBeanSentencias.getTipo());
            cs.setDouble(4, objBeanSentencias.getTipoCambio());
            cs.setString(5, usuario);
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduResolucionesMovimientos : " + e.getMessage());
            return e.getMessage();
        }
        return "GUARDO";
    }

    @Override
    public String iduResolucionesProcesoDescuentos(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_RESOLUCIONES_RETORNO(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getPeriodo());
            cs.setString(2, objBeanSentencias.getMes());
            cs.setString(3, objBeanSentencias.getCIP());
            cs.setString(4, objBeanSentencias.getTipo());
            cs.setString(5, objBeanSentencias.getTipoPago());
            cs.setString(6, objBeanSentencias.getTipoRemuneracion());
            cs.setDouble(7, objBeanSentencias.getMonto());
            cs.setDouble(8, objBeanSentencias.getRemuneracion());
            cs.setString(9, objBeanSentencias.getJuez());
            cs.setDouble(10, objBeanSentencias.getTipoCambio());
            cs.setString(11, usuario);
            cs.setString(12, objBeanSentencias.getMode());
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduResolucionesProcesoDescuentos : " + e.getMessage());
            return e.getMessage();
        }
        return null;
    }

    @Override
    public String iduResolucionesProceso(BeanSentencias objBeanSentencias, String usuario) {
        sql = "{CALL SP_IDU_RESOLUCIONES_PROCESO(?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanSentencias.getPeriodo());
            cs.setString(2, objBeanSentencias.getMes());
            cs.setString(3, objBeanSentencias.getTipo());
            cs.setString(4, usuario);
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduResolucionesProceso : " + e.getMessage());
            return e.getMessage();
        }
        return null;
    }
}
