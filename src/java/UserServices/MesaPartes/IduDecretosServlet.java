package UserServices.MesaPartes;

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

@WebServlet(name = "IduDecretosServlet", urlPatterns = {"/IduDecretos"})
public class IduDecretosServlet extends HttpServlet {

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
        java.util.Date fecha_rec = sdf.parse(Utiles.checkFecha(request.getParameter("fechaRecepcion")));
        objBnDecretos = new BeanMesaPartes();
        objBnDecretos.setMode(request.getParameter("mode"));
        objBnDecretos.setPeriodo(request.getParameter("periodo"));
        objBnDecretos.setTipo("I");
        objBnDecretos.setNumero(request.getParameter("numero"));
        objBnDecretos.setCodigo(request.getParameter("codigo"));
        objBnDecretos.setFechaRecepcion(new java.sql.Date(fecha_rec.getTime()));
        objBnDecretos.setSeccion(request.getParameter("seccion"));
        objBnDecretos.setPrioridad(request.getParameter("prioridad"));
        objBnDecretos.setComentario(request.getParameter("observacion"));
        objBnDecretos.setFolios(Utiles.checkNum(request.getParameter("prorrateo")));
        objBnDecretos.setConceptos(request.getParameter("conceptos"));
        objDsDecretos = new DecretosDAOImpl(objConnection);
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        int k = 0;
        String ListaArea[][] = null;
        switch (objBnDecretos.getMode()) {
            case "I":
                if (Utiles.checkNum(request.getParameter("prorrateo")) == 0) {
                    objBnDecretos.setFolios(1);
                }
                ListaArea = Utiles.generaLista(request.getParameter("area"), 1);
                for (int j = 0; j < objBnDecretos.getFolios(); j++) {
                    for (String[] area : ListaArea) {
                        objBnDecretos.setMode("I");
                        objBnDecretos.setCodigo("0");
                        objBnDecretos.setArea("" + Integer.valueOf(area[0].trim()));
                        k = objDsDecretos.iduDecretos(objBnDecretos, objUsuario.getUsuario());
                        String ListaDecreto[][] = Utiles.generaLista(request.getParameter("decreto"), 1);
                        for (String[] decreto : ListaDecreto) {
                            objBnDecretos.setDecreto("" + Integer.valueOf(decreto[0].trim()));
                            k = objDsDecretos.iduDecretosTipoDecretos(objBnDecretos, objUsuario.getUsuario());
                        }
                        String ListaRemuneracion[][] = Utiles.generaLista(request.getParameter("conceptos"), 1);
                        for (String[] remuneracion : ListaRemuneracion) {                            
                            objBnDecretos.setConceptos(remuneracion[0].trim());
                            k = objDsDecretos.iduDecretosTipoRemuneracion(objBnDecretos, objUsuario.getUsuario());
                        }
                    }
                }
                break;
            case "U":
                objBnDecretos.setMode("D");
                objBnDecretos.setCodigo("0");
                k = objDsDecretos.iduDecretos(objBnDecretos, objUsuario.getUsuario());
                ListaArea = Utiles.generaLista(request.getParameter("area"), 1);
                for (String[] area : ListaArea) {
                    objBnDecretos.setMode("I");
                    objBnDecretos.setCodigo("0");
                    objBnDecretos.setArea("" + Integer.valueOf(area[0].trim()));
                    k = objDsDecretos.iduDecretos(objBnDecretos, objUsuario.getUsuario());
                    String ListaDecreto[][] = Utiles.generaLista(request.getParameter("decreto"), 1);
                    for (String[] decreto : ListaDecreto) {
                        objBnDecretos.setDecreto("" + Integer.valueOf(decreto[0].trim()));
                        k = objDsDecretos.iduDecretosTipoDecretos(objBnDecretos, objUsuario.getUsuario());
                    }
                    String ListaRemuneracion[][] = Utiles.generaLista(request.getParameter("conceptos"), 1);
                    for (String[] remuneracion : ListaRemuneracion) {
                        objBnDecretos.setConceptos(remuneracion[0].trim());
                        k = objDsDecretos.iduDecretosTipoRemuneracion(objBnDecretos, objUsuario.getUsuario());
                    }
                }
                break;
            case "D":
                objBnDecretos.setCodigo("0");
                k = objDsDecretos.iduDecretos(objBnDecretos, objUsuario.getUsuario());
                break;
            default:
                break;
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(IduDecretosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IduDecretosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
