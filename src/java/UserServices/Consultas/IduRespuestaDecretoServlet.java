package UserServices.Consultas;

import BusinessServices.Beans.BeanMesaPartes;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.DecretosDAO;
import DataService.Despachadores.Impl.DecretosDAOImpl;
import Utiles.Utiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "IduRespuestaDecretoServlet", urlPatterns = {"/IduRespuestaDecreto"})
public class IduRespuestaDecretoServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanMesaPartes objBnDecretos;
    private Connection objConnection;
    private DecretosDAO objDsDecretos;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); //No Complaciente en Fecha
        java.util.Date fechaDocumento = sdf.parse(Utiles.checkFecha(request.getParameter("fechaDocumento")));
        objBnDecretos = new BeanMesaPartes();
        objBnDecretos.setMode(request.getParameter("mode"));
        objBnDecretos.setPeriodo(request.getParameter("periodo"));
        objBnDecretos.setCodigo(request.getParameter("codigo"));
        objBnDecretos.setNumero(request.getParameter("numero"));
        objBnDecretos.setDecreto(request.getParameter("decreto"));
        objBnDecretos.setTipoDocumento(request.getParameter("tipoDocumento"));
        objBnDecretos.setNumeroDocumento(request.getParameter("numeroDocumento"));
        objBnDecretos.setFechaDocumento(new java.sql.Date(fechaDocumento.getTime()));
        objBnDecretos.setAsunto(request.getParameter("asunto"));
        objDsDecretos = new DecretosDAOImpl(objConnection);
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        int k = objDsDecretos.iduDecretosRespuesta(objBnDecretos, objUsuario.getUsuario());
        // EN CASO DE NO HABER PROBLEMAS RETORNAMOS UNA NUEVA CONSULTA CON TODOS LOS DATOS.
        response.setContentType("text/html;charset=UTF-8");
        if (result == null) {
            result = "GUARDO";
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(result);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(IduRespuestaDecretoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(IduRespuestaDecretoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
