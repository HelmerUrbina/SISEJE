package UserServices.Consultas;

import BusinessServices.Beans.BeanConsultaBeneficiario;
import BusinessServices.Beans.BeanSentencias;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.ConsultaBeneficiarioDAO;
import DataService.Despachadores.Impl.ConsultaBeneficiarioDAOImpl;
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

@WebServlet(name = "ConsultaBeneficiarioServlet", urlPatterns = {"/ConsultaBeneficiario"})
public class ConsultaBeneficiarioServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanConsultaBeneficiario objBnBeneficiario;
    private Connection objConnection;
    private ConsultaBeneficiarioDAO objDsBeneficiario;

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
        objBnBeneficiario = new BeanConsultaBeneficiario();
        objBnBeneficiario.setMode(request.getParameter("mode"));
        objBnBeneficiario.setDNI(request.getParameter("dni"));
        objBnBeneficiario.setCIP(request.getParameter("cip"));
        objBnBeneficiario.setSentencia(Utiles.Utiles.checkNum(request.getParameter("sentencia")));
        objBnBeneficiario.setResolucion(request.getParameter("resolucion"));
        objDsBeneficiario = new ConsultaBeneficiarioDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnBeneficiario.getMode().equals("beneficiario")) {
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
        }
        if (objBnBeneficiario.getMode().equals("B")) {
            result = "" + BuscarBeneficiario(objDsBeneficiario.getListaBuscaBeneficiario(request.getParameter("personal"), request.getParameter("dni")));
        }
        if (objBnBeneficiario.getMode().equals("A")) {
            objBnBeneficiario = objDsBeneficiario.getBeneficiario(objBnBeneficiario);
            result = objBnBeneficiario.getDNI() + "+++"
                    + objBnBeneficiario.getBeneficiario();
        }
        if (objBnBeneficiario.getMode().equals("G")) {
            result = "" + TablaSentencias(objDsBeneficiario.getListaSentencias(objBnBeneficiario));
        }

        if (objBnBeneficiario.getMode().equals("S")) {
            result = "" + TablaPagoResoluciones(objDsBeneficiario.getListaPagoResoluciones(objBnBeneficiario));
        }

        if (objBnBeneficiario.getMode().equals("TP")) {
            objBnBeneficiario = objDsBeneficiario.getResolucionesDetalle(objBnBeneficiario);
            result = objBnBeneficiario.getFormaPago() + "+++"
                    + objBnBeneficiario.getMes() + "+++"
                    + objBnBeneficiario.getCuotas() + "+++"
                    + objBnBeneficiario.getMonto() + "+++"
                    + objBnBeneficiario.getPorcentaje() + "+++"
                    + objBnBeneficiario.getMontoGenerado();
        }
        if (objBnBeneficiario.getMode().equals("DE")) {
            objBnBeneficiario = objDsBeneficiario.getResolucionesDetalle(objBnBeneficiario);
            result = "" + TablaResolucionesDetalle(objDsBeneficiario.getListaResolucionesDetalle(objBnBeneficiario));
        }

        /*
        
        
        if (objBnBeneficiario.getMode().equals("R")) {
            objBnBeneficiario = objDsBeneficiario.getResoluciones(objBnBeneficiario);
            result = objBnBeneficiario.getPeriodo() + "+++"
                    + objBnBeneficiario.getMesaPartes() + "+++"
                    + objBnBeneficiario.getNumeroResolucion() + "+++"
                    + objBnBeneficiario.getExpediente() + "+++"
                    + objBnBeneficiario.getFecha() + "+++"
                    + objBnBeneficiario.getOficio() + "+++"
                    + objBnBeneficiario.getFechaFin() + "+++"
                    + objBnBeneficiario.getJuez() + "+++"
                    + objBnBeneficiario.getJuzgado();
        }
        if (objBnBeneficiario.getMode().equals("RB")) {
            objBnBeneficiario = objDsBeneficiario.getBeneficiario(objBnBeneficiario);
            result = objBnBeneficiario.getTipoRemuneracion() + "+++"
                    + objBnBeneficiario.getOficio() + "+++"
                    + objBnBeneficiario.getTipoPago() + "+++"
                    + objBnBeneficiario.getGrado() + "+++"
                    + objBnBeneficiario.getUnidad() + "+++"
                    + objBnBeneficiario.getJuez();
        }
        
        
        if (objBnBeneficiario.getMode().equals("J")) {
            result = objDsBeneficiario.getJuzgadoSentencia(objBnBeneficiario);
        }
        if (objBnBeneficiario.getMode().equals("M")) {
            objBnBeneficiario = objDsBeneficiario.getMesaPartes(objBnBeneficiario);
            result = objBnBeneficiario.getOficio() + "+++"
                    + objBnBeneficiario.getFecha() + "+++"
                    + objBnBeneficiario.getJuzgado() + "+++"
                    + objBnBeneficiario.getJuez() + "+++"
                    + objBnBeneficiario.getArma();
        }*/
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO        
        switch (request.getParameter("mode")) {
            case "beneficiario":
                dispatcher = request.getRequestDispatcher("Consultas/Beneficiario.jsp");
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

    private StringBuilder BuscarBeneficiario(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleBeneficiario\">");
            sb.append("<table id=\"TablaBuscarBeneficiario\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">BENEFICIARIO</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanConsultaBeneficiario beneficiario = (BeanConsultaBeneficiario) lista.get(k);
                sb.append("<tr>");
                sb.append("<td><input type=\"radio\" id=\"").append(beneficiario.getDNI()).append("\" name=\"div_dni\" value=\"").append(beneficiario.getDNI()).append("\" class=\"with-gap radio-col-light-blue\">");
                sb.append("<label for=\"").append(beneficiario.getDNI()).append("\" style=\"font-weight: bold\">").append(beneficiario.getDNI()).append("</label>");
                sb.append("</td>");
                sb.append("<td>").append(beneficiario.getBeneficiario()).append("</td>");
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
            sb.append("<th style=\"text-align: center\">TIPO</th>");
            sb.append("<th style=\"text-align: center\">DEMANDADO</th>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">EXPEDIENTE</th>");
            sb.append("<th style=\"text-align: center\">FORMA PAGO</th>");
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
            sb.append("<th style=\"text-align: center\">ACCION</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanConsultaBeneficiario sentencia = (BeanConsultaBeneficiario) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getMes()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getPersonal()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getPeriodo()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getCuenta()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getDNI()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                switch (sentencia.getEstado()) {
                    case "ACTIVO":
                        sb.append("<span class=\"badge bg-green\">").append(sentencia.getEstado()).append("</span>");
                        break;
                    case "PROCESADO":
                        sb.append("<span class=\"badge bg-blue\">").append(sentencia.getEstado()).append("</span>");
                        break;
                    case "INACTIVO":
                        sb.append("<span class=\"badge bg-orange\">").append(sentencia.getEstado()).append("</span>");
                        break;
                    default:
                        sb.append("<span class=\"badge bg-red\">").append(sentencia.getEstado()).append("</span>");
                        break;
                }
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\">");
                sb.append("<button type=\"button\" class=\"btn bg-light-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Ver Pagos\" onclick=\"javascript: fn_VerResolucion('").append(sentencia.getCIP()).append("','").append(sentencia.getSentencia()).append("','").append(sentencia.getResolucion()).append("');\">");
                sb.append("<i class=\"material-icons\">visibility</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn bg-lime btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Detalle Resolución\" onclick=\"javascript: fn_DetalleResolucion('").append(sentencia.getCIP()).append("','").append(sentencia.getSentencia()).append("','").append(sentencia.getResolucion()).append("','").append(sentencia.getTipo()).append("');\">");
                sb.append("<i class=\"material-icons\">add_circle_outline</i>").append("</button> ");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder TablaPagoResoluciones(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetallePagoResolucion\">");
            sb.append("<table id=\"TablaPagosResolucion\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">PERIODO</th>");
            sb.append("<th style=\"text-align: center\">MES</th>");
            sb.append("<th style=\"text-align: center\">CONCEPTO</th>");
            sb.append("<th style=\"text-align: center\">BANCO</th>");
            sb.append("<th style=\"text-align: center\">CUENTA</th>");
            sb.append("<th style=\"text-align: center\">FORMA PAGO</th>");
            sb.append("<th style=\"text-align: center\">GESTIONADO</th>");

            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanConsultaBeneficiario sentencia = (BeanConsultaBeneficiario) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getPeriodo()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getMes()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getEstado()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getBanco()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getCuenta()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getFormaPago()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getMontoProcesado()).append("</td>");
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
                BeanConsultaBeneficiario detalle = (BeanConsultaBeneficiario) lista.get(k);
                sb.append("<tr>");
                sb.append("<td><input type=\"checkbox\" id=\"div_checkbox_").append(detalle.getEstado()).append("\" onclick=\"javascript: fn_ActivaDetalle('").append(detalle.getEstado()).append("');\" class=\"chk-col-blue\" disabled /><label for=\"div_checkbox_").append(detalle.getEstado()).append("\">").append(detalle.getFormaPago()).append("</label></td>");
                sb.append("<td><input type=\"text\" id=\"txt_Monto_").append(detalle.getEstado()).append("\" class=\"form-control\" value=\"").append(detalle.getMonto()).append("\" onchange=\"javascript: CalculaTotal('").append(detalle.getEstado()).append("');\"  disabled /></td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
        }
        return sb;
    }
}
