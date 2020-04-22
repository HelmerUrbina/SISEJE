package UserServices.MesaPartes;

import BusinessServices.Beans.BeanMesaPartes;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.Impl.MesaPartesDAOImpl;
import DataService.Despachadores.MesaPartesDAO;
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

@WebServlet(name = "MesaPartesServlet", urlPatterns = {"/MesaPartes"})
public class MesaPartesServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanMesaPartes objBnMesaPartes;
    private Connection objConnection;
    private MesaPartesDAO objDsMesaPartes;
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
        objBnMesaPartes = new BeanMesaPartes();
        objBnMesaPartes.setMode(request.getParameter("mode"));
        objBnMesaPartes.setPeriodo(request.getParameter("periodo"));
        objBnMesaPartes.setMes(request.getParameter("mes"));
        objBnMesaPartes.setFechaBusqueda(request.getParameter("dia"));
        objBnMesaPartes.setCodigo(request.getParameter("codigo"));
        objBnMesaPartes.setEstado(request.getParameter("estado"));
        objDsMesaPartes = new MesaPartesDAOImpl(objConnection);
        if (objBnMesaPartes.getMode().equals("mesaPartes")) {
            objDsCombo = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objPeriodos") != null) {
                request.removeAttribute("objPeriodos");
            }
            request.setAttribute("objPeriodos", objDsCombo.getPeriodos());
            if (request.getAttribute("objPrioridad") != null) {
                request.removeAttribute("objPrioridad");
            }
            request.setAttribute("objPrioridad", objDsCombo.getPrioridad());
            if (request.getAttribute("objTipoDocumento") != null) {
                request.removeAttribute("objTipoDocumento");
            }
            request.setAttribute("objTipoDocumento", objDsCombo.getTipoDocumentos());
            if (request.getAttribute("objJuzgados") != null) {
                request.removeAttribute("objJuzgados");
            }
            request.setAttribute("objJuzgados", objDsCombo.getJuzgados());
        }
        if (objBnMesaPartes.getMode().equals("B")) {
            result = "" + TablaResultados(objDsMesaPartes.getListaMesaPartes(objBnMesaPartes));
        }
        if (objBnMesaPartes.getMode().equals("C")) {
            result = "" + ConsultaMesaPartes(objDsMesaPartes.getConsultaMesaPartes(objBnMesaPartes));
        }
        if (objBnMesaPartes.getMode().equals("I")) {
            result = objDsMesaPartes.getNumeroDocumento(objBnMesaPartes);
        }
        if (objBnMesaPartes.getMode().equals("U")) {
            objBnMesaPartes = objDsMesaPartes.getMesaPartes(objBnMesaPartes);
            result = objBnMesaPartes.getNumero() + "+++"
                    + objBnMesaPartes.getFechaRecepcion() + "+++"
                    + objBnMesaPartes.getTipoDocumento() + "+++"
                    + objBnMesaPartes.getNumeroDocumento() + "+++"
                    + objBnMesaPartes.getIndicativo() + "+++"
                    + objBnMesaPartes.getAsunto() + "+++"
                    + objBnMesaPartes.getFechaDocumento() + "+++"
                    + objBnMesaPartes.getPrioridad() + "+++"
                    + objBnMesaPartes.getFolios() + "+++"
                    + objBnMesaPartes.getJuzgado() + "+++"
                    + objBnMesaPartes.getPostFirma() + "+++"
                    + objBnMesaPartes.getArchivo();
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "mesaPartes":
                dispatcher = request.getRequestDispatcher("MesaPartes/MesaPartes.jsp");
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

    private StringBuilder TablaResultados(List lista) {
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
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
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
                sb.append("<td style=\"text-align: center\">");
                switch (mp.getEstado()) {
                    case "ANULADO":
                        sb.append("<span class=\"badge bg-red\">").append(mp.getEstado()).append("</span>");
                        break;
                    case "DECRETADO":
                        sb.append("<span class=\"badge bg-grey\">").append(mp.getEstado()).append("</span>");
                        break;
                    case "GENERADO":
                        sb.append("<span class=\"badge bg-blue\">").append(mp.getEstado()).append("</span>");
                        break;
                    case "RESPONDIDO":
                        sb.append("<span class=\"badge bg-green\">").append(mp.getEstado()).append("</span>");
                        break;
                    default:
                        sb.append("<span class=\"badge bg-black\">").append(mp.getEstado()).append("</span>");
                        break;
                }
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\"><button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Registro\" onclick=\"javascript: EditarRegistro(").append(mp.getCodigo()).append(");\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button>").append("&nbsp;&nbsp;");
                sb.append("<button type=\"button\" class=\"btn bg-pink btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Anular Registro\" onclick=\"javascript: EliminarRegistro(").append(mp.getCodigo()).append(");\">");
                sb.append("<i class=\"material-icons\">delete</i>").append("</button>").append("&nbsp;&nbsp;");
                sb.append("<button type=\"button\" class=\"btn bg-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Subir Archivo\" onclick=\"javascript: SubirArchivo(").append(mp.getCodigo()).append(");\">");
                sb.append("<i class=\"material-icons\">cloud_upload</i>").append("</button>");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;
    }

    private StringBuilder ConsultaMesaPartes(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleMesaPartes\">");
            sb.append("<table id=\"TablaResultados\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CÓDIGO</th>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">ASUNTO</th>");
            sb.append("<th style=\"text-align: center\">FECHA</th>");
            sb.append("<th style=\"text-align: center\">FIRMA</th>");
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
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
                sb.append("<td style=\"text-align: center\">").append(mp.getPostFirma()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                switch (mp.getEstado()) {
                    case "ANULADO":
                        sb.append("<span class=\"badge bg-red\">").append(mp.getEstado()).append("</span>");
                        break;
                    case "DECRETADO":
                        sb.append("<span class=\"badge bg-grey\">").append(mp.getEstado()).append("</span>");
                        break;
                    case "GENERADO":
                        sb.append("<span class=\"badge bg-blue\">").append(mp.getEstado()).append("</span>");
                        break;
                    case "RESPONDIDO":
                        sb.append("<span class=\"badge bg-green\">").append(mp.getEstado()).append("</span>");
                        break;
                    default:
                        sb.append("<span class=\"badge bg-black\">").append(mp.getEstado()).append("</span>");
                        break;
                }
                sb.append("</td>");
                sb.append("<td style=\"text-align: center\">");
                sb.append("<button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Descargar Documento\" onclick=\"javascript: fn_Descargar('").append(mp.getCodigo()).append("'").append(",'").append(mp.getArchivo()).append("');\">");
                sb.append("<i class=\"material-icons\">cloud_download</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn btn-navy btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Seguimiento de Decretos\" onclick=\"javascript: fn_Seguimiento('").append(mp.getCodigo()).append("'").append(");\">");
                sb.append("<i class=\"material-icons\">more</i>").append("</button>");
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
