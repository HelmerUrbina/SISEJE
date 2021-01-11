package Utiles;

import BusinessServices.Beans.BeanComun;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
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

@WebServlet(name = "CombosServlet", urlPatterns = {"/Combos"})
public class CombosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private List lista;
    private Connection objConnection;
    private CombosDAO objDsCombos;

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
            dispatcher = request.getRequestDispatcher("/SISEJE/FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String accion = request.getParameter("accion");
        String codigo = request.getParameter("codigo");
        StringBuilder sb = new StringBuilder();
        objDsCombos = new CombosDAOImpl(objConnection);
        switch (accion) {
            case "MESA_PARTES":
                lista = objDsCombos.getMesaPartes(codigo, Integer.parseInt(request.getParameter("area")));
                sb = combo(lista, "id=\"cbo_MesaPartes\" onchange=\"javascript: cargarMesaPartes();\"");
                break;
            case "JUZGADO":
                lista = objDsCombos.getJuzgados(codigo);
                sb = combo(lista, "id=\"cbo_Juzgados\"");
                break;
            case "JUZGADO_DEPARTAMENTO":
                lista = objDsCombos.getJuzgados(request.getParameter("tipoJuzgado"), request.getParameter("departamento"));
                sb = combo(lista, "id=\"cbo_Juzgados\"");
                break;
            case "AREAS":
                lista = objDsCombos.getAreas(codigo);
                sb = combo(lista, "id=\"cbo_Areas\"");
                break;
            case "BANCO":
                lista = objDsCombos.getBancos();
                sb = combo(lista, "id=\"cbo_Banco\"");
                break;
            case "LUGAR":
                lista = objDsCombos.getLugar(request.getParameter("departamento"));
                sb = combo(lista, "id=\"cbo_Lugar\"");
                break;
            case "CONCEPTO_PLANILLA":
                lista = objDsCombos.getConceptosSentencia(request.getParameter("periodo"), request.getParameter("mes"), request.getParameter("tipo"));
                sb = combo(lista, "id=\"cbo_Conceptos\" onchange=\"javascript: fn_BuscarDatos();\"");
                break;
            case "TIPO_PERSONAL_PLANILLA":
                lista = objDsCombos.getTipoPersonalPlanilla(request.getParameter("periodo"), request.getParameter("mes"), request.getParameter("tipo"), request.getParameter("tipoPersonal"));
                sb = combo(lista, "id=\"cbo_Planilla\" onchange=\"javascript: fn_BuscarDatos();\"");
                break;
            case "TIPO_PERSONAL_PLANILLA_MCPP":
                lista = objDsCombos.getTipoPersonalPlanillaMCPP(request.getParameter("periodo"), request.getParameter("mes"), request.getParameter("tipo"), request.getParameter("tipoPersonal"));
                sb = combo(lista, "id=\"cbo_Planilla\" onchange=\"javascript: fn_BuscarDatos();\"");
                break;
            case "BENEFICIARIO":
                sb.append(objDsCombos.getBeneficiario(codigo));
                break;
            case "BENEFICIARIO_CUENTA":
                sb.append(objDsCombos.getBeneficiarioCuenta(request.getParameter("dni"), request.getParameter("banco")));
                break;
            case "BENEFICIARIO_SENTENCIA":
                sb.append(objDsCombos.getBeneficiarioSentencia(request.getParameter("cip"), request.getParameter("sentencia")));
                break;
            case "RAZONSOCIAL":
                sb.append(objDsCombos.getRazonSocial(codigo));
                break;
            default:
                break;
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(sb);
        }
    }

    private StringBuilder combo(List list, String atr) {
        StringBuilder sb = new StringBuilder();
        BeanComun comun;
        if (list != null) {
            for (int k = 0; k < list.size(); k++) {
                comun = (BeanComun) list.get(k);
                sb.append("<option value=\"").append(comun.getCodigo()).append("\">");
                sb.append(comun.getDescripcion());
                sb.append("</option>");
            }
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
