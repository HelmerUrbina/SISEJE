package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanMesaPartes;
import DataService.Despachadores.MesaPartesDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MesaPartesDAOImpl implements MesaPartesDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanMesaPartes objBnMesaPartes;
    private PreparedStatement objPreparedStatement;
    private int s = 0;

    public MesaPartesDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaMesaPartes(BeanMesaPartes objBeanMesaPartes) {
        lista = new LinkedList<>();
        sql = "SELECT NMESA_PARTES_CORRELATIVO AS CODIGO, LPAD(NMESA_PARTES_CORRELATIVO,6,0) AS NUMERO, "
                + "UTIL.FUN_TIPO_DOCUMENTO(NTIPO_DOCUMENTO_CODIGO)||'-'||VMESA_PARTES_DOCUMENTO AS DOCUMENTO, "
                + "UPPER(VMESA_PARTES_ASUNTO) AS ASUNTO, DMESA_PARTES_FECHA AS FECHA, "
                + "UTIL.FUN_PRIORIDAD(NPRIORIDAD_CODIGO) AS PRIORIDAD, UPPER(VMESA_PARTES_POST_FIRMA) AS FIRMA, "
                + "VMESA_PARTES_DIGITAL AS ARCHIVO, UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_MESA_PARTES WHERE "
                + "CMESA_PARTES_TIPO='I' AND "
                + "CPERIODO_CODIGO=? AND "
                + "TO_CHAR(DMESA_PARTES_RECEPCION,'MM')=? AND "
                + "TO_CHAR(DMESA_PARTES_RECEPCION,'DD') = LPAD(?,2,0) "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanMesaPartes.getPeriodo());
            objPreparedStatement.setString(2, objBeanMesaPartes.getMes());
            objPreparedStatement.setString(3, objBeanMesaPartes.getFechaBusqueda());
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
            System.out.println("Error al obtener getListaMesaPartes(objBeanMesaPartes) : " + e.getMessage());
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
    public List getConsultaMesaPartes(BeanMesaPartes objBeanMesaPartes) {
        lista = new LinkedList<>();
        sql = "SELECT NMESA_PARTES_CORRELATIVO AS CODIGO, LPAD(NMESA_PARTES_CORRELATIVO,6,0) AS NUMERO, "
                + "UTIL.FUN_TIPO_DOCUMENTO(NTIPO_DOCUMENTO_CODIGO)||'-'||VMESA_PARTES_DOCUMENTO AS DOCUMENTO, "
                + "UPPER(VMESA_PARTES_ASUNTO) AS ASUNTO, DMESA_PARTES_FECHA AS FECHA, "
                + "UTIL.FUN_PRIORIDAD(NPRIORIDAD_CODIGO) AS PRIORIDAD, UPPER(VMESA_PARTES_POST_FIRMA) AS FIRMA,"
                + "VMESA_PARTES_DIGITAL AS ARCHIVO, UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_MESA_PARTES WHERE "
                + "CMESA_PARTES_TIPO='I' AND "
                + "CPERIODO_CODIGO LIKE ? AND "
                + "TO_CHAR(DMESA_PARTES_RECEPCION,'MM') LIKE ? AND "
                + "CESTADO_CODIGO LIKE ? "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanMesaPartes.getPeriodo().replace("0000", "%"));
            objPreparedStatement.setString(2, objBeanMesaPartes.getMes().replace("00", "%"));
            objPreparedStatement.setString(3, objBeanMesaPartes.getEstado().replace("00", "%"));
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaPartes = new BeanMesaPartes();
                objBnMesaPartes.setCodigo(objResultSet.getString("CODIGO"));
                objBnMesaPartes.setNumero(objResultSet.getString("NUMERO"));
                objBnMesaPartes.setTipoDocumento(objResultSet.getString("DOCUMENTO"));
                objBnMesaPartes.setAsunto(objResultSet.getString("ASUNTO"));
                objBnMesaPartes.setFechaDocumento(objResultSet.getDate("FECHA"));
                objBnMesaPartes.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnMesaPartes.setPostFirma(objResultSet.getString("FIRMA"));
                objBnMesaPartes.setArchivo(objResultSet.getString("ARCHIVO"));
                objBnMesaPartes.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnMesaPartes);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getConsultaMesaPartes(objBeanMesaParte) : " + e.getMessage());
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
    public BeanMesaPartes getMesaPartes(BeanMesaPartes objBeanMesaPartes) {
        sql = "SELECT LPAD(NMESA_PARTES_CORRELATIVO,6,0) AS NUMERO, DMESA_PARTES_RECEPCION, "
                + "NTIPO_DOCUMENTO_CODIGO, VMESA_PARTES_DOCUMENTO, VMESA_PARTES_INDICATIVO, "
                + "UPPER(VMESA_PARTES_ASUNTO) AS ASUNTO, DMESA_PARTES_FECHA, NPRIORIDAD_CODIGO, NMESA_PARTES_FOLIOS, "
                + "NTIPO_JUZGADO_CODIGO, CDEPARTAMENTO_CODIGO, NJUZGADO_CODIGO, "
                + "UTIL.FUN_NOMBRE_JUZGADO(NJUZGADO_CODIGO) AS JUZGADO, "
                + "UPPER(VMESA_PARTES_POST_FIRMA) AS POST_FIRMA, VMESA_PARTES_DIGITAL "
                + "FROM SISEJE_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND  "
                + "CMESA_PARTES_TIPO='I' AND  "
                + "NMESA_PARTES_CORRELATIVO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanMesaPartes.getPeriodo());
            objPreparedStatement.setString(2, objBeanMesaPartes.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanMesaPartes.setNumero(objResultSet.getString("NUMERO"));
                objBeanMesaPartes.setFechaRecepcion(objResultSet.getDate("DMESA_PARTES_RECEPCION"));
                objBeanMesaPartes.setTipoDocumento(objResultSet.getString("NTIPO_DOCUMENTO_CODIGO"));
                objBeanMesaPartes.setNumeroDocumento(objResultSet.getString("VMESA_PARTES_DOCUMENTO"));
                objBeanMesaPartes.setIndicativo(objResultSet.getString("VMESA_PARTES_INDICATIVO"));
                objBeanMesaPartes.setAsunto(objResultSet.getString("ASUNTO"));
                objBeanMesaPartes.setFechaDocumento(objResultSet.getDate("DMESA_PARTES_FECHA"));
                objBeanMesaPartes.setPrioridad(objResultSet.getString("NPRIORIDAD_CODIGO"));
                objBeanMesaPartes.setFolios(objResultSet.getInt("NMESA_PARTES_FOLIOS"));
                objBeanMesaPartes.setTipoJuzgado(objResultSet.getString("NTIPO_JUZGADO_CODIGO"));
                objBeanMesaPartes.setDepartamento(objResultSet.getString("CDEPARTAMENTO_CODIGO"));
                objBeanMesaPartes.setJuzgado(objResultSet.getString("NJUZGADO_CODIGO"));
                objBeanMesaPartes.setUsuarioResponsable(objResultSet.getString("JUZGADO"));
                objBeanMesaPartes.setPostFirma(objResultSet.getString("POST_FIRMA"));
                objBeanMesaPartes.setArchivo(objResultSet.getString("VMESA_PARTES_DIGITAL"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMesaPartes(objBeanMesaPartes) : " + e.getMessage());
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
        return objBeanMesaPartes;
    }

    @Override
    public String getNumeroDocumento(BeanMesaPartes objBnMesaPartes) {
        String result = "000001";
        sql = "SELECT LPAD(NVL(MAX(NMESA_PARTES_CORRELATIVO)+1,1),6,0) AS CODIGO "
                + "FROM SISEJE_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTES_TIPO='I'";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBnMesaPartes.getPeriodo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("CODIGO");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getNumeroDocumento(objBnMesaPartes) : " + e.getMessage());
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
    public int iduMesaPartes(BeanMesaPartes objBeanMesaPartes, String usuario) {
        sql = "{CALL SP_IDU_MESA_PARTES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanMesaPartes.getPeriodo());
            cs.setString(2, objBeanMesaPartes.getTipo());
            cs.setString(3, objBeanMesaPartes.getCodigo());
            cs.setString(4, objBeanMesaPartes.getTipoDocumento());
            cs.setString(5, objBeanMesaPartes.getNumeroDocumento());
            cs.setString(6, objBeanMesaPartes.getIndicativo());
            cs.setString(7, objBeanMesaPartes.getAsunto());
            cs.setDate(8, objBeanMesaPartes.getFechaDocumento());
            cs.setString(9, objBeanMesaPartes.getPrioridad());
            cs.setInt(10, objBeanMesaPartes.getFolios());
            cs.setString(11, objBeanMesaPartes.getTipoJuzgado());
            cs.setString(12, objBeanMesaPartes.getDepartamento());
            cs.setString(13, objBeanMesaPartes.getJuzgado());
            cs.setString(14, objBeanMesaPartes.getPostFirma());
            cs.setString(15, objBeanMesaPartes.getArchivo());
            cs.setString(16, objBeanMesaPartes.getEstado());
            cs.setString(17, usuario);
            cs.setString(18, objBeanMesaPartes.getMode().toUpperCase());
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduMesaPartes : " + e.getMessage());
        }
        return s;
    }
}
