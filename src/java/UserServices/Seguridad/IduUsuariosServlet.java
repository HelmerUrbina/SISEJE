package UserServices.Seguridad;

import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.Impl.UsuarioDAOImpl;
import DataService.Despachadores.UsuarioDAO;
import Utiles.JavaMail;
import Utiles.Utiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "IduUsuariosServlet", urlPatterns = {"/IduUsuarios"})
public class IduUsuariosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanUsuario objBnUsuarios;
    private Connection objConnection;
    private UsuarioDAO objDsUsuarios;

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
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("../FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String result = null;
        objBnUsuarios = new BeanUsuario();
        objBnUsuarios.setMode(request.getParameter("mode"));
        objBnUsuarios.setUsuario(request.getParameter("usuario"));
        objBnUsuarios.setPaterno(request.getParameter("paterno"));
        objBnUsuarios.setMaterno(request.getParameter("materno"));
        objBnUsuarios.setNombres(request.getParameter("nombres"));
        objBnUsuarios.setSeccion(request.getParameter("seccion"));
        objBnUsuarios.setArea(request.getParameter("area"));
        objBnUsuarios.setCorreo(request.getParameter("correo"));
        objBnUsuarios.setAutorizacion(request.getParameter("tipo"));
        objBnUsuarios.setEstado(request.getParameter("estado"));
        objDsUsuarios = new UsuarioDAOImpl(objConnection);
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        int k = 0;
        if (objBnUsuarios.getMode().equals("O")) {
            objBnUsuarios.setMode("D");
            k = objDsUsuarios.iduOpciones(objBnUsuarios, objUsuario.getUsuario());
            String lista[][] = Utiles.generaLista(request.getParameter("lista"), 1);
            for (String[] item : lista) {
                if (item[0].trim().length() == 5) {
                    objBnUsuarios.setMode("I");
                    objBnUsuarios.setModulo(item[0].trim().substring(0, 2));
                    objBnUsuarios.setMenu(item[0].trim().substring(3, 5));
                    k = objDsUsuarios.iduOpciones(objBnUsuarios, objUsuario.getUsuario());
                }
            }
        } else {
            k = objDsUsuarios.iduUsuario(objBnUsuarios, objUsuario.getUsuario());
        }
        if (request.getParameter("mode").equals("I")) {
            result = "==> " + objBnUsuarios.getPassword() + " <==";
        }        
        // EN CASO DE NO HABER PROBLEMAS RETORNAMOS UNA NUEVA CONSULTA CON TODOS LOS DATOS.
        /*if (k != 0 && request.getParameter("mode").equals("I")) {
            JavaMail mail = new JavaMail(objBnUsuarios);
            mail.SendMail();
        }*/
        response.setContentType("text/html;charset=ISO-8859-1");
        if (result == null) {
            try (PrintWriter out = response.getWriter()) {
                out.print("GUARDO");
            }
        } else {
            //PROCEDEMOS A ELIMINAR TODO;
            try (PrintWriter out = response.getWriter()) {
                out.print(result);
            }
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
