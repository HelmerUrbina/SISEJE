package UserServices.Resoluciones;

import BusinessServices.Beans.BeanJuzgados;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.Impl.JuzgadosDAOImpl;
import DataService.Despachadores.JuzgadosDAO;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "JuzgadosServlet", urlPatterns = {"/Juzgados"})
public class JuzgadosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanJuzgados objBnTipoJuzgados;
    private Connection objConnection;
    private JuzgadosDAO objDsJuzgado;

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
        objBnTipoJuzgados = new BeanJuzgados();
        objBnTipoJuzgados.setMode(request.getParameter("mode"));
        objBnTipoJuzgados.setCodigo(request.getParameter("codigo"));
        objDsJuzgado = new JuzgadosDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnTipoJuzgados.getMode().equals("juzgados")) {
            CombosDAO objCombos = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objTipoJuzgados") != null) {
                request.removeAttribute("objTipoJuzgados");
            }
            request.setAttribute("objTipoJuzgados", objCombos.getTipoJuzgados());
            if (request.getAttribute("objDepartamentos") != null) {
                request.removeAttribute("objDepartamentos");
            }
            request.setAttribute("objDepartamentos", objCombos.getDepartamentos());
            if (request.getAttribute("objJuzgados") != null) {
                request.removeAttribute("objJuzgados");
            }
            request.setAttribute("objJuzgados", objDsJuzgado.getListaJuzgados());
        }
        if (objBnTipoJuzgados.getMode().equals("U")) {
            objBnTipoJuzgados = objDsJuzgado.getJuzgado(objBnTipoJuzgados);
            result = objBnTipoJuzgados.getTipoJuzgados() + "+++"
                    + objBnTipoJuzgados.getNombre() + "+++"
                    + objBnTipoJuzgados.getDireccion() + "+++"
                    + objBnTipoJuzgados.getTelefono() + "+++"
                    + objBnTipoJuzgados.getDepartamento()+ "+++"
                    + objBnTipoJuzgados.getEstado();           
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "juzgados":
                dispatcher = request.getRequestDispatcher("Resoluciones/Juzgados.jsp");
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

}
