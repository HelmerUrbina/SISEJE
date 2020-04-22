package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanUsuario;
import BusinessServices.Beans.BeanUsuarioMenu;
import DataService.Despachadores.UsuarioDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final Connection objConnection;
    private PreparedStatement objPreparedStatement;
    private ResultSet objResultSet;
    private String sql;
    private List lista;
    private BeanUsuario objBnUsuario;
    private BeanUsuarioMenu objBnUsuarioMenu;
    private Integer s = 0;

    public UsuarioDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public BeanUsuario autentica(String usuario, String password) {
        password = EncriptarCadena(password, true);
        sql = "SELECT VUSUARIO_CODIGO AS USUARIO, VUSUARIO_PATERNO AS PATERNO, VUSUARIO_MATERNO AS MATERNO, "
                + "VUSUARIO_NOMBRES AS NOMBRES, VUSUARIO_CORREO AS CORREO, CSECCION_CODIGO AS SECCION, "
                + "NAREA_CODIGO AREA, NUSUARIO_ADMIN AS AUTORIZACION "
                + "FROM SISEJE_USUARIOS WHERE "
                + "VUSUARIO_CODIGO=? AND "
                + "VUSUARIO_PASSWORD=? AND "
                + "CESTADO_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objPreparedStatement.setString(2, password);
            objPreparedStatement.setString(3, "AC");
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBnUsuario = new BeanUsuario();
                objBnUsuario.setUsuario(objResultSet.getString("USUARIO"));
                objBnUsuario.setPaterno(objResultSet.getString("PATERNO"));
                objBnUsuario.setMaterno(objResultSet.getString("MATERNO"));
                objBnUsuario.setNombres(objResultSet.getString("NOMBRES"));
                objBnUsuario.setCorreo(objResultSet.getString("CORREO"));
                objBnUsuario.setSeccion(objResultSet.getString("SECCION"));
                objBnUsuario.setArea(objResultSet.getString("AREA"));
                objBnUsuario.setAutorizacion(objResultSet.getString("AUTORIZACION"));
                objBnUsuario.setSistema("SISEJE");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener autentica(usuario) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return objBnUsuario;
    }

    @Override
    public List getModulos(String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT DISTINCT SISEJE_MODULOS.CMODULO_CODIGO AS CODIGO, SISEJE_MODULOS.VMODULO_NOMBRE AS DESCRIPCION "
                + "FROM SISEJE_USUARIOS_MENU INNER JOIN SISEJE_MODULOS ON ("
                + "SISEJE_USUARIOS_MENU.CMODULO_CODIGO=SISEJE_MODULOS.CMODULO_CODIGO) WHERE "
                + "SISEJE_USUARIOS_MENU.VUSUARIO_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnUsuarioMenu = new BeanUsuarioMenu();
                objBnUsuarioMenu.setModulo(objResultSet.getString("CODIGO"));
                objBnUsuarioMenu.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnUsuarioMenu);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener Modulos(usuario) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getMenu(String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT SISEJE_MENU.CMODULO_CODIGO AS MODULO, SISEJE_MENU.CMENU_CODIGO AS MENU, "
                + "SISEJE_MENU.VMENU_NOMBRE AS DESCRIPCION, SISEJE_MENU.VMENU_SERVLET AS SERVLET, "
                + "SISEJE_MENU.VMENU_MODO AS MODO "
                + "FROM SISEJE_USUARIOS_MENU INNER JOIN SISEJE_MENU ON ("
                + "SISEJE_USUARIOS_MENU.CMODULO_CODIGO=SISEJE_MENU.CMODULO_CODIGO AND "
                + "SISEJE_USUARIOS_MENU.CMENU_CODIGO=SISEJE_MENU.CMENU_CODIGO) WHERE "
                + "SISEJE_USUARIOS_MENU.VUSUARIO_CODIGO=? "
                + "ORDER BY 1, 2 ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnUsuarioMenu = new BeanUsuarioMenu();
                objBnUsuarioMenu.setModulo(objResultSet.getString("MODULO"));
                objBnUsuarioMenu.setMenu(objResultSet.getString("MENU"));
                objBnUsuarioMenu.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBnUsuarioMenu.setServlet(objResultSet.getString("SERVLET"));
                objBnUsuarioMenu.setModo(objResultSet.getString("MODO"));
                lista.add(objBnUsuarioMenu);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMenu(usuario) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getModulos() {
        lista = new LinkedList<>();
        sql = "SELECT CMODULO_CODIGO AS CODIGO, VMODULO_NOMBRE AS MODULO "
                + "FROM SISEJE_MODULOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnUsuarioMenu = new BeanUsuarioMenu();
                objBnUsuarioMenu.setModulo(objResultSet.getString("CODIGO"));
                objBnUsuarioMenu.setDescripcion(objResultSet.getString("MODULO"));
                lista.add(objBnUsuarioMenu);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getModulos() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getMenu() {
        lista = new LinkedList<>();
        sql = "SELECT CMODULO_CODIGO AS MODULO, CMODULO_CODIGO||'-'||CMENU_CODIGO AS CODIGO, "
                + "VMENU_NOMBRE AS MENU "
                + "FROM SISEJE_MENU WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnUsuarioMenu = new BeanUsuarioMenu();
                objBnUsuarioMenu.setModulo(objResultSet.getString("MODULO"));
                objBnUsuarioMenu.setMenu(objResultSet.getString("CODIGO"));
                objBnUsuarioMenu.setDescripcion(objResultSet.getString("MENU"));
                lista.add(objBnUsuarioMenu);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMenu() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getOpciones(String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT CMODULO_CODIGO||'-'||CMENU_CODIGO AS CODIGO "
                + "FROM SISEJE_USUARIOS_MENU "
                + "WHERE VUSUARIO_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                lista.add(objResultSet.getString("CODIGO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getOpciones(objBeanUsuario, usuario) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getListaUsuarios() {
        lista = new LinkedList<>();
        sql = "SELECT VUSUARIO_CODIGO AS USUARIO, VUSUARIO_PATERNO AS PATERNO, VUSUARIO_MATERNO AS MATERNO, "
                + "VUSUARIO_NOMBRES AS NOMBRES, VUSUARIO_CORREO AS CORREO, "
                + "UTIL.FUN_SECCION(CSECCION_CODIGO) AS SECCION, "
                + "UTIL.FUN_AREA(CSECCION_CODIGO, NAREA_CODIGO) AS AREA, "
                + "CASE NUSUARIO_ADMIN WHEN 1 THEN 'ADMINISTRADOR' ELSE 'USUARIO' END AS AUTORIZACION, "
                + "UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM SISEJE_USUARIOS "
                + "ORDER BY PATERNO, MATERNO, NOMBRES";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnUsuario = new BeanUsuario();
                objBnUsuario.setUsuario(objResultSet.getString("USUARIO"));
                objBnUsuario.setPaterno(objResultSet.getString("PATERNO"));
                objBnUsuario.setMaterno(objResultSet.getString("MATERNO"));
                objBnUsuario.setNombres(objResultSet.getString("NOMBRES"));
                objBnUsuario.setCorreo(objResultSet.getString("CORREO"));
                objBnUsuario.setSeccion(objResultSet.getString("SECCION"));
                objBnUsuario.setArea(objResultSet.getString("AREA"));
                objBnUsuario.setSistema(objResultSet.getString("AUTORIZACION"));
                objBnUsuario.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnUsuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaUsuarios() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public BeanUsuario getUsuario(BeanUsuario objBeanUsuario) {
        sql = "SELECT VUSUARIO_PATERNO AS PATERNO, VUSUARIO_MATERNO AS MATERNO, "
                + "VUSUARIO_NOMBRES AS NOMBRES, VUSUARIO_CORREO AS CORREO, "
                + "CSECCION_CODIGO AS SECCION, NAREA_CODIGO AS AREA, "
                + "NUSUARIO_ADMIN AS AUTORIZACION, CESTADO_CODIGO AS ESTADO "
                + "FROM SISEJE_USUARIOS WHERE "
                + "VUSUARIO_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanUsuario.getUsuario());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanUsuario.setPaterno(objResultSet.getString("PATERNO"));
                objBeanUsuario.setMaterno(objResultSet.getString("MATERNO"));
                objBeanUsuario.setNombres(objResultSet.getString("NOMBRES"));
                objBeanUsuario.setCorreo(objResultSet.getString("CORREO"));
                objBeanUsuario.setSeccion(objResultSet.getString("SECCION"));
                objBeanUsuario.setArea(objResultSet.getString("AREA"));
                objBeanUsuario.setAutorizacion(objResultSet.getString("AUTORIZACION"));
                objBeanUsuario.setEstado(objResultSet.getString("ESTADO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuario() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return objBeanUsuario;
    }

    @Override
    public BeanUsuario recuperarPassword(String usuario, String correo) {
        sql = "SELECT VUSUARIO_PATERNO AS PATERNO, VUSUARIO_MATERNO AS MATERNO, VUSUARIO_NOMBRES AS NOMBRES, "
                + "VUSUARIO_CORREO AS CORREO, VUSUARIO_PASSWORD AS PASSWORD "
                + "FROM SISEJE_USUARIOS WHERE "
                + "VUSUARIO_CODIGO=? AND "
                + "UPPER(VUSUARIO_CORREO)=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objPreparedStatement.setString(2, correo.toUpperCase());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBnUsuario = new BeanUsuario();
                objBnUsuario.setPaterno(objResultSet.getString("PATERNO"));
                objBnUsuario.setMaterno(objResultSet.getString("MATERNO"));
                objBnUsuario.setNombres(objResultSet.getString("NOMBRES"));
                objBnUsuario.setCorreo(objResultSet.getString("CORREO"));
                objBnUsuario.setPassword(EncriptarCadena(objResultSet.getString("PASSWORD"), false));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener recuperarPassword() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return objBnUsuario;
    }

    @Override
    public String obtenerPassword(String usuario) {
        String password = "";
        sql = "SELECT VUSUARIO_PASSWORD AS PASSWORD "
                + "FROM SISEJE_USUARIOS WHERE "
                + "VUSUARIO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                password = EncriptarCadena(objResultSet.getString("PASSWORD"), false);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener obtenerPassword() : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return password;
    }

    @Override
    public int iduUsuario(BeanUsuario objBeanUsuario, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * USUARIO, EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        String password = "";
        if (objBeanUsuario.getMode().equals("I")) {
            objBeanUsuario.setPassword(Utiles.Utiles.generarAleatorio(5));            
            password = EncriptarCadena(objBeanUsuario.getPassword(), true);
        }
        if (objBeanUsuario.getMode().equals("P")) {
            password = EncriptarCadena(objBeanUsuario.getPassword(), true);
            objBeanUsuario.setPaterno(EncriptarCadena(objBeanUsuario.getPaterno(), true));
        }
        sql = "{CALL SP_IDU_USUARIOS(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanUsuario.getUsuario());
            cs.setString(2, password);
            cs.setString(3, objBeanUsuario.getPaterno());
            cs.setString(4, objBeanUsuario.getMaterno());
            cs.setString(5, objBeanUsuario.getNombres());
            cs.setString(6, objBeanUsuario.getCorreo());
            cs.setString(7, objBeanUsuario.getSeccion());
            cs.setString(8, objBeanUsuario.getArea());
            cs.setString(9, objBeanUsuario.getAutorizacion());
            cs.setString(10, objBeanUsuario.getEstado());
            cs.setString(11, usuario);
            cs.setString(12, objBeanUsuario.getMode());
            s = cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduUsuario : " + e.getMessage());
            return 0;
        }
        return s;
    }

    @Override
    public int iduOpciones(BeanUsuario objBeanUsuario, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * USUARIO, EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_USUARIOS_MENU(?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanUsuario.getUsuario());
            cs.setString(2, objBeanUsuario.getModulo());
            cs.setString(3, objBeanUsuario.getMenu());
            cs.setString(4, usuario);
            cs.setString(5, objBeanUsuario.getMode());
            s = cs.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduOpciones : " + e.getMessage());
            return 0;
        }
        return s;
    }

    public String EncriptarCadena(String cadena, boolean a) {
        String retorno = "";
        int it = cadena.length();
        int temp;
        int[] textoascii = new int[it];
        if (a) {
            for (int i = 0; i < it; i++) {
                textoascii[i] = cadena.charAt(i);
                temp = textoascii[i];
                temp = temp + 200 % 27;
                if (temp > 127) {
                    int v = temp / 127;
                    temp = temp - 128 * v;
                }
                retorno = retorno + (char) temp;
            }
        } else {
            for (int i = 0; i < it; i++) {
                textoascii[i] = cadena.charAt(i);
                temp = textoascii[i];
                temp = temp - 200 % 27;
                if (temp > 127) {
                    int v = temp / 127;
                    temp = temp - 128 * v;
                }
                retorno = retorno + (char) temp;
            }
        }
        return retorno;
    }
}
