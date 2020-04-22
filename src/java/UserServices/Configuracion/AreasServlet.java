package UserServices.Configuracion;

import BusinessServices.Beans.BeanArea;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.AreasDAO;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.AreasDAOImpl;
import DataService.Despachadores.Impl.CombosDAOImpl;
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

@WebServlet(name = "AreasServlet", urlPatterns = {"/Areas"})
public class AreasServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanArea objBnAreas;
    private Connection objConnection;
    private AreasDAO objDsArea;

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
        objBnAreas = new BeanArea();
        objBnAreas.setMode(request.getParameter("mode"));
        objBnAreas.setSeccion(request.getParameter("seccion"));
        objBnAreas.setCodigo(request.getParameter("codigo"));
        objDsArea = new AreasDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS. 
        if (objBnAreas.getMode().equals("areas")) {
            CombosDAO objDsCombo = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objSecciones") != null) {
                request.removeAttribute("objSecciones");
            }
            request.setAttribute("objSecciones", objDsCombo.getSecciones());
        }
        if (objBnAreas.getMode().equals("G")) {
            result = "" + TablaResultados(objDsArea.getListaAreas(objBnAreas.getSeccion()));
        }
        if (objBnAreas.getMode().equals("U")) {
            objBnAreas = objDsArea.getArea(objBnAreas);
            result = objBnAreas.getDescripcion() + "+++"
                    + objBnAreas.getAbreviatura() + "+++"
                    + objBnAreas.getEstado();
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "areas":
                dispatcher = request.getRequestDispatcher("Configuracion/Areas.jsp");
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
            sb.append("<div id=\"DetalleAreas\">");
            sb.append("<table id=\"TablaResultados\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">DESCRIPCIÓN</th>");
            sb.append("<th style=\"text-align: center\">ABREVIATURA</th>");
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanArea area = (BeanArea) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(area.getDescripcion()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(area.getAbreviatura()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                switch (area.getEstado()) {
                    case "ACTIVO":
                        sb.append("<span class=\"badge bg-green\">").append(area.getEstado()).append("</span>");
                        break;
                    default:
                        sb.append("<span class=\"badge bg-red\">").append(area.getEstado()).append("</span>");
                        break;
                }
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\"><button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Área\" onclick=\"javascript: EditarRegistro('").append(area.getCodigo()).append("');\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button>").append("&nbsp;");
                sb.append("<button type=\"button\" class=\"btn bg-pink btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Anular Área\" onclick=\"javascript: EliminarRegistro('").append(area.getCodigo()).append("');\">");
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
