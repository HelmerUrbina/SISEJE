package UserServices.Resoluciones;

import BusinessServices.Beans.BeanBeneficiarios;
import BusinessServices.Beans.BeanSentencias;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.BeneficiariosDAO;
import DataService.Despachadores.Impl.BeneficiariosDAOImpl;
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

@WebServlet(name = "BeneficiariosServlet", urlPatterns = {"/Beneficiarios"})
public class BeneficiariosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanBeneficiarios objBnBeneficiario;
    private Connection objConnection;
    private BeneficiariosDAO objDsBeneficiario;

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
        objBnBeneficiario = new BeanBeneficiarios();
        objBnBeneficiario.setMode(request.getParameter("mode"));
        objBnBeneficiario.setTipoDocumento(request.getParameter("tipoDocumento"));
        objBnBeneficiario.setDocumento(request.getParameter("codigo"));
        objBnBeneficiario.setCuenta(request.getParameter("cuenta"));
        objDsBeneficiario = new BeneficiariosDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnBeneficiario.getMode().equals("G")) {
            result = "" + TablaBeneficiarios(objDsBeneficiario.getBeneficiarios(objBnBeneficiario.getTipoDocumento(), objBnBeneficiario.getDocumento()));
        }
        if (objBnBeneficiario.getMode().equals("S")) {
            result = "" + TablaBeneficiariosCuentas(objDsBeneficiario.getBeneficiariosCuentas(objBnBeneficiario.getDocumento()));
        }
        if (objBnBeneficiario.getMode().equals("U")) {
            objBnBeneficiario = objDsBeneficiario.getBeneficiario(objBnBeneficiario);
            result = objBnBeneficiario.getTipoDocumento() + "+++"
                    + objBnBeneficiario.getApellidoPaterno() + "+++"
                    + objBnBeneficiario.getApellidoMaterno() + "+++"
                    + objBnBeneficiario.getNombres() + "+++"
                    + objBnBeneficiario.getFechaNacimiento() + "+++"
                    + objBnBeneficiario.getRazonSocial() + "+++"
                    + objBnBeneficiario.getCCI();
        }
        if (objBnBeneficiario.getMode().equals("C")) {
            objBnBeneficiario = objDsBeneficiario.getBeneficiarioCuenta(objBnBeneficiario);
            result = objBnBeneficiario.getBanco() + "+++"
                    + objBnBeneficiario.getApellidoPaterno() + "+++"
                    + objBnBeneficiario.getCCI();
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "beneficiarios":
                dispatcher = request.getRequestDispatcher("Resoluciones/Beneficiarios.jsp");
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

    private StringBuilder TablaBeneficiarios(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleBeneficiarios\">");
            sb.append("<table id=\"TablaResultados\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">DOCUMENTO</th>");
            sb.append("<th style=\"text-align: center\">AP. PATERNO</th>");
            sb.append("<th style=\"text-align: center\">AP. MATERNO</th>");
            sb.append("<th style=\"text-align: center\">NOMBRES</th>");
            sb.append("<th style=\"text-align: center\">FEC. NACIMIENTO</th>");
            sb.append("<th style=\"text-align: center\">RAZÓN SOCIAL</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanBeneficiarios beneficiario = (BeanBeneficiarios) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(beneficiario.getDocumento()).append("</td>");
                sb.append("<td>").append(beneficiario.getApellidoPaterno()).append("</td>");
                sb.append("<td>").append(beneficiario.getApellidoMaterno()).append("</td>");
                sb.append("<td>").append(beneficiario.getNombres()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(beneficiario.getFechaNacimiento()).append("</td>");
                sb.append("<td>").append(beneficiario.getRazonSocial()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                sb.append("<button type=\"button\" class=\"btn bg-light-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Ver Cuentas\" onclick=\"javascript: fn_DetalleRegistroBeneficiario('").append(beneficiario.getDocumento()).append("');\">");
                sb.append("<i class=\"material-icons\">visibility</i>").append("</button> ");
                sb.append("<button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Registro\" onclick=\"javascript: fn_EditarRegistro('").append(beneficiario.getDocumento()).append("');\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button> ");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</div>");
        }
        return sb;

    }

    private StringBuilder TablaBeneficiariosCuentas(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<div id=\"DetalleBeneficiarios\">");
            sb.append("<table id=\"TablaCuentas\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">BANCO</th>");
            sb.append("<th style=\"text-align: center\">CUENTA</th>");
            sb.append("<th style=\"text-align: center\">CCI</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanBeneficiarios beneficiario = (BeanBeneficiarios) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(beneficiario.getBanco()).append("</td>");
                sb.append("<td>").append(beneficiario.getTipoDocumento()).append("</td>");
                sb.append("<td>").append(beneficiario.getCCI()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                sb.append("<button type=\"button\" class=\"btn btn-primary btn-xs waves-effect\" toggle=\"tooltip\" data-placement=\"bottom\" title=\"Editar Cuenta\" onclick=\"javascript: fn_EditarRegistroCuenta('").append(beneficiario.getCuenta()).append("','").append(beneficiario.getCuenta()).append("');\">");
                sb.append("<i class=\"material-icons\">mode_edit</i>").append("</button> ");
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
