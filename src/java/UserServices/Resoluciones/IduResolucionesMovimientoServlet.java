package UserServices.Resoluciones;

import BusinessServices.Beans.BeanSentencias;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.Impl.SentenciasDAOImpl;
import DataService.Despachadores.SentenciasDAO;
import Utiles.ImportarArchivo;
import Utiles.Utiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "IduResolucionesMovimientoServlet", urlPatterns = {"/IduResolucionesMovimiento"})
@MultipartConfig(location = "C:/SISEJE/Resoluciones/Procesado")
public class IduResolucionesMovimientoServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanSentencias objBnSentencias;
    private Connection objConnection;
    private SentenciasDAO objDsSentencias;
    private static final long serialVersionUID = 1L;

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
        objBnSentencias.setPeriodo(request.getParameter("periodo"));
        objBnSentencias.setMes(request.getParameter("mes"));
        objBnSentencias.setTipo(request.getParameter("tipo"));
        objBnSentencias.setArchivo(request.getParameter("archivo"));
        objBnSentencias.setMonto(0.0);
        objDsSentencias = new SentenciasDAOImpl(objConnection);
        result = objDsSentencias.iduResolucionesProcesoDescuentos(objBnSentencias, objUsuario.getUsuario());
        if (result == null) {
            if (objBnSentencias.getMode().equals("S")) {
                Collection<Part> parts = request.getParts();
                for (Part part : parts) {
                    if (null != Utiles.getFileName(part)) {
                        objBnSentencias.setArchivo(Utiles.getFileName(part));
                        part.write(objBnSentencias.getPeriodo() + "-" + objBnSentencias.getMes() + "-" + objBnSentencias.getTipo() + "-" + objBnSentencias.getArchivo());
                    }
                }
            }
            ImportarArchivo imp = new ImportarArchivo(objConnection);
            if (request.getParameter("tipo").endsWith("txt")) {
                imp.ImportarArchivoTXT(objBnSentencias, objUsuario.getUsuario());
            } else {
                imp.ImportarArchivoExcel(objBnSentencias, objUsuario.getUsuario());
            }
            result = objDsSentencias.iduResolucionesProceso(objBnSentencias, objUsuario.getUsuario());
        }
        // EN CASO DE NO HABER PROBLEMAS RETORNAMOS UNA NUEVA CONSULTA CON TODOS LOS DATOS.
        response.setContentType("text/html;charset=UTF-8");
        if (result == null) {
            try (PrintWriter out = response.getWriter()) {
                out.print("GUARDO");
            }
        } else {
            //PROCEDEMOS A ELIMINAR TODO;
            try (PrintWriter out = response.getWriter()) {
                out.print(result);
            }
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
