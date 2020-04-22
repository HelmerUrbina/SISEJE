package UserServices.Seguridad;

import BusinessServices.Beans.BeanUsuario;
import BusinessServices.Beans.BeanUsuarioMenu;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.Impl.UsuarioDAOImpl;
import DataService.Despachadores.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsuariosServlet", urlPatterns = {"/Usuarios"})
public class UsuariosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanUsuario objBnUsuario;
    private Connection objConnection;
    private UsuarioDAO objDsUsuario;

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
        objBnUsuario = new BeanUsuario();
        objBnUsuario.setMode(request.getParameter("mode"));
        objBnUsuario.setUsuario(request.getParameter("codigo"));
        objDsUsuario = new UsuarioDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnUsuario.getMode().equals("usuarios")) {
            if (request.getAttribute("objUsuarios") != null) {
                request.removeAttribute("objUsuarios");
            }
            request.setAttribute("objUsuarios", objDsUsuario.getListaUsuarios());
            CombosDAO objCombos = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objSecciones") != null) {
                request.removeAttribute("objSecciones");
            }
            request.setAttribute("objSecciones", objCombos.getSecciones());
        }
        if (objBnUsuario.getMode().equals("U")) {
            objBnUsuario = objDsUsuario.getUsuario(objBnUsuario);
            result = objBnUsuario.getUsuario() + "+++"
                    + objBnUsuario.getPaterno() + "+++"
                    + objBnUsuario.getMaterno() + "+++"
                    + objBnUsuario.getNombres() + "+++"
                    + objBnUsuario.getSeccion() + "+++"
                    + objBnUsuario.getArea() + "+++"
                    + objBnUsuario.getCorreo() + "+++"
                    + objBnUsuario.getAutorizacion() + "+++"
                    + objBnUsuario.getEstado();
        }
        if (objBnUsuario.getMode().equals("B")) {
            result = "" + TablaOpcionesUsuario();
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "usuarios":
                dispatcher = request.getRequestDispatcher("Seguridad/Usuarios.jsp");
                break;
            default:
                dispatcher = request.getRequestDispatcher("../error.jsp");
                break;
        }
        if (result != null) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.print(result);
            }
        } else {
            dispatcher.forward(request, response);
        }
    }

    private StringBuilder TablaOpcionesUsuario() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"table table-striped table-hover\">");
        sb.append("<tbody>");
        List listaModulos = objDsUsuario.getModulos();
        List listaMenu = objDsUsuario.getMenu();
        List listaOpcion = objDsUsuario.getOpciones(objBnUsuario.getUsuario());
        String opcion;
        for (int j = 0; j < listaModulos.size(); j++) {
            BeanUsuarioMenu modulos = (BeanUsuarioMenu) listaModulos.get(j);
            sb.append("<tr>");
            sb.append("<td style=\"font-weight: bold\">").append(modulos.getDescripcion());
            sb.append("</td>");
            sb.append("</tr>");
            for (int k = 0; k < listaMenu.size(); k++) {
                BeanUsuarioMenu menu = (BeanUsuarioMenu) listaMenu.get(k);
                if (modulos.getModulo().equals(menu.getModulo())) {
                    String checked = "";
                    for (int m = 0; m < listaOpcion.size(); m++) {
                        opcion = (String) listaOpcion.get(m);
                        if (menu.getMenu().equals(opcion)) {
                            checked = "checked";
                        }
                    }
                    sb.append("<tr>");
                    sb.append("<td><input type=\"checkbox\" id=\"").append(menu.getMenu()).append("\" ").append(checked).append(" />");
                    sb.append("<label for=\"").append(menu.getMenu()).append("\">").append(menu.getDescripcion()).append("</label>");
                    sb.append("</td>");
                    sb.append("</tr>");
                }
            }
        }
        sb.append("</tbody>");
        sb.append("</table>");

        return sb;
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
