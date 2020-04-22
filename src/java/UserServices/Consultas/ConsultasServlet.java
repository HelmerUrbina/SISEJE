package UserServices.Consultas;

import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ConsultasServlet", urlPatterns = {"/Consultas"})
public class ConsultasServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private Connection objConnection;

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

        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        CombosDAO objCombos = new CombosDAOImpl(objConnection);
        if (request.getAttribute("objPeriodos") != null) {
            request.removeAttribute("objPeriodos");
        }
        request.setAttribute("objPeriodos", objCombos.getPeriodos());
        if (request.getAttribute("objTipoDocumento") != null) {
            request.removeAttribute("objTipoDocumento");
        }
        request.setAttribute("objTipoDocumento", objCombos.getTipoDocumentos());
        if (request.getParameter("mode").equals("decretos")) {
            if (request.getAttribute("objPrioridad") != null) {
                request.removeAttribute("objPrioridad");
            }
            request.setAttribute("objPrioridad", objCombos.getPrioridad());
            if (request.getAttribute("objSecciones") != null) {
                request.removeAttribute("objSecciones");
            }
            request.setAttribute("objSecciones", objCombos.getSecciones());
            if (request.getAttribute("objTipoDecretos") != null) {
                request.removeAttribute("objTipoDecretos");
            }
            request.setAttribute("objTipoDecretos", objCombos.getTipoDecretos());
            if (request.getAttribute("objTipoDocumento") != null) {
                request.removeAttribute("objTipoDocumento");
            }
            request.setAttribute("objTipoDocumento", objCombos.getTipoDocumentos());
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "mesaPartes":
                dispatcher = request.getRequestDispatcher("Consultas/MesaPartes.jsp");
                break;
            case "decretos":
                dispatcher = request.getRequestDispatcher("Consultas/Decretos.jsp");
                break;
            default:
                dispatcher = request.getRequestDispatcher("error.jsp");
                break;
        }
        dispatcher.forward(request, response);

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
