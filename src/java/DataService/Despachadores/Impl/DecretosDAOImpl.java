package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanMesaPartes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import DataService.Despachadores.DecretosDAO;

public class DecretosDAOImpl implements DecretosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanMesaPartes objBnMesaPartes;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public DecretosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaDecretosPendientes(BeanMesaPartes objBeanDecreto) {
        lista = new LinkedList<>();
        sql = "SELECT NMESA_PARTES_CORRELATIVO AS CODIGO, LPAD(NMESA_PARTES_CORRELATIVO,6,0) AS NUMERO, "
                + "UTIL.FUN_TIPO_DOCUMENTO(NTIPO_DOCUMENTO_CODIGO)||'-'||VMESA_PARTES_DOCUMENTO AS DOCUMENTO, "
                + "VMESA_PARTES_ASUNTO AS ASUNTO, DMESA_PARTES_FECHA AS FECHA, "
                + "UTIL.FUN_PRIORIDAD(NPRIORIDAD_CODIGO) AS PRIORIDAD, VMESA_PARTES_POST_FIRMA AS FIRMA, "
                + "VMESA_PARTES_DIGITAL AS ARCHIVO, UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_MESA_PARTES WHERE "
                + "CMESA_PARTES_TIPO='I' AND "
                + "CPERIODO_CODIGO=? AND "
                + "TO_CHAR(DMESA_PARTES_RECEPCION,'MM')=? AND "
                + "CESTADO_CODIGO = ? "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getMes());
            objPreparedStatement.setString(3, objBeanDecreto.getEstado());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaPartes = new BeanMesaPartes();
                objBnMesaPartes.setCodigo(objResultSet.getString("CODIGO"));
                objBnMesaPartes.setNumero(objResultSet.getString("NUMERO"));
                objBnMesaPartes.setTipoDocumento(objResultSet.getString("DOCUMENTO").toUpperCase());
                objBnMesaPartes.setAsunto(objResultSet.getString("ASUNTO").toUpperCase());
                objBnMesaPartes.setFechaDocumento(objResultSet.getDate("FECHA"));
                objBnMesaPartes.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnMesaPartes.setPostFirma(objResultSet.getString("FIRMA").toUpperCase());
                objBnMesaPartes.setArchivo(objResultSet.getString("ARCHIVO"));
                objBnMesaPartes.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnMesaPartes);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaDecretosPendientes(objBeanDecreto) : " + e.getMessage());
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
    public List getListaDecretosDecretados(BeanMesaPartes objBeanDecreto) {
        lista = new LinkedList<>();
        sql = "SELECT LPAD(NMESA_PARTES_CORRELATIVO,6,0) AS NUMERO, "
                + "UTIL.FUN_MESA_PARTE_DOCUMENTO(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO) AS DOCUMENTO, "
                + "DMESA_PARTES_DECRETO_FECHA AS FECHA, UPPER(VMESA_PARTES_DECRETO_OBSERVACI) AS OBSERVACION, "
                + "UTIL.FUN_DECRETO_TIPO_DECRETO(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO) AS DECRETO, "
                + "UTIL.FUN_DECRETO_AREA(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO) AS AREA, "
                + "UTIL.FUN_PRIORIDAD(NPRIORIDAD_CODIGO) AS PRIORIDAD "
                + "FROM SISEJE_MESA_PARTES_DECRETO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTES_TIPO='I' AND "
                + "TO_CHAR(DMESA_PARTES_DECRETO_FECHA,'MM')=? AND "
                + "CESTADO_CODIGO!='AN' "
                + "GROUP BY CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO, DMESA_PARTES_DECRETO_FECHA, "
                + "VMESA_PARTES_DECRETO_OBSERVACI, NPRIORIDAD_CODIGO "
                + "ORDER BY NUMERO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getMes());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaPartes = new BeanMesaPartes();
                objBnMesaPartes.setNumero(objResultSet.getString("NUMERO"));
                objBnMesaPartes.setTipoDocumento(objResultSet.getString("DOCUMENTO"));
                objBnMesaPartes.setFechaDocumento(objResultSet.getDate("FECHA"));
                objBnMesaPartes.setDecreto(objResultSet.getString("DECRETO"));
                objBnMesaPartes.setComentario(objResultSet.getString("OBSERVACION"));
                objBnMesaPartes.setArea(objResultSet.getString("AREA"));
                objBnMesaPartes.setPrioridad(objResultSet.getString("PRIORIDAD"));
                lista.add(objBnMesaPartes);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaDecretosDecretados(objBeanDecreto) : " + e.getMessage());
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
    public List getListaConsultaDecretosPendientes(BeanMesaPartes objBeanDecreto) {
        lista = new LinkedList<>();
        sql = "SELECT DECRETO.NMESA_PARTES_DECRETO AS CODIGO, LPAD(DECRETO.NMESA_PARTES_CORRELATIVO,6,0) AS NUMERO, "
                + "UTIL.FUN_MESA_PARTE_DOCUMENTO(DECRETO.CPERIODO_CODIGO, DECRETO.CMESA_PARTES_TIPO, DECRETO.NMESA_PARTES_CORRELATIVO) AS DOCUMENTO, "
                + "DECRETO.DMESA_PARTES_DECRETO_FECHA AS FECHA, DECRETO.VMESA_PARTES_DECRETO_OBSERVACI AS OBSERVACION, "
                + "UTIL.FUN_DECRETO_TIPO_DECRETO(DECRETO.CPERIODO_CODIGO, DECRETO.CMESA_PARTES_TIPO, DECRETO.NMESA_PARTES_CORRELATIVO) AS DECRETO, "
                + "UTIL.FUN_PRIORIDAD(DECRETO.NPRIORIDAD_CODIGO) AS PRIORIDAD, "
                + "MESA_PARTES.VMESA_PARTES_DIGITAL AS DIGITAL "
                + "FROM SISEJE_MESA_PARTES_DECRETO DECRETO INNER JOIN SISEJE_MESA_PARTES MESA_PARTES ON ("
                + "DECRETO.CPERIODO_CODIGO=MESA_PARTES.CPERIODO_CODIGO AND "
                + "DECRETO.CMESA_PARTES_TIPO=MESA_PARTES.CMESA_PARTES_TIPO AND "
                + "DECRETO.NMESA_PARTES_CORRELATIVO=MESA_PARTES.NMESA_PARTES_CORRELATIVO) WHERE "
                + "DECRETO.CPERIODO_CODIGO LIKE ? AND "
                + "DECRETO.CMESA_PARTES_TIPO='I' AND "
                + "TO_CHAR(DECRETO.DMESA_PARTES_DECRETO_FECHA,'MM') LIKE ? AND "
                + "DECRETO.CSECCION_CODIGO=? AND "
                + "DECRETO.NAREA_CODIGO=? AND "
                + "DECRETO.CESTADO_CODIGO='AC' AND "
                + "DECRETO.CESTADO_CODIGO!='AN' "
                + "ORDER BY NUMERO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo().replace("00", "%"));
            objPreparedStatement.setString(2, objBeanDecreto.getMes().replace("00", "%"));
            objPreparedStatement.setString(3, objBeanDecreto.getSeccion());
            objPreparedStatement.setString(4, objBeanDecreto.getArea());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaPartes = new BeanMesaPartes();
                objBnMesaPartes.setCodigo(objResultSet.getString("CODIGO"));
                objBnMesaPartes.setNumero(objResultSet.getString("NUMERO"));
                objBnMesaPartes.setTipoDocumento(objResultSet.getString("DOCUMENTO"));
                objBnMesaPartes.setFechaDocumento(objResultSet.getDate("FECHA"));
                objBnMesaPartes.setDecreto(objResultSet.getString("DECRETO"));
                objBnMesaPartes.setComentario(objResultSet.getString("OBSERVACION"));
                objBnMesaPartes.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnMesaPartes.setArchivo(objResultSet.getString("DIGITAL"));
                lista.add(objBnMesaPartes);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaConsultaDecretos(objBeanDecreto) : " + e.getMessage());
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
    public List getListaConsultaDecretosRespondidos(BeanMesaPartes objBeanDecreto) {
        lista = new LinkedList<>();
        sql = "SELECT RESPUESTA. NMESA_PARTES_RESPUESTA_CODIGO AS CODIGO, LPAD(DECRETO.NMESA_PARTES_CORRELATIVO,6,0) AS NUMERO, "
                + "UTIL.FUN_MESA_PARTE_DOCUMENTO(DECRETO.CPERIODO_CODIGO, DECRETO.CMESA_PARTES_TIPO, DECRETO.NMESA_PARTES_CORRELATIVO) AS DOCUMENTO, "
                + "DECRETO.DMESA_PARTES_DECRETO_FECHA AS FECHA, DECRETO.VMESA_PARTES_DECRETO_OBSERVACI AS OBSERVACION, "
                + "UTIL.FUN_DECRETO_TIPO_DECRETO(DECRETO.CPERIODO_CODIGO, DECRETO.CMESA_PARTES_TIPO, DECRETO.NMESA_PARTES_CORRELATIVO) AS DECRETO, "
                + "UTIL.FUN_TIPO_DOCUMENTO(RESPUESTA.NTIPO_DOCUMENTO_CODIGO)||'-'||RESPUESTA.VMESA_PARTES_RESPUESTA_NUMERO||' '||VMESA_PARTES_RESPUESTA_ASUNTO AS RESPUESTA,"
                + "RESPUESTA.DMESA_PARTES_RESPUESTA_FECHA AS FECHA_RESPUESTA "
                + "FROM SISEJE_MESA_PARTES_DECRETO DECRETO INNER JOIN SISEJE_MESA_PARTES MESA_PARTES ON ("
                + "DECRETO.CPERIODO_CODIGO=MESA_PARTES.CPERIODO_CODIGO AND "
                + "DECRETO.CMESA_PARTES_TIPO=MESA_PARTES.CMESA_PARTES_TIPO AND "
                + "DECRETO.NMESA_PARTES_CORRELATIVO=MESA_PARTES.NMESA_PARTES_CORRELATIVO) INNER JOIN SISEJE_MESA_PARTES_RESPUESTA RESPUESTA ON "
                + "(DECRETO.CPERIODO_CODIGO=RESPUESTA.CPERIODO_CODIGO AND "
                + "DECRETO.CMESA_PARTES_TIPO=RESPUESTA.CMESA_PARTES_TIPO AND "
                + "DECRETO.NMESA_PARTES_CORRELATIVO=RESPUESTA.NMESA_PARTES_CORRELATIVO AND "
                + "DECRETO.NMESA_PARTES_DECRETO=RESPUESTA.NMESA_PARTES_DECRETO) WHERE "
                + "DECRETO.CPERIODO_CODIGO LIKE ? AND "
                + "DECRETO.CMESA_PARTES_TIPO='I' AND "
                + "TO_CHAR(DECRETO.DMESA_PARTES_DECRETO_FECHA,'MM') LIKE ? AND "
                + "DECRETO.NAREA_CODIGO=? AND "
                + "DECRETO.CESTADO_CODIGO!='AN' "
                + "ORDER BY NUMERO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo().replace("00", "%"));
            objPreparedStatement.setString(2, objBeanDecreto.getMes().replace("00", "%"));
            objPreparedStatement.setString(3, objBeanDecreto.getArea());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaPartes = new BeanMesaPartes();
                objBnMesaPartes.setCodigo(objResultSet.getString("CODIGO"));
                objBnMesaPartes.setNumero(objResultSet.getString("NUMERO"));
                objBnMesaPartes.setTipoDocumento(objResultSet.getString("DOCUMENTO"));
                objBnMesaPartes.setFechaDocumento(objResultSet.getDate("FECHA"));
                objBnMesaPartes.setDecreto(objResultSet.getString("DECRETO"));
                objBnMesaPartes.setComentario(objResultSet.getString("OBSERVACION"));
                objBnMesaPartes.setPrioridad(objResultSet.getString("RESPUESTA"));
                objBnMesaPartes.setFechaRecepcion(objResultSet.getDate("FECHA_RESPUESTA"));
                lista.add(objBnMesaPartes);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaConsultaDecretosRespondidos(objBeanDecreto) : " + e.getMessage());
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
    public List getListaSeguimientoDecretos(BeanMesaPartes objBeanDecreto) {
        lista = new LinkedList<>();
        sql = "SELECT  "
                + "UTIL.FUN_MESA_PARTE_DOCUMENTO(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO) AS DOCUMENTO, "
                + "DMESA_PARTES_DECRETO_FECHA AS FECHA, VMESA_PARTES_DECRETO_OBSERVACI AS OBSERVACION, "
                + "UTIL.FUN_DECRETO_TIPO_DECRETO(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO) AS DECRETO, "
                + "UTIL.FUN_AREA(CSECCION_CODIGO, NAREA_CODIGO) AS AREA, "
               /* + "UTIL.FUN_DECRETO_AREA(CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO) AS AREA, "*/
                + "UTIL.FUN_PRIORIDAD(NPRIORIDAD_CODIGO) AS PRIORIDAD, UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_MESA_PARTES_DECRETO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTES_TIPO='I' AND "
                + "NMESA_PARTES_CORRELATIVO=? AND "
                + "CESTADO_CODIGO!='AN' "
                + "GROUP BY CPERIODO_CODIGO, CMESA_PARTES_TIPO, NMESA_PARTES_CORRELATIVO, DMESA_PARTES_DECRETO_FECHA,"
                + "CSECCION_CODIGO, NAREA_CODIGO, CESTADO_CODIGO, NPRIORIDAD_CODIGO, VMESA_PARTES_DECRETO_OBSERVACI  "
                + "ORDER BY DMESA_PARTES_DECRETO_FECHA ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getNumero());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaPartes = new BeanMesaPartes();
                objBnMesaPartes.setTipoDocumento(objResultSet.getString("DOCUMENTO"));
                objBnMesaPartes.setFechaDocumento(objResultSet.getDate("FECHA"));
                objBnMesaPartes.setDecreto(objResultSet.getString("DECRETO"));
                objBnMesaPartes.setComentario(objResultSet.getString("OBSERVACION"));
                objBnMesaPartes.setArea(objResultSet.getString("AREA"));
                objBnMesaPartes.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnMesaPartes.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnMesaPartes);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaSeguimientoDecretos(objBeanDecreto) : " + e.getMessage());
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
    public BeanMesaPartes getDecretos(BeanMesaPartes objBeanDecreto) {
        sql = "SELECT CSECCION_CODIGO, DMESA_PARTES_DECRETO_FECHA, "
                + "VMESA_PARTES_DECRETO_OBSERVACI, NPRIORIDAD_CODIGO "
                + "FROM SISEJE_MESA_PARTES_DECRETO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTES_TIPO='I' AND "
                + "NMESA_PARTES_CORRELATIVO=? AND "
                + "CESTADO_CODIGO!='AN' "
                + "GROUP BY CSECCION_CODIGO, DMESA_PARTES_DECRETO_FECHA, "
                + "VMESA_PARTES_DECRETO_OBSERVACI, NPRIORIDAD_CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getNumero());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanDecreto.setSeccion(objResultSet.getString("CSECCION_CODIGO"));
                objBeanDecreto.setFechaDocumento(objResultSet.getDate("DMESA_PARTES_DECRETO_FECHA"));
                objBeanDecreto.setComentario(objResultSet.getString("VMESA_PARTES_DECRETO_OBSERVACI"));
                objBeanDecreto.setPrioridad(objResultSet.getString("NPRIORIDAD_CODIGO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDecretos(objBeanDecreto) : " + e.getMessage());
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
        return objBeanDecreto;
    }

    @Override
    public BeanMesaPartes getDecretosRespuesta(BeanMesaPartes objBeanDecreto) {
        sql = "SELECT NTIPO_DOCUMENTO_CODIGO, VMESA_PARTES_RESPUESTA_NUMERO, DMESA_PARTES_RESPUESTA_FECHA, VMESA_PARTES_RESPUESTA_ASUNTO "
                + "FROM SISEJE_MESA_PARTES_RESPUESTA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NMESA_PARTES_RESPUESTA_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanDecreto.setTipoDocumento(objResultSet.getString("NTIPO_DOCUMENTO_CODIGO"));
                objBeanDecreto.setNumeroDocumento(objResultSet.getString("VMESA_PARTES_RESPUESTA_NUMERO"));
                objBeanDecreto.setFechaDocumento(objResultSet.getDate("DMESA_PARTES_RESPUESTA_FECHA"));
                objBeanDecreto.setAsunto(objResultSet.getString("VMESA_PARTES_RESPUESTA_ASUNTO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDecretosRespuesta(objBeanDecreto) : " + e.getMessage());
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
        return objBeanDecreto;
    }

    @Override
    public String getDecretosAreasDetalle(BeanMesaPartes objBeanDecreto) {        
        String result = "";
        sql = "SELECT DISTINCT NAREA_CODIGO AS CODIGO "
                + "FROM SISEJE_MESA_PARTES_DECRETO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTES_TIPO='I' AND "
                + "NMESA_PARTES_CORRELATIVO=? AND "
                + "CESTADO_CODIGO!='AN'";       
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getNumero());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                result += objResultSet.getString("CODIGO") + "+++";
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDecretosAreasDetalle(objBeanDecreto) : " + e.getMessage());
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
    public String getDecretosTipoDecretos(BeanMesaPartes objBeanDecreto) {
        String result = "";
        sql = "SELECT DISTINCT TIPO.NTIPO_DECRETO_CODIGO AS CODIGO "
                + "FROM SISEJE_MESA_PARTES_DECRETO DECR INNER JOIN SISEJE_MESA_PARTES_DECRETO_TIP TIPO ON ("
                + "DECR.CPERIODO_CODIGO=TIPO.CPERIODO_CODIGO AND "
                + "DECR.CMESA_PARTES_TIPO=TIPO.CMESA_PARTES_TIPO AND "
                + "DECR.NMESA_PARTES_CORRELATIVO=TIPO.NMESA_PARTES_CORRELATIVO AND "
                + "DECR.NMESA_PARTES_DECRETO=TIPO.NMESA_PARTES_DECRETO) WHERE "
                + "DECR.CPERIODO_CODIGO=? AND "
                + "DECR.CMESA_PARTES_TIPO='I' AND "
                + "DECR.NMESA_PARTES_CORRELATIVO=? AND "
                + "DECR.CESTADO_CODIGO!='AN'";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getNumero());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                result += objResultSet.getString("CODIGO") + "+++";
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDecretosTipoDecretos(objBeanDecreto) : " + e.getMessage());
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
    public String getDecretosTipoRemuneracion(BeanMesaPartes objBeanDecreto) {
        String result = "";
        sql = "SELECT DISTINCT REMU.NTIPO_CONCEPTO_CODIGO AS CODIGO "
                + "FROM SISEJE_MESA_PARTES_DECRETO DECR INNER JOIN SISEJE_MESA_PARTES_DECRETO_REM REMU ON ("
                + "DECR.CPERIODO_CODIGO=REMU.CPERIODO_CODIGO AND "
                + "DECR.CMESA_PARTES_TIPO=REMU.CMESA_PARTES_TIPO AND "
                + "DECR.NMESA_PARTES_CORRELATIVO=REMU.NMESA_PARTES_CORRELATIVO AND "
                + "DECR.NMESA_PARTES_DECRETO=REMU.NMESA_PARTES_DECRETO) WHERE "
                + "DECR.CPERIODO_CODIGO=? AND "
                + "DECR.CMESA_PARTES_TIPO='I' AND "
                + "DECR.NMESA_PARTES_CORRELATIVO=? AND "
                + "DECR.CESTADO_CODIGO!='AN'";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getNumero());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                result += objResultSet.getString("CODIGO") + "+++";
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDecretosTipoRemuneracion(objBeanDecreto) : " + e.getMessage());
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
    public int iduDecretos(BeanMesaPartes objBeanDecreto, String usuario) {
        sql = "{CALL SP_IDU_MESA_PARTES_DECRETOS(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanDecreto.getPeriodo());
            cs.setString(2, objBeanDecreto.getTipo());
            cs.setString(3, objBeanDecreto.getNumero());
            cs.setString(4, objBeanDecreto.getCodigo());
            cs.setString(5, objBeanDecreto.getSeccion());
            cs.setString(6, objBeanDecreto.getArea());
            cs.setDate(7, objBeanDecreto.getFechaRecepcion());
            cs.setString(8, objBeanDecreto.getPrioridad());
            cs.setString(9, objBeanDecreto.getComentario());
            cs.setString(10, usuario);
            cs.setString(11, objBeanDecreto.getMode().toUpperCase());
            cs.registerOutParameter(12, java.sql.Types.VARCHAR);
            s = cs.executeUpdate();
            objBeanDecreto.setCodigo(cs.getString(12));
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduDecretos : " + e.getMessage());
        }
        return s;
    }

    @Override
    public int iduDecretosTipoDecretos(BeanMesaPartes objBeanDecreto, String usuario) {
        sql = "{CALL SP_IDU_MESA_PARTES_DECRETOS_TI(?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanDecreto.getPeriodo());
            cs.setString(2, objBeanDecreto.getTipo());
            cs.setString(3, objBeanDecreto.getNumero());
            cs.setString(4, objBeanDecreto.getCodigo());
            cs.setString(5, objBeanDecreto.getDecreto());
            cs.setString(6, usuario);
            cs.setString(7, objBeanDecreto.getMode().toUpperCase());
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduDecretosTipoDecretos : " + e.getMessage());
        }
        return s;
    }

    @Override
    public int iduDecretosTipoRemuneracion(BeanMesaPartes objBeanDecreto, String usuario) {
        sql = "{CALL SP_IDU_MESA_PARTES_DECRETOS_RE(?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanDecreto.getPeriodo());
            cs.setString(2, objBeanDecreto.getTipo());
            cs.setString(3, objBeanDecreto.getNumero());
            cs.setString(4, objBeanDecreto.getCodigo());
            cs.setString(5, objBeanDecreto.getConceptos());
            cs.setString(6, usuario);
            cs.setString(7, objBeanDecreto.getMode().toUpperCase());
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduDecretosTipoDecretos : " + e.getMessage());
        }
        return s;
    }

    @Override
    public int iduDecretosRespuesta(BeanMesaPartes objBeanDecreto, String usuario) {
        sql = "{CALL SP_IDU_MESA_PARTES_RESPUESTA(?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanDecreto.getPeriodo());
            cs.setString(2, objBeanDecreto.getCodigo());
            cs.setString(3, objBeanDecreto.getNumero());
            cs.setString(4, objBeanDecreto.getDecreto());
            cs.setString(5, objBeanDecreto.getTipoDocumento());
            cs.setString(6, objBeanDecreto.getNumeroDocumento());
            cs.setDate(7, objBeanDecreto.getFechaDocumento());
            cs.setString(8, objBeanDecreto.getAsunto());
            cs.setString(9, usuario);
            cs.setString(10, objBeanDecreto.getMode().toUpperCase());
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println(objBeanDecreto.getMode().toUpperCase());
            System.out.println("Error al ejecutar iduDecretosRespuesta : " + e.getMessage());
        }
        return s;
    }
}
