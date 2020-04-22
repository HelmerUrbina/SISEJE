package UserServices.Configuracion;

import BusinessServices.Beans.BeanTipoRemuneracion;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.Impl.TipoRemuneracionDAOImpl;
import DataService.Despachadores.TipoRemuneracionDAO;
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

@WebServlet(name = "TipoRemuneracionServlet", urlPatterns = {"/TipoRemuneracion"})
public class TipoRemuneracionServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanTipoRemuneracion objBnTipoRemuneracion;
    private Connection objConnection;
    private TipoRemuneracionDAO objDsTipoRemuneracion;

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
        objBnTipoRemuneracion = new BeanTipoRemuneracion();
        objBnTipoRemuneracion.setMode(request.getParameter("mode"));
        objBnTipoRemuneracion.setTipoPersonal(request.getParameter("tipoPersonal"));
        objBnTipoRemuneracion.setCodigo(request.getParameter("codigo"));
        objDsTipoRemuneracion = new TipoRemuneracionDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnTipoRemuneracion.getMode().equals("G")) {
            result = "" + TablaResultados(objDsTipoRemuneracion.getListaTipoRemuneracion(objBnTipoRemuneracion.getTipoPersonal()));
        }
        if (objBnTipoRemuneracion.getMode().equals("U")) {
            objBnTipoRemuneracion = objDsTipoRemuneracion.getTipoRemuneracion(objBnTipoRemuneracion);
            result = objBnTipoRemuneracion.getCodigo() + "+++"
                    + objBnTipoRemuneracion.getDescripcion() + "+++"
                    + objBnTipoRemuneracion.getTipo() + "+++"
                    + objBnTipoRemuneracion.getEstado();
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "tipoRemuneracion":
                dispatcher = request.getRequestDispatcher("Configuracion/TipoRemuneracion.jsp");
                break;
            default:
                dispatcher = request.getRequestDispatcher("error.jsp");
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

    private StringBuilder TablaResultados(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleTipoRemuneracion\">");
            sb.append("<table id=\"TablaResultados\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CÓDIGO</th>");
            sb.append("<th style=\"text-align: center\">DESCRIPCIÓN</th>");
            sb.append("<th style=\"text-align: center\">TIPO</th>");
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanTipoRemuneracion tipoRemuneracion = (BeanTipoRemuneracion) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(tipoRemuneracion.getCodigo()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(tipoRemuneracion.getDescripcion()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(tipoRemuneracion.getTipo()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                switch (tipoRemuneracion.getEstado()) {
                    case "ACTIVO":
                        sb.append("<span class=\"badge bg-green\">").append(tipoRemuneracion.getEstado()).append("</span>");
                        break;
                    default:
                        sb.append("<span class=\"badge bg-red\">").append(tipoRemuneracion.getEstado()).append("</span>");
                        break;
                }
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\"><button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Área\" onclick=\"javascript: EditarRegistro('").append(tipoRemuneracion.getCodigo()).append("');\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button>").append("&nbsp;");
                sb.append("<button type=\"button\" class=\"btn bg-pink btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Anular Área\" onclick=\"javascript: EliminarRegistro('").append(tipoRemuneracion.getCodigo()).append("');\">");
                sb.append("<i class=\"material-icons\">delete</i>").append("</button>").append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");

        }
        return sb;
    }
}
