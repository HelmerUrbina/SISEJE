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

@WebServlet(name = "SentenciasServlet", urlPatterns = {"/Sentencias"})
public class SentenciasServlet extends HttpServlet {

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
        objBnSentencias.setTipo("" + session.getAttribute("area"));
        objBnSentencias.setSentencia(Utiles.Utiles.checkNum(request.getParameter("sentencia")));
        objBnSentencias.setCIP(request.getParameter("cip"));
        objBnSentencias.setResolucion(Utiles.Utiles.checkNum(request.getParameter("resolucion")));
        objDsSentencias = new SentenciasDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnSentencias.getMode().equals("sentencias")) {
            CombosDAO objCombos = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objPeriodos") != null) {
                request.removeAttribute("objPeriodos");
            }
            request.setAttribute("objPeriodos", objCombos.getPeriodos());
            if (request.getAttribute("objJuzgados") != null) {
                request.removeAttribute("objJuzgados");
            }
            request.setAttribute("objJuzgados", objCombos.getJuzgados());
            if (request.getAttribute("objBancos") != null) {
                request.removeAttribute("objBancos");
            }
            request.setAttribute("objBancos", objCombos.getBancos());
            if (request.getAttribute("objTipoPagos") != null) {
                request.removeAttribute("objTipoPagos");
            }
            request.setAttribute("objTipoPagos", objCombos.getTipoPagos());
        }
        if (objBnSentencias.getMode().equals("B")) {
            result = "" + BuscarPersonal(objDsSentencias.getListaBuscaPersonal(request.getParameter("personal"), request.getParameter("cip")));
        }
        if (objBnSentencias.getMode().equals("A")) {
            objBnSentencias = objDsSentencias.getPersonal(objBnSentencias);
            result = objBnSentencias.getCIP() + "+++"
                    + objBnSentencias.getDNI() + "+++"
                    + objBnSentencias.getPersonal() + "+++"
                    + objBnSentencias.getGrado() + "+++"
                    + objBnSentencias.getArma() + "+++"
                    + objBnSentencias.getUnidad() + "+++"
                    + objBnSentencias.getSituacion();
        }
        if (objBnSentencias.getMode().equals("G")) {
            result = "" + TablaSentencias(objDsSentencias.getListaSentencias(objBnSentencias));
        }
        if (objBnSentencias.getMode().equals("U")) {
            objBnSentencias = objDsSentencias.getSentencias(objBnSentencias);
            result = objBnSentencias.getOficio() + "+++"
                    + objBnSentencias.getFecha();
        }
        if (objBnSentencias.getMode().equals("S")) {
            result = "" + TablaResoluciones(objDsSentencias.getListaResoluciones(objBnSentencias));
        }
        if (objBnSentencias.getMode().equals("R")) {
            objBnSentencias = objDsSentencias.getResoluciones(objBnSentencias);
            result = objBnSentencias.getPeriodo() + "+++"
                    + objBnSentencias.getMesaPartes() + "+++"
                    + objBnSentencias.getMes() + "+++"
                    + objBnSentencias.getNumeroResolucion() + "+++"
                    + objBnSentencias.getExpediente() + "+++"
                    + objBnSentencias.getFecha() + "+++"
                    + objBnSentencias.getOficio() + "+++"
                    + objBnSentencias.getFechaFin() + "+++"
                    + objBnSentencias.getJuez() + "+++"
                    + objBnSentencias.getJuzgado();
        }
        if (objBnSentencias.getMode().equals("RB")) {
            objBnSentencias = objDsSentencias.getBeneficiario(objBnSentencias);
            result = objBnSentencias.getTipoRemuneracion() + "+++"
                    + objBnSentencias.getOficio() + "+++"
                    + objBnSentencias.getTipoPago() + "+++"
                    + objBnSentencias.getGrado() + "+++"
                    + objBnSentencias.getUnidad() + "+++"
                    + objBnSentencias.getJuez();
        }
        if (objBnSentencias.getMode().equals("TP")) {
            objBnSentencias = objDsSentencias.getResolucionesDetalle(objBnSentencias);
            result = objBnSentencias.getTipoPago() + "+++"
                    + objBnSentencias.getTipoMoneda() + "+++"
                    + objBnSentencias.getCuotas() + "+++"
                    + objBnSentencias.getMonto() + "+++"
                    + objBnSentencias.getPorcentaje() + "+++"
                    + objBnSentencias.getTotal();
        }
        if (objBnSentencias.getMode().equals("DE")) {
            objBnSentencias = objDsSentencias.getResolucionesDetalle(objBnSentencias);
            result = "" + TablaResolucionesDetalle(objDsSentencias.getListaResolucionesDetalle(objBnSentencias));
        }
        if (objBnSentencias.getMode().equals("J")) {
            result = objDsSentencias.getJuzgadoSentencia(objBnSentencias);
        }
        if (objBnSentencias.getMode().equals("M")) {
            objBnSentencias = objDsSentencias.getMesaPartes(objBnSentencias, request.getParameter("periodo"), request.getParameter("mesaPartes"));
            result = objBnSentencias.getOficio() + "+++"
                    + objBnSentencias.getFecha() + "+++"
                    + objBnSentencias.getJuzgado() + "+++"
                    + objBnSentencias.getJuez() + "+++"
                    + objBnSentencias.getArma() + "+++"
                    + objBnSentencias.getGrado();
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO        
        switch (request.getParameter("mode")) {
            case "sentencias":
                dispatcher = request.getRequestDispatcher("Resoluciones/Sentencias.jsp");
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

    private StringBuilder BuscarPersonal(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetallePersonal\">");
            sb.append("<table id=\"TablaBuscarPersonal\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CIP</th>");
            sb.append("<th style=\"text-align: center\">DNI</th>");
            sb.append("<th style=\"text-align: center\">PERSONAL</th>");
            sb.append("<th style=\"text-align: center\">GRADO</th>");
            sb.append("<th style=\"text-align: center\">SITUACIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanSentencias sentencia = (BeanSentencias) lista.get(k);
                sb.append("<tr>");
                sb.append("<td><input type=\"radio\" id=\"").append(sentencia.getCIP()).append("\" name=\"div_cip\" value=\"").append(sentencia.getCIP()).append("\" class=\"with-gap radio-col-light-blue\">");
                sb.append("<label for=\"").append(sentencia.getCIP()).append("\" style=\"font-weight: bold\">").append(sentencia.getCIP()).append("</label>");
                sb.append("</td>");
                sb.append("<td>").append(sentencia.getDNI()).append("</td>");
                sb.append("<td>").append(sentencia.getPersonal()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getGrado()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getSituacion()).append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder TablaSentencias(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleSentencia\">");
            sb.append("<table id=\"TablaSentencias\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CÓDIGO</th>");
            sb.append("<th style=\"text-align: center\">TIPO</th>");
            sb.append("<th style=\"text-align: center\">MOTIVO</th>");
            sb.append("<th style=\"text-align: center\">FECHA</th>");
            sb.append("<th style=\"text-align: center\">EXPEDIENTE</th>");
            sb.append("<th style=\"text-align: center\">BENEFICIARIO</th>");
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanSentencias sentencia = (BeanSentencias) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getSentencia()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getUnidad()).append("</td>");
                sb.append("<td>").append(sentencia.getOficio()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getFecha()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getJuzgado()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getJuez()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                switch (sentencia.getSituacion()) {
                    case "ACTIVO":
                        sb.append("<span class=\"badge bg-green\">").append(sentencia.getSituacion()).append("</span>");
                        break;
                    case "CERRADO":
                        sb.append("<span class=\"badge bg-blue\">").append(sentencia.getSituacion()).append("</span>");
                        break;
                    default:
                        sb.append("<span class=\"badge bg-red\">").append(sentencia.getSituacion()).append("</span>");
                        break;
                }
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\">");
                sb.append("<button type=\"button\" class=\"btn bg-light-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Ver Histórico\" onclick=\"javascript: DetalleRegistroSentencia(").append(sentencia.getSentencia()).append(");\">");
                sb.append("<i class=\"material-icons\">visibility</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Sentencia\" onclick=\"javascript: EditarRegistroSentencia(").append(sentencia.getSentencia()).append(");\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-pink btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Anular Sentencia\" onclick=\"javascript: EliminarRegistroSentencia(").append(sentencia.getSentencia()).append(");\">");
                sb.append("<i class=\"material-icons\">delete</i>").append("</button>");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder TablaResoluciones(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleResoluciones\">");
            sb.append("<table id=\"TablaResoluciones\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">RESOLUCIÓN</th>");
            sb.append("<th style=\"text-align: center\">MESA DE PARTES</th>");
            sb.append("<th style=\"text-align: center\">EXPEDIENTE</th>");
            sb.append("<th style=\"text-align: center\">FECHA</th>");
            sb.append("<th style=\"text-align: center\">FORMA DE PAGO</th>");
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanSentencias resolucion = (BeanSentencias) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getNumeroResolucion()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getMesaPartes()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getExpediente()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getFecha()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(resolucion.getGrado()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                switch (resolucion.getSituacion()) {
                    case "ACTIVO":
                        sb.append("<span class=\"badge bg-green\">").append(resolucion.getSituacion()).append("</span>");
                        break;
                    case "PROCESADO":
                        sb.append("<span class=\"badge bg-blue\">").append(resolucion.getSituacion()).append("</span>");
                        break;
                    case "INACTIVO":
                        sb.append("<span class=\"badge bg-orange\">").append(resolucion.getSituacion()).append("</span>");
                        break;
                    default:
                        sb.append("<span class=\"badge bg-red\">").append(resolucion.getSituacion()).append("</span>");
                        break;
                }
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\">");
                sb.append("<button type=\"button\" class=\"btn bg-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Beneficiario\" onclick=\"javascript: RegistrarBeneficiario('").append(resolucion.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">note_add</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-lime btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Conceptos\" onclick=\"javascript: DetalleResolucion('").append(resolucion.getResolucion()).append("','").append(resolucion.getTipoPago()).append("');\">");
                sb.append("<i class=\"material-icons\">add_circle_outline</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Resolución\" onclick=\"javascript: EditarRegistroResolucion('").append(resolucion.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-pink btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Eliminar Resolución\" onclick=\"javascript: EliminarRegistroResolucion('").append(resolucion.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">delete</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-black btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Reporte\" onclick=\"javascript: ReporteResolucion('").append(resolucion.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">print</i>").append("</button> ");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder TablaResolucionesDetalle(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<table class=\"table table-striped table-hover\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CÓNCEPTO</th>");
            sb.append("<th style=\"text-align: center\">MONTO</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanSentencias detalle = (BeanSentencias) lista.get(k);
                sb.append("<tr>");
                sb.append("<td><input type=\"checkbox\" id=\"div_checkbox_").append(detalle.getTipoRemuneracion()).append("\" onclick=\"javascript: fn_ActivaDetalle('").append(detalle.getTipoRemuneracion()).append("');\" class=\"chk-col-blue\" /><label for=\"div_checkbox_").append(detalle.getTipoRemuneracion()).append("\">").append(detalle.getOficio()).append("</label></td>");
                sb.append("<td><input type=\"text\" id=\"txt_Monto_").append(detalle.getTipoRemuneracion()).append("\" class=\"form-control\" value=\"").append(detalle.getMonto()).append("\" onchange=\"javascript: CalculaTotal('").append(detalle.getTipoRemuneracion()).append("');\"  disabled /></td>");
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
