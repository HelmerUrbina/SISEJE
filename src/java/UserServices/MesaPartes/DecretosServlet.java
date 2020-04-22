package UserServices.MesaPartes;

import BusinessServices.Beans.BeanMesaPartes;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.Impl.DecretosDAOImpl;
import DataService.Despachadores.DecretosDAO;
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

@WebServlet(name = "DecretosServlet", urlPatterns = {"/Decretos"})
public class DecretosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanMesaPartes objBnDecretos;
    private Connection objConnection;
    private DecretosDAO objDsDecretos;
    private CombosDAO objDsCombo;

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
        session = request.getSession(false);
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("../FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String result = null;
        objBnDecretos = new BeanMesaPartes();
        objBnDecretos.setMode(request.getParameter("mode"));
        objBnDecretos.setPeriodo(request.getParameter("periodo"));
        objBnDecretos.setMes(request.getParameter("mes"));
        objBnDecretos.setEstado(request.getParameter("estado"));
        objBnDecretos.setNumero(request.getParameter("numero"));
        objBnDecretos.setCodigo(request.getParameter("codigo"));
        objBnDecretos.setSeccion("" + session.getAttribute("seccion"));
        objBnDecretos.setArea("" + session.getAttribute("area"));
        objDsDecretos = new DecretosDAOImpl(objConnection);
        if (objBnDecretos.getMode().equals("decretos")) {
            objDsCombo = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objPeriodos") != null) {
                request.removeAttribute("objPeriodos");
            }
            request.setAttribute("objPeriodos", objDsCombo.getPeriodos());
            if (request.getAttribute("objPrioridad") != null) {
                request.removeAttribute("objPrioridad");
            }
            request.setAttribute("objPrioridad", objDsCombo.getPrioridad());
            if (request.getAttribute("objSecciones") != null) {
                request.removeAttribute("objSecciones");
            }
            request.setAttribute("objSecciones", objDsCombo.getSecciones());
            if (request.getAttribute("objTipoDecretos") != null) {
                request.removeAttribute("objTipoDecretos");
            }
            request.setAttribute("objTipoDecretos", objDsCombo.getTipoDecretos());
            if (request.getAttribute("objTipoDocumento") != null) {
                request.removeAttribute("objTipoDocumento");
            }
            request.setAttribute("objTipoDocumento", objDsCombo.getTipoDocumentos());
            if (request.getAttribute("objTipoRemuneracion") != null) {
                request.removeAttribute("objTipoRemuneracion");
            }
            request.setAttribute("objTipoRemuneracion", objDsCombo.getTipoConceptos());
            if (request.getAttribute("objJuzgados") != null) {
                request.removeAttribute("objJuzgados");
            }
            request.setAttribute("objJuzgados", objDsCombo.getJuzgados());
        }
        if (objBnDecretos.getMode().equals("B")) {
            if (objBnDecretos.getEstado().equals("AC")) {
                result = "" + TablaResultadosPendiente(objDsDecretos.getListaDecretosPendientes(objBnDecretos));
            }
            if (objBnDecretos.getEstado().equals("DE")) {
                result = "" + TablaResultadosDecretados(objDsDecretos.getListaDecretosDecretados(objBnDecretos));
            }
        }
        if (objBnDecretos.getMode().equals("U")) {
            objBnDecretos = objDsDecretos.getDecretos(objBnDecretos);
            result = objBnDecretos.getSeccion() + "+++"
                    + objBnDecretos.getArea() + "+++"
                    + objBnDecretos.getFechaDocumento() + "+++"
                    + objBnDecretos.getComentario() + "+++"
                    + objBnDecretos.getPrioridad() + "+++"
                    + objBnDecretos.getConceptos();
        }
        if (objBnDecretos.getMode().equals("C")) {
            if (objBnDecretos.getEstado().equals("DE")) {
                result = "" + TablaConsultaDecretosPendientes(objDsDecretos.getListaConsultaDecretosPendientes(objBnDecretos));
            }
            if (objBnDecretos.getEstado().equals("RS")) {
                result = "" + TablaConsultaDecretosRespondidos(objDsDecretos.getListaConsultaDecretosRespondidos(objBnDecretos));
            }
        }
        if (objBnDecretos.getMode().equals("P")) {
            objBnDecretos = objDsDecretos.getDecretosRespuesta(objBnDecretos);
            result = objBnDecretos.getTipoDocumento() + "+++"
                    + objBnDecretos.getNumeroDocumento() + "+++"
                    + objBnDecretos.getFechaDocumento() + "+++"
                    + objBnDecretos.getAsunto();
        }
        if (objBnDecretos.getMode().equals("S")) {
            result = "" + TablaSeguimientoDecretos(objDsDecretos.getListaSeguimientoDecretos(objBnDecretos));
        }
        if (objBnDecretos.getMode().equals("A")) {
            result = objDsDecretos.getDecretosAreasDetalle(objBnDecretos);
        }
        if (objBnDecretos.getMode().equals("T")) {
            result = objDsDecretos.getDecretosTipoDecretos(objBnDecretos);
        }
        if (objBnDecretos.getMode().equals("R")) {
            result = objDsDecretos.getDecretosTipoRemuneracion(objBnDecretos);
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "decretos":
                dispatcher = request.getRequestDispatcher("MesaPartes/Decretos.jsp");
                break;
            default:
                dispatcher = request.getRequestDispatcher("error.jsp");
                break;
        }
        if (result != null) {
            response.setContentType("text/html;charset=ISO-8859-1");
            try (PrintWriter out = response.getWriter()) {
                out.print(result);
            }
        } else {
            dispatcher.forward(request, response);
        }
    }

    private StringBuilder TablaResultadosPendiente(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleMesaPartes\">");
            sb.append("<table id=\"TablaResultados\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CORRELATIVO</th>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">ASUNTO</th>");
            sb.append("<th style=\"text-align: center\">FECHA</th>");
            sb.append("<th style=\"text-align: center\">POST FIRMA</th>");
            sb.append("<th style=\"text-align: center\">PRIORIDAD</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanMesaPartes mp = (BeanMesaPartes) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center; font-weight: bold;color: black\">").append(mp.getNumero()).append("</td>");
                sb.append("<td>").append(mp.getTipoDocumento()).append("</td>");
                sb.append("<td>").append(mp.getAsunto()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getFechaDocumento()).append("</td>");
                sb.append("<td>").append(mp.getPostFirma()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getPrioridad()).append("</td>");
                sb.append("<td style=\"text-align: center\"><button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Ver Documento\" onclick=\"javascript: VerRegistro(").append(mp.getCodigo()).append(");\">");
                sb.append("<i class=\"material-icons\">visibility</i>").append("</button>").append("&nbsp;&nbsp;");
                sb.append("<button type=\"button\" class=\"btn btn-success btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Nuevo Decreto\" onclick=\"javascript: NuevoRegistro(").append(mp.getCodigo()).append(");\">");
                sb.append("<i class=\"material-icons\">note_add</i>").append("</button>").append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder TablaResultadosDecretados(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleMesaPartes\">");
            sb.append("<table id=\"TablaResultados\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CORRELATIVO</th>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">AREA</th>");
            sb.append("<th style=\"text-align: center\">FECHA</th>");
            sb.append("<th style=\"text-align: center\">DECRETO</th>");
            sb.append("<th style=\"text-align: center\">OBSERVACIÓN</th>");
            sb.append("<th style=\"text-align: center\">PRIORIDAD</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanMesaPartes mp = (BeanMesaPartes) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center; font-weight: bold;color: black\">").append(mp.getNumero()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getTipoDocumento()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getArea()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getFechaDocumento()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getDecreto()).append("</td>");
                sb.append("<td>").append(Utiles.Utiles.checkStr(mp.getComentario())).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getPrioridad()).append("</td>");
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\"><button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Decreto\" onclick=\"javascript: EditarRegistro('").append(mp.getNumero()).append("', '").append(mp.getCodigo()).append("');\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button>").append("&nbsp;");
                sb.append("<button type=\"button\" class=\"btn bg-pink btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Eliminar Decreto\" onclick=\"javascript: EliminarRegistro('").append(mp.getNumero()).append("', '").append(mp.getCodigo()).append("');\">");
                sb.append("<i class=\"material-icons\">delete</i>").append("</button>").append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder TablaConsultaDecretosPendientes(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleMesaPartes\">");
            sb.append("<table id=\"TablaResultados\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CORRELATIVO</th>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">FECHA</th>");
            sb.append("<th style=\"text-align: center\">DECRETO</th>");
            sb.append("<th style=\"text-align: center\">OBSERVACIÓN</th>");
            sb.append("<th style=\"text-align: center\">PRIORIDAD</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanMesaPartes mp = (BeanMesaPartes) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center; font-weight: bold;color: black\">").append(mp.getNumero()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getTipoDocumento()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getFechaDocumento()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getDecreto()).append("</td>");
                sb.append("<td>").append(Utiles.Utiles.checkStr(mp.getComentario())).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getPrioridad()).append("</td>");
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\">");
                sb.append("<button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Ver Documento\" onclick=\"javascript: fn_Descargar('").append(Utiles.Utiles.checkNum(mp.getNumero())).append("'").append(",'").append(mp.getArchivo()).append("');\">");
                sb.append("<i class=\"material-icons\">cloud_download</i>").append("</button>").append("&nbsp;");
                sb.append("<button type=\"button\" class=\"btn bg-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Respuesta de Documento\" onclick=\"javascript: fn_NuevoRegistro('").append(Utiles.Utiles.checkNum(mp.getNumero())).append("','").append(mp.getCodigo()).append("');\">");
                sb.append("<i class=\"material-icons\">library_add</i>").append("</button>").append("&nbsp;");
                sb.append("<button type=\"button\" class=\"btn bg-black btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Sub Decreto Documento\" onclick=\"javascript: fn_NuevoDecreto('").append(Utiles.Utiles.checkNum(mp.getNumero())).append("','").append(mp.getCodigo()).append("');\">");
                sb.append("<i class=\"material-icons\">group_add</i>").append("</button>");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder TablaConsultaDecretosRespondidos(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleMesaPartes\">");
            sb.append("<table id=\"TablaResultados\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">NÚMERO</th>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">FECHA</th>");
            sb.append("<th style=\"text-align: center\">DECRETO</th>");
            sb.append("<th style=\"text-align: center\">OBSERVACIÓN</th>");
            sb.append("<th style=\"text-align: center\">DOC. RESPUESTA</th>");
            sb.append("<th style=\"text-align: center\">FEC. RESPUESTA</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanMesaPartes mp = (BeanMesaPartes) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center; font-weight: bold;color: black\">").append(mp.getNumero()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getTipoDocumento()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getFechaDocumento()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getDecreto()).append("</td>");
                sb.append("<td>").append(Utiles.Utiles.checkStr(mp.getComentario())).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getPrioridad()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getFechaRecepcion()).append("</td>");
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\"><button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Registro\" onclick=\"javascript: EditarRegistro('").append(mp.getCodigo()).append("', '").append(mp.getNumero()).append("');\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button>").append("&nbsp;");
                sb.append("<button type=\"button\" class=\"btn bg-pink btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Eliminar Registro\" onclick=\"javascript: EliminarRegistro('").append(mp.getCodigo()).append("', '").append(mp.getNumero()).append("');\">");
                sb.append("<i class=\"material-icons\">delete</i>").append("</button>").append("</td>");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder TablaSeguimientoDecretos(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<table class=\"table table-striped table-hover\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">FECHA</th>");
            sb.append("<th style=\"text-align: center\">OBSERVACIÓN</th>");
            sb.append("<th style=\"text-align: center\">DECRETO</th>");
            sb.append("<th style=\"text-align: center\">AREA</th>");
            sb.append("<th style=\"text-align: center\">PRIORIDAD</th>");
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanMesaPartes mp = (BeanMesaPartes) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(mp.getTipoDocumento()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getFechaDocumento()).append("</td>");
                sb.append("<td>").append(Utiles.Utiles.checkStr(mp.getComentario())).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getDecreto()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getArea()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getPrioridad()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(mp.getEstado()).append("</td>");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
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
