package UserServices.Resoluciones;

import BusinessServices.Beans.BeanSentencias;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.Impl.SentenciasDAOImpl;
import DataService.Despachadores.SentenciasDAO;
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

@WebServlet(name = "IduResolucionesServlet", urlPatterns = {"/IduResoluciones"})
public class IduResolucionesServlet extends HttpServlet {

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
        java.util.Date fechaExpediente = sdf.parse(Utiles.Utiles.checkFecha(request.getParameter("fechaExpediente")));
        java.util.Date fechaOficio = sdf.parse(Utiles.Utiles.checkFecha(request.getParameter("fechaOficio")));
        objBnSentencias = new BeanSentencias();
        objBnSentencias.setMode(request.getParameter("mode"));
        objBnSentencias.setCIP(request.getParameter("cip"));
        objBnSentencias.setTipo("" + session.getAttribute("area"));
        objBnSentencias.setSentencia(Utiles.Utiles.checkNum(request.getParameter("sentencia")));
        objBnSentencias.setResolucion(Utiles.Utiles.checkNum(request.getParameter("resolucion")));
        objBnSentencias.setPeriodo(request.getParameter("periodo"));
        objBnSentencias.setMesaPartes(request.getParameter("mesaPartes"));
        objBnSentencias.setOficio(request.getParameter("oficio"));
        objBnSentencias.setFechaFin(new java.sql.Date(fechaOficio.getTime()));
        objBnSentencias.setExpediente(request.getParameter("expediente"));
        objBnSentencias.setNumeroResolucion(request.getParameter("numeroResolucion"));
        objBnSentencias.setFecha(new java.sql.Date(fechaExpediente.getTime()));
        objBnSentencias.setJuez(request.getParameter("juez")); 
        objBnSentencias.setJuzgado(request.getParameter("juzgado"));
        objBnSentencias.setLugar(request.getParameter("lugar"));
        objBnSentencias.setDepartamento(request.getParameter("departamento"));
        objBnSentencias.setTipoPago(request.getParameter("tipoPago"));
        objBnSentencias.setCuotas(Utiles.Utiles.checkNum(request.getParameter("cuotas")));
        objBnSentencias.setMonto(Utiles.Utiles.checkDouble(request.getParameter("monto")));
        objBnSentencias.setPorcentaje(Utiles.Utiles.checkDouble(request.getParameter("porcentaje")));
        objDsSentencias = new SentenciasDAOImpl(objConnection);
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        int k = objDsSentencias.iduResoluciones(objBnSentencias, objUsuario.getUsuario());
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(IduResolucionesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IduResolucionesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
