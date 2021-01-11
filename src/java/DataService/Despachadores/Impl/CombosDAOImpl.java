package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanComun;
import DataService.Despachadores.CombosDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CombosDAOImpl implements CombosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanComun objBnComun;
    private PreparedStatement objPreparedStatement;

    public CombosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getModulos() {
        lista = new LinkedList<>();
        sql = "SELECT CMODULO_CODIGO AS CODIGO, VMODULO_NOMBRE AS DESCRIPCION "
                + "FROM SISEJE_MODULOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getModulos() : " + e.getMessage());
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
    public List getPeriodos() {
        lista = new LinkedList<>();
        sql = "SELECT CPERIODO_CODIGO AS CODIGO, CPERIODO_CODIGO AS DESCRIPCION "
                + "FROM SISEJE_PERIODOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPeriodos() : " + e.getMessage());
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
    public List getTipoDocumentos() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_DOCUMENTO_CODIGO AS CODIGO, VTIPO_DOCUMENTO_ABREVIATURA AS DESCRIPCION "
                + "FROM SISEJE_TIPO_DOCUMENTOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoDocumentos() : " + e.getMessage());
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
    public List getTipoConceptos() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_CONCEPTO_CODIGO AS CODIGO, VTIPO_CONCEPTO_ABREVIATURA AS DESCRIPCION "
                + "FROM SISEJE_TIPO_CONCEPTOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoConceptos() : " + e.getMessage());
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
    public List getTipoRemuneracion() {
        lista = new LinkedList<>();
        sql = "SELECT DISTINCT CTIPO_REMUNERACION_CODIGO AS CODIGO, "
                + "VTIPO_REMUNERACION_DESCRIPCION AS DESCRIPCION "
                + "FROM SISEJE_TIPO_REMUNERACION WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoRemuneracion() : " + e.getMessage());
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
    public List getTipoPagos() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_PAGO_CODIGO AS CODIGO, VTIPO_PAGO_ABREVIATURA AS DESCRIPCION "
                + "FROM SISEJE_TIPO_PAGOS "
                + "WHERE CESTADO_CODIGO='AC' "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoPagos() : " + e.getMessage());
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
    public List getSecciones() {
        lista = new LinkedList<>();
        sql = "SELECT CSECCION_CODIGO AS CODIGO, VSECCION_ABREVIATURA AS DESCRIPCION "
                + "FROM SISEJE_SECCIONES WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getSecciones() : " + e.getMessage());
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
    public List getAreas(String codigo) {
        lista = new LinkedList<>();
        sql = "SELECT NAREA_CODIGO AS CODIGO, VAREA_ABREVIATURA AS DESCRIPCION "
                + "FROM SISEJE_AREAS WHERE "
                + "CSECCION_CODIGO=? AND "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, codigo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getAreas('" + codigo + "') : " + e.getMessage());
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
    public List getPrioridad() {
        lista = new LinkedList<>();
        sql = "SELECT NPRIORIDAD_CODIGO AS CODIGO, VPRIORIDAD_ABREVIATURA AS DESCRIPCION "
                + "FROM SISEJE_PRIORIDAD WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPrioridad() : " + e.getMessage());
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
    public List getTipoJuzgados() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_JUZGADO_CODIGO AS CODIGO, UPPER(VTIPO_JUZGADO_DESCRIPCION) AS DESCRIPCION "
                + "FROM SISEJE_TIPO_JUZGADOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoJuzgados() : " + e.getMessage());
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
    public List getTipoDecretos() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_DECRETO_CODIGO AS CODIGO, VTIPO_DECRETO_DESCRIPCION AS DESCRIPCION "
                + "FROM SISEJE_TIPO_DECRETOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoDecretos() : " + e.getMessage());
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
    public List getDepartamentos() {
        lista = new LinkedList<>();
        sql = "SELECT CDEPARTAMENTO_CODIGO AS CODIGO, VDEPARTAMENTO_NOMBRE AS DESCRIPCION "
                + "FROM SISEJE_DEPARTAMENTOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDepartamentos() : " + e.getMessage());
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
    public List getJuzgados() {
        lista = new LinkedList<>();
        sql = "SELECT NJUZGADO_CODIGO AS CODIGO, VJUZGADO_NOMBRE AS DESCRIPCION "
                + "FROM SISEJE_JUZGADOS "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getJuzgados() : " + e.getMessage());
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
    public List getJuzgados(String tipoJuzgado) {
        lista = new LinkedList<>();
        sql = "SELECT NJUZGADO_CODIGO AS CODIGO, VJUZGADO_NOMBRE AS DESCRIPCION "
                + "FROM SISEJE_JUZGADOS WHERE "
                + "NTIPO_JUZGADO_CODIGO=? "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, tipoJuzgado);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getJuzgados('" + tipoJuzgado + "') : " + e.getMessage());
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
    public List getJuzgados(String tipoJuzgado, String departamento) {
        lista = new LinkedList<>();
        sql = "SELECT NJUZGADO_CODIGO AS CODIGO, VJUZGADO_NOMBRE AS DESCRIPCION "
                + "FROM SISEJE_JUZGADOS WHERE "
                + "NTIPO_JUZGADO_CODIGO=? AND "
                + "CDEPARTAMENTO_CODIGO=?  "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, tipoJuzgado);
            objPreparedStatement.setString(2, departamento);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getJuzgados('" + tipoJuzgado + "', '" + departamento + "') : " + e.getMessage());
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
    public List getBancos() {
        lista = new LinkedList<>();
        sql = "SELECT NBANCO_CODIGO AS CODIGO, VBANCO_NOMBRE AS DESCRIPCION "
                + "FROM SISEJE_BANCOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBancos() : " + e.getMessage());
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
    public List getLugar(String departamento) {
        lista = new LinkedList<>();
        sql = "SELECT CLUGAR_CODIGO AS CODIGO, VLUGAR_SUCURSAL AS DESCRIPCION "
                + "FROM SISEJE_LUGAR WHERE "
                + "CESTADO_CODIGO='AC' AND "
                + "CDEPARTAMENTO_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, departamento);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getLugar("+departamento+") : " + e.getMessage());
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
    public List getMesaPartes(String periodo, Integer area) {
        lista = new LinkedList<>();
        sql = "SELECT SISEJE_MESA_PARTES.NMESA_PARTES_CORRELATIVO||'-'||SISEJE_MESA_PARTES_DECRETO.NMESA_PARTES_DECRETO AS CODIGO,  "
                + "SISEJE_MESA_PARTES.NMESA_PARTES_CORRELATIVO||'-'||SISEJE_MESA_PARTES_DECRETO.NMESA_PARTES_DECRETO||':'||UTIL.FUN_TIPO_DOCUMENTO(NTIPO_DOCUMENTO_CODIGO)||'-'||"
                + "SISEJE_MESA_PARTES.VMESA_PARTES_DOCUMENTO||' '||SISEJE_MESA_PARTES.VMESA_PARTES_ASUNTO AS DESCRIPCION "
                + "FROM SISEJE_MESA_PARTES INNER JOIN SISEJE_MESA_PARTES_DECRETO ON ("
                + "SISEJE_MESA_PARTES.CPERIODO_CODIGO=SISEJE_MESA_PARTES_DECRETO.CPERIODO_CODIGO AND "
                + "SISEJE_MESA_PARTES.CMESA_PARTES_TIPO=SISEJE_MESA_PARTES_DECRETO.CMESA_PARTES_TIPO AND "
                + "SISEJE_MESA_PARTES.NMESA_PARTES_CORRELATIVO=SISEJE_MESA_PARTES_DECRETO.NMESA_PARTES_CORRELATIVO) WHERE "
                + "SISEJE_MESA_PARTES.NJUZGADO_CODIGO!=0 AND "
                + "SISEJE_MESA_PARTES_DECRETO.CPERIODO_CODIGO=? AND "
                + "SISEJE_MESA_PARTES_DECRETO.CMESA_PARTES_TIPO='I' AND "
                + "SISEJE_MESA_PARTES_DECRETO.CSECCION_CODIGO='04' AND "
                + "SISEJE_MESA_PARTES_DECRETO.NAREA_CODIGO=? AND "
                + "SISEJE_MESA_PARTES_DECRETO.CESTADO_CODIGO='AC' "
                + "ORDER BY SISEJE_MESA_PARTES.NMESA_PARTES_CORRELATIVO DESC, SISEJE_MESA_PARTES_DECRETO.NMESA_PARTES_DECRETO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, area);
            objResultSet = objPreparedStatement.executeQuery();
            objBnComun = new BeanComun();
            objBnComun.setCodigo("0");
            objBnComun.setDescripcion("Seleccione");
            lista.add(objBnComun);
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMesaPartes('" + periodo + "','" + area + "') : " + e.getMessage());
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
    public List getAreaResolucion() {
        lista = new LinkedList<>();
        sql = "SELECT CSENTENCIA_TIPO AS CODIGO, UTIL.FUN_AREA('04',CSENTENCIA_TIPO) AS DESCRIPCION "
                + "FROM SISEJE_RESOLUCIONES "
                + "GROUP BY CSENTENCIA_TIPO "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getAreaResolucion() : " + e.getMessage());
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
    public List getTipoMonedas() {
        lista = new LinkedList<>();
        sql = "SELECT CTIPO_MONEDA_CODIGO AS CODIGO, VTIPO_MONEDA_DESCRIPCION AS DESCRIPCION "
                + "FROM SISEJE_TIPO_MONEDA "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getAreaResolucion() : " + e.getMessage());
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
    public List getConceptosSentencia(String periodo, String mes, String tipo) {
        lista = new LinkedList<>();
        sql = "SELECT CRESOLUCION_RETORNO_DISTRIBUCI AS CODIGO, UTIL.FUN_CONCEPTO_DISTRIBUCION(CRESOLUCION_RETORNO_DISTRIBUCI) AS DESCRIPCION "
                + "FROM SISEJE_RESOLUCIONES_MOVIMIENTO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CSENTENCIA_TIPO=? AND "
                + "NRESOLUCION_MOVIMIENTO_PAGO>0 "
                + "GROUP BY CRESOLUCION_RETORNO_DISTRIBUCI "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, mes);
            objPreparedStatement.setString(3, tipo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getConceptosSentencia('" + periodo + "','" + mes + "','" + tipo + "') : " + e.getMessage());
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
    public List getTipoPersonalPlanilla(String periodo, String mes, String tipo, String tipoPersonal) {
        lista = new LinkedList<>();
        sql = "SELECT CPLANILLA_CODIGO AS CODIGO, PLANILLA_ABREVIATURA AS DESCRIPCION "
                + "FROM V_RESOLUCION_MOVIMIENTO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CSENTENCIA_TIPO=? AND "
                + "NSITUACION_TIPO=? "
                + "GROUP BY CPLANILLA_CODIGO, PLANILLA_ABREVIATURA "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, mes);
            objPreparedStatement.setString(3, tipo);
            objPreparedStatement.setString(4, tipoPersonal);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoPersonalPlanilla('" + periodo + "','" + mes + "','" + tipo + "','" + tipoPersonal + "') : " + e.getMessage());
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
    public List getTipoPersonalPlanillaMCPP(String periodo, String mes, String tipo, String tipoPersonal) {
        lista = new LinkedList<>();
        sql = "SELECT PLANILLA_MCPP AS CODIGO, PLANILLA_MCPP AS DESCRIPCION "
                + "FROM V_RESOLUCION_MOVIMIENTO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CSENTENCIA_TIPO=? AND "
                + "NSITUACION_TIPO=? "
                + "GROUP BY PLANILLA_MCPP "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, mes);
            objPreparedStatement.setString(3, tipo);
            objPreparedStatement.setString(4, tipoPersonal);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnComun = new BeanComun();
                objBnComun.setCodigo(objResultSet.getString("CODIGO"));
                objBnComun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnComun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoPersonalPlanilla('" + periodo + "','" + mes + "','" + tipo + "','" + tipoPersonal + "') : " + e.getMessage());
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
    public String getBeneficiario(String codigo) {
        String result = "";
        sql = "SELECT DISTINCT NVL(VBENEFICIARIO_PATERNO,'') AS PATERNO, NVL(VBENEFICIARIO_MATERNO,'') AS MATERNO, "
                + "NVL(VBENEFICIARIO_NOMBRES,'') AS NOMBRES, DBENEFICIARIO_NACIMIENTO AS FECHA, "
                + "NVL(VBENEFICIARIO_RAZON_SOCIAL,'') AS RAZON_SOCIAL, NVL(VBENEFICIARIO_RUC,'') AS RUC "
                + "FROM SISEJE_BENEFICIARIO WHERE "
                + "TRIM(VBENEFICIARIO_DOCUMENTO)=TRIM(?) ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, codigo);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("PATERNO") + "+++" + objResultSet.getString("MATERNO") + "+++"
                        + objResultSet.getString("NOMBRES") + "+++" + objResultSet.getDate("FECHA") + "+++"
                        + objResultSet.getString("RAZON_SOCIAL") + "+++" + objResultSet.getString("RUC");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficiario('" + codigo + "') : " + e.getMessage());
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
    public String getBeneficiarioCuenta(String dni, String banco) {
        String result = "";
        sql = "SELECT DISTINCT NVL(TRIM(VBENEFICIARIO_CUENTA_NUMERO), ' ') AS CUENTA, "
                + "NVL(VBENEFICIARIO_CUENTA_CCI, ' ') AS CCI "
                + "FROM SISEJE_BENEFICIARIO_CUENTA WHERE "
                + "TRIM(VBENEFICIARIO_DOCUMENTO)=? AND "
                + "NBANCO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, dni);
            objPreparedStatement.setString(2, banco);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("CUENTA") + "+++" + objResultSet.getString("CCI");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficiarioCuenta('" + dni + "','" + banco + "') : " + e.getMessage());
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
    public String getBeneficiarioSentencia(String CIP, String sentencia) {
        String result = "";
        sql = "SELECT UTIL.FUN_SENTENCIA_BENEFICIARIO(CPERSONAL_CIP, NSENTENCIA_CODIGO) AS BENEFICIARIO  "
                + "FROM SISEJE_SENTENCIAS WHERE "
                + "CPERSONAL_CIP=? AND "
                + "NSENTENCIA_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, CIP);
            objPreparedStatement.setString(2, sentencia);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("BENEFICIARIO");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficiarioSentencia('" + CIP + "','" + sentencia + "') : " + e.getMessage());
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
    public String getRazonSocial(String codigo) {
        String result = "";
        sql = "SELECT VBENEFICIARIO_RAZON_SOCIAL AS RAZON_SOCIAL "
                + "FROM SISEJE_BENEFICIARIO WHERE "
                + "VBENEFICIARIO_DOCUMENTO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, codigo);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("RAZON_SOCIAL");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getRazonSocial('" + codigo + "') : " + e.getMessage());
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

}
