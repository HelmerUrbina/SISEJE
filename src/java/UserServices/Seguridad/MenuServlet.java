package UserServices.Seguridad;

import BusinessServices.Beans.BeanMenu;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.Impl.MenuDAOImpl;
import DataService.Despachadores.MenuDAO;
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

@WebServlet(name = "MenuServlet", urlPatterns = {"/Menu"})
public class MenuServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanMenu objBnMenu;
    private Connection objConnection;
    private MenuDAO objDsMenu;

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
        objBnMenu = new BeanMenu();
        objBnMenu.setMode(request.getParameter("mode"));
        objBnMenu.setCodigo(request.getParameter("codigo"));
        objDsMenu = new MenuDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnMenu.getMode().equals("menu")) {
            if (request.getAttribute("objMenus") != null) {
                request.removeAttribute("objMenus");
            }
            request.setAttribute("objMenus", objDsMenu.getListaMenu());
            CombosDAO combo = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objModulos") != null) {
                request.removeAttribute("objModulos");
            }
            request.setAttribute("objModulos", combo.getModulos());
        }
        if (objBnMenu.getMode().equals("U")) {
            objBnMenu = objDsMenu.getMenu(objBnMenu);
            result = objBnMenu.getModulo() + "+++"
                    + objBnMenu.getNombre() + "+++"
                    + objBnMenu.getServlet() + "+++"
                    + objBnMenu.getModo() + "+++"
                    + objBnMenu.getEstado();
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "menu":
                dispatcher = request.getRequestDispatcher("Seguridad/Menu.jsp");
                break;
            default:
                dispatcher = request.getRequestDispatcher("../error.jsp");
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
