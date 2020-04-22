package UserServices.MesaPartes;

import BusinessServices.Beans.BeanMesaPartes;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.Impl.MesaPartesDAOImpl;
import DataService.Despachadores.MesaPartesDAO;
import Utiles.Utiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@WebServlet(name = "IduMesaPartesServlet", urlPatterns = {"/IduMesaPartes"})
@MultipartConfig(location = "C:/SISEJE/MesaPartes")
public class IduMesaPartesServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanMesaPartes objBnMesaPartes;
    private Connection objConnection;
    private MesaPartesDAO objDsMesaPartes;
    private static final long serialVersionUID = 1L;

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
        java.util.Date fecha_doc = sdf.parse(Utiles.checkFecha(request.getParameter("fechaDocumento")));
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        sdf1.setLenient(false); //No Complaciente en Fecha
        java.util.Date fecha_rec = sdf1.parse(Utiles.checkFecha(request.getParameter("fechaRecepcion")));
        objBnMesaPartes = new BeanMesaPartes();
        objBnMesaPartes.setMode(request.getParameter("mode"));
        objBnMesaPartes.setPeriodo(request.getParameter("periodo"));
        objBnMesaPartes.setTipo(request.getParameter("tipo"));
        objBnMesaPartes.setCodigo(request.getParameter("codigo"));
        objBnMesaPartes.setNumero(request.getParameter("numero"));
        objBnMesaPartes.setFechaRecepcion(new java.sql.Date(fecha_rec.getTime()));
        objBnMesaPartes.setTipoDocumento(request.getParameter("tipoDocumento"));
        objBnMesaPartes.setNumeroDocumento(request.getParameter("numeroDocumento"));
        objBnMesaPartes.setIndicativo(request.getParameter("indicativo"));
        objBnMesaPartes.setAsunto(request.getParameter("asunto"));
        objBnMesaPartes.setFechaDocumento(new java.sql.Date(fecha_doc.getTime()));
        objBnMesaPartes.setPrioridad(request.getParameter("prioridad"));
        objBnMesaPartes.setFolios(Utiles.checkNum(request.getParameter("folios")));
        objBnMesaPartes.setJuzgado(request.getParameter("juzgado"));
        objBnMesaPartes.setPostFirma(request.getParameter("firma"));
        objBnMesaPartes.setArchivo(request.getParameter("archivo"));
        objBnMesaPartes.setEstado(request.getParameter("estado"));
        if (objBnMesaPartes.getMode().equals("A")) {
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                if (null != Utiles.getFileName(part)) {
                    objBnMesaPartes.setArchivo(Utiles.getFileName(part));
                    part.write(objBnMesaPartes.getPeriodo() + "-" + objBnMesaPartes.getTipo() + "-" + Utiles.checkNum(objBnMesaPartes.getNumero()) + "-" + objBnMesaPartes.getArchivo());
                }
            }
        }
        objDsMesaPartes = new MesaPartesDAOImpl(objConnection);
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        int k = objDsMesaPartes.iduMesaPartes(objBnMesaPartes, objUsuario.getUsuario());
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
            Logger.getLogger(IduMesaPartesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IduMesaPartesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
