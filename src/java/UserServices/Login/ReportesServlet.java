package UserServices.Login;

import BusinessServices.Beans.BeanReporte;
import BusinessServices.Beans.BeanUsuario;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@WebServlet(name = "ReportesServlet", urlPatterns = {"/Reportes"})
public class ReportesServlet extends HttpServlet {

    private HttpSession session = null;
    private ServletConfig config = null;
    private ServletContext context = null;
    private RequestDispatcher dispatcher;
    private Connection objConnection;
    private BeanReporte reporte;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException, ParseException {
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession();
        //VERIFICAMOS QUE LA SESSION SEA VALIDA        
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("../FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        reporte = new BeanReporte();
        reporte.setReporte(request.getParameter("reporte"));
        reporte.setPeriodo(request.getParameter("periodo"));
        reporte.setMes(request.getParameter("mes"));
        reporte.setCodigo(request.getParameter("codigo"));
        reporte.setCodigo2(request.getParameter("codigo2"));
        reporte.setCIP(request.getParameter("cip"));
        reporte.setSentencia(request.getParameter("sentencia"));
        reporte.setTipo(request.getParameter("tipo"));
        String nombre = "";
        switch (reporte.getReporte()) {
            //REPORTES DEL MODULO DE MESA DE PARTES
            case "MPAR0001":
                nombre = "MesaPartes/MPAR0001.jasper";
                break;
            case "MPAR0002":
                nombre = "MesaPartes/MPAR0002.jasper";
                break;
            //REPORTES DEL MODULO DE SENTENCIAS
            case "RESO0001":
                nombre = "Resoluciones/RESO0001.jasper";
                break;
            case "RESO0002":
                nombre = "Resoluciones/RESO0002.jasper";
                if (reporte.getCodigo().equals("00")) {
                    reporte.setCodigo("%");
                }
                break;
            case "RESO0003":
                nombre = "Resoluciones/RESO0003.jasper";
                if (reporte.getCodigo().equals("00")) {
                    reporte.setCodigo("%");
                }
                break;
            case "RESO0004":
                nombre = "Resoluciones/RESO0004.jasper";
                if (reporte.getCodigo().equals("00")) {
                    reporte.setCodigo("%");
                }
                break;
            case "RESO0005":
                nombre = "Resoluciones/RESO0005.jasper";
                if (reporte.getCodigo().equals("00")) {
                    reporte.setCodigo("%");
                }
                break;
            case "RESO0006":
                nombre = "Resoluciones/RESO0006.jasper";
                if (reporte.getCodigo().equals("00")) {
                    reporte.setCodigo("%");
                }
                break;
            case "RESO0007":
                nombre = "Resoluciones/RESO0007.jasper";
                break;
            case "RESO0008":
                nombre = "Resoluciones/RESO0008.jasper";
                break;
            case "RESO0009":
                nombre = "Resoluciones/RESO0009.jasper";
                break;
            case "RESO0010":
                nombre = "Resoluciones/RESO0010.jasper";
                break;
            case "RESO0011":
                nombre = "Resoluciones/RESO0011.jasper";
                break;
            case "RESO0012":
                nombre = "Resoluciones/RESO0012.jasper";
                break;
            case "RESO0013":
                nombre = "Resoluciones/RESO0013.jasper";
                break;
            case "RESO0014":
                nombre = "Resoluciones/RESO0014.jasper";
                break;
            case "RESO0015":
                nombre = "Resoluciones/RESO0015.jasper";
                break;
            case "RESO0016":
                nombre = "Resoluciones/RESO0016.jasper";
                break;
            case "RESO0017":
                nombre = "Resoluciones/RESO0017.jasper";
                break;
            case "RESO0018":
                nombre = "Resoluciones/RESO0018.jasper";
                break;
            case "RESO0019":
                nombre = "Resoluciones/RESO0019.jasper";
                break;
            case "RESO0020":
                nombre = "Resoluciones/RESO0020.jasper";
                break;
            case "RESO0021":
                nombre = "Resoluciones/RESO0021.jasper";
                break;
            default:
                break;
        }
        InputStream stream = context.getResourceAsStream("/Reportes/" + nombre);
        if (stream == null) {
            throw new IllegalArgumentException("No se encuentra el reporte con nombre: " + nombre);
        }
        Map parameters = new HashMap();
        parameters.put("REPORT_LOCALE", new Locale("en", "US"));
        parameters.put("PERIODO", reporte.getPeriodo());
        parameters.put("MES", reporte.getMes());
        parameters.put("CODIGO", reporte.getCodigo());
        parameters.put("CODIGO2", reporte.getCodigo2());
        parameters.put("TIPO", reporte.getTipo());
        parameters.put("CIP", reporte.getCIP());
        parameters.put("SENTENCIA", reporte.getSentencia());
        parameters.put("USUARIO", objUsuario.getUsuario());
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parameters, objConnection);
        response.setContentType("application/pdf");
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        exporter.exportReport();
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
        } catch (JRException ex) {
            Logger.getLogger(ReportesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (JRException ex) {
            Logger.getLogger(ReportesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
