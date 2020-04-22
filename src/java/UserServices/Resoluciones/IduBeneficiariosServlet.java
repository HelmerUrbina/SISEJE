package UserServices.Resoluciones;

import BusinessServices.Beans.BeanBeneficiarios;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.Impl.BeneficiariosDAOImpl;
import DataService.Despachadores.BeneficiariosDAO;
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

@WebServlet(name = "IduBeneficiariosServlet", urlPatterns = {"/IduBeneficiarios"})
public class IduBeneficiariosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanBeneficiarios objBnBeneficiario;
    private Connection objConnection;
    private BeneficiariosDAO objDsBeneficiarios;

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
        java.util.Date fechaNacimiento = sdf.parse(Utiles.Utiles.checkFecha(request.getParameter("fechaNacimiento")));
        objBnBeneficiario = new BeanBeneficiarios();
        objBnBeneficiario.setMode(request.getParameter("mode"));
        objBnBeneficiario.setTipoDocumento(request.getParameter("tipoDocumento"));
        objBnBeneficiario.setDocumento(request.getParameter("codigo"));
        objBnBeneficiario.setApellidoPaterno(request.getParameter("paterno"));
        objBnBeneficiario.setApellidoMaterno(request.getParameter("materno"));
        objBnBeneficiario.setNombres(request.getParameter("nombres"));
        objBnBeneficiario.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
        objBnBeneficiario.setRazonSocial(request.getParameter("razonSocial"));
        objBnBeneficiario.setCCI(request.getParameter("ruc"));
        objDsBeneficiarios = new BeneficiariosDAOImpl(objConnection);
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        int k = objDsBeneficiarios.iduBeneficiario(objBnBeneficiario, objUsuario.getUsuario());
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
            Logger.getLogger(IduBeneficiariosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IduBeneficiariosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
