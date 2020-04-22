package UserServices.Resoluciones;

import BusinessServices.Beans.BeanSentencias;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.Impl.SentenciasDAOImpl;
import DataService.Despachadores.SentenciasDAO;
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

@WebServlet(name = "SentenciasProcesarServlet", urlPatterns = {"/SentenciasProcesar"})
public class SentenciasProcesarServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanSentencias objBnSentencias;
    private Connection objConnection;
    private SentenciasDAO objDsSentencias;

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
        objBnSentencias = new BeanSentencias();
        objBnSentencias.setMode(request.getParameter("mode"));
        objBnSentencias.setCIP(request.getParameter("cip"));
        objBnSentencias.setSentencia(Utiles.Utiles.checkNum(request.getParameter("sentencia")));
        objBnSentencias.setResolucion(Utiles.Utiles.checkNum(request.getParameter("resolucion")));
        objBnSentencias.setTipo("" + session.getAttribute("area"));
        objDsSentencias = new SentenciasDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnSentencias.getMode().equals("sentenciasProcesar")) {
            CombosDAO objCombos = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objPeriodos") != null) {
                request.removeAttribute("objPeriodos");
            }
            request.setAttribute("objPeriodos", objCombos.getPeriodos());
            if (request.getAttribute("objTipoJuzgados") != null) {
                request.removeAttribute("objTipoJuzgados");
            }
            request.setAttribute("objTipoJuzgados", objCombos.getTipoJuzgados());
            if (request.getAttribute("objBancos") != null) {
                request.removeAttribute("objBancos");
            }
            request.setAttribute("objBancos", objCombos.getBancos());
            if (request.getAttribute("objJuzgados") != null) {
                request.removeAttribute("objJuzgados");
            }
            request.setAttribute("objJuzgados", objCombos.getJuzgados());
        }
        if (objBnSentencias.getMode().equals("S")) {
            result = "" + TablaResolucionesSinProcesar(objDsSentencias.getListaResolucionesActivas(objBnSentencias));
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "sentenciasProcesar":
                dispatcher = request.getRequestDispatcher("Resoluciones/SentenciasProcesar.jsp");
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

    private StringBuilder TablaResolucionesSinProcesar(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleSentencia\">");
            sb.append("<table id=\"TablaSentencias\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">DEMANDADO</th>");
            sb.append("<th style=\"text-align: center\">TIPO</th>");
            sb.append("<th style=\"text-align: center\">MESA DE PARTES</th>");
            sb.append("<th style=\"text-align: center\">EXPEDIENTE</th>");
            sb.append("<th style=\"text-align: center\">FORMA DE PAGO</th>");
            sb.append("<th style=\"text-align: center\">DEMANDANTE</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanSentencias resolucion = (BeanSentencias) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getPersonal()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getUnidad()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getMesaPartes()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getExpediente()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getGrado()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getSituacion()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                sb.append("<button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Ver Resolución\" onclick=\"javascript: EditarRegistroResolucion('").append(resolucion.getCIP()).append("','").append(resolucion.getSentencia()).append("','").append(resolucion.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-amber btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Beneficiario\" onclick=\"javascript: RegistrarBeneficiario('").append(resolucion.getCIP()).append("','").append(resolucion.getSentencia()).append("','").append(resolucion.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">note_add</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-deep-orange btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Conceptos\" onclick=\"javascript: DetalleResolucion('").append(resolucion.getCIP()).append("','").append(resolucion.getSentencia()).append("','").append(resolucion.getResolucion()).append("','").append(resolucion.getTipoPago()).append("');\">");
                sb.append("<i class=\"material-icons\">add_circle_outline</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-black btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Reporte\" onclick=\"javascript: ReporteResolucion('").append(resolucion.getCIP()).append("','").append(resolucion.getSentencia()).append("','").append(resolucion.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">print</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Cerrar Resolución\" onclick=\"javascript: CerrarResolucion('").append(resolucion.getCIP()).append("','").append(resolucion.getSentencia()).append("','").append(resolucion.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">check_circle</i>").append("</button> ");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
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
