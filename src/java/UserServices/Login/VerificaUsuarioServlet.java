package UserServices.Login;

import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.Impl.UsuarioDAOImpl;
import DataService.Despachadores.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "VerificaUsuarioServlet", urlPatterns = {"/VerificaUsuario"})
public class VerificaUsuarioServlet extends HttpServlet {

    private ServletConfig config;
    private ServletContext context;
    private HttpSession session;
    private Connection objConnection;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession();
        objConnection = (Connection) context.getAttribute("objConnection");
        response.setContentType("text/html;charset=ISO-8859-1");
        String accion = request.getParameter("accion");
        String result = null;
        String target = null;
        if (objConnection != null) {
            switch (accion) {
                case "LOGIN":
                    String usuario = request.getParameter("usuario");
                    String password = request.getParameter("password");
                    UsuarioDAO daoUsuarios = new UsuarioDAOImpl(objConnection);
                    BeanUsuario objUsuario = daoUsuarios.autentica(usuario, password);
                    if (objUsuario == null) {
                        result = "Usuario no Registrado o No Activado!!!";
                    } else {
                        request.getSession().setAttribute("ID", session.getId());
                        request.getSession().setAttribute("usuario", objUsuario);
                        request.getSession().setAttribute("seccion", objUsuario.getSeccion());
                        request.getSession().setAttribute("area", objUsuario.getArea());
                        request.getSession().setAttribute("objUsuario" + session.getId(), objUsuario);
                        request.getSession().setAttribute("autorizacion", objUsuario.getAutorizacion());
                        request.getSession().setAttribute("objModulo", daoUsuarios.getModulos(usuario));
                        request.getSession().setAttribute("objMenu", daoUsuarios.getMenu(usuario));
                        result = "Login/Principal.jsp";
                        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        System.out.println("SISTEMA DE SENTENCIAS JUDICIALES - EJERCITO PERUANO");
                        System.out.println("------- -- ---------- ---------- - -------- -------");
                        System.out.println("Se ha conectado el Usuario : " + objUsuario.getPaterno() + " " + objUsuario.getMaterno() + ", " + objUsuario.getNombres());
                        System.out.println("Fecha y Hora de Ingreso : " + formatoFechaHora.format((new Date()).getTime()));
                        System.out.println("IP de ingreso : " + request.getRemoteAddr());
                    }
                    break;
                default:
                    request.getSession().removeAttribute("ID");
                    request.getSession().removeAttribute("objUsuario" + session.getId());
                    request.getSession().removeAttribute("autorizacion");
                    request.getSession().invalidate();
                    target = "index.jsp";
                    break;
            }
        } else {
            result = "Sin Acceso a la Base de Datos";
        }
        if (result != null) {
            response.setContentType("text/html;charset=ISO-8859-1");
            try (PrintWriter out = response.getWriter()) {
                out.print(result);
            }
        }
        if (target != null) {
            response.sendRedirect(target);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
