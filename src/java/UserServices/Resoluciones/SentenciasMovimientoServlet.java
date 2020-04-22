package UserServices.Resoluciones;

import BusinessServices.Beans.BeanSentencias;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.Impl.SentenciasDAOImpl;
import DataService.Despachadores.SentenciasDAO;
import Utiles.ExportarExcel;
import Utiles.ExportarTXT;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SentenciasMovimientoServlet", urlPatterns = {"/SentenciasMovimiento"})
public class SentenciasMovimientoServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanSentencias objBnSentencias;
    private Connection objConnection;
    private SentenciasDAO objDsSentencias;
    private List objConsulta;

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
        objBnSentencias.setTipoCambio(Utiles.Utiles.checkDouble(request.getParameter("tipoCambio")));
        objDsSentencias = new SentenciasDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnSentencias.getMode().equals("movimiento")) {
            CombosDAO objCombos = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objPeriodos") != null) {
                request.removeAttribute("objPeriodos");
            }
            request.setAttribute("objPeriodos", objCombos.getPeriodos());
            if (request.getAttribute("objTipo") != null) {
                request.removeAttribute("objTipo");
            }
            request.setAttribute("objTipo", objCombos.getAreaResolucion());
        }
        if (objBnSentencias.getMode().equals("G")) {
            result = "" + TablaSentenciasMovimiento(objDsSentencias.getListaResolucionesMovimientos(objBnSentencias));
        }
        if (objBnSentencias.getMode().equals("P")) {
            result = objDsSentencias.iduResolucionesMovimientos(objBnSentencias, objUsuario.getUsuario());
        }
        if (objBnSentencias.getMode().equals("E")) {
            int nombreArchivo = (int) Math.floor(Math.random() * 100000000);
            objConsulta = objDsSentencias.getListaResolucionesProceso(objBnSentencias);
            ExportarExcel exportExcel = new ExportarExcel();
            String archivo = "C:/SISEJE/Temporal/" + nombreArchivo + ".xlsx";
            exportExcel.GenerarArchivoDescuentos("" + archivo, objConsulta);
            result = "CORRECTO";
            File fileToDownload = new File(archivo);
            try (FileInputStream fileInputStream = new FileInputStream(fileToDownload);
                    ServletOutputStream out = response.getOutputStream()) {
                String mimeType = new MimetypesFileTypeMap().getContentType(archivo);
                response.setContentType(mimeType);
                response.setContentLength(fileInputStream.available());
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + fileToDownload.getName() + "\"");
                int c;
                while ((c = fileInputStream.read()) != -1) {
                    out.write(c);
                }
                out.flush();
            } catch (IOException e) {
                System.out.println("Error al leer Archivo" + e.getMessage());
            }
            if (fileToDownload.exists()) {
                fileToDownload.delete();
            }
        }
        if (objBnSentencias.getMode().equals("T")) {
            int nombreArchivo = (int) Math.floor(Math.random() * 100000000);
            objConsulta = objDsSentencias.getListaResolucionesProceso(objBnSentencias);
            ExportarTXT exportTXT = new ExportarTXT();
            String archivo = "C:/SISEJE/Temporal/" + nombreArchivo + ".txt";
            exportTXT.GenerarArchivoDescuentos("" + archivo, objConsulta);
            result = "CORRECTO";
            File fileToDownload = new File(archivo);
            try (FileInputStream fileInputStream = new FileInputStream(fileToDownload);
                    ServletOutputStream out = response.getOutputStream()) {
                String mimeType = new MimetypesFileTypeMap().getContentType(archivo);
                response.setContentType(mimeType);
                response.setContentLength(fileInputStream.available());
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + fileToDownload.getName() + "\"");
                int c;
                while ((c = fileInputStream.read()) != -1) {
                    out.write(c);
                }
                out.flush();
            } catch (IOException e) {
                System.out.println("Error al leer Archivo" + e.getMessage());
            }
            if (fileToDownload.exists()) {
                fileToDownload.delete();
            }
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "movimiento":
                dispatcher = request.getRequestDispatcher("Resoluciones/SentenciasMovimiento.jsp");
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

    private StringBuilder TablaSentenciasMovimiento(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<table class=\"table table-striped table-hover\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">NUMERO</th>");
            sb.append("<th style=\"text-align: center\">ESTADO</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanSentencias sentencia = (BeanSentencias) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getCuotas()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getTipoRemuneracion()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                switch (sentencia.getCuotas()) {
                    case 1:
                        sb.append("<button type=\"button\" class=\"btn bg-blue btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Procesar Datos\" onclick=\"javascript: fn_ProcesarDatos();\">");
                        sb.append("<i class=\"material-icons\">loop</i>").append("</button> ");
                        sb.append("<button type=\"button\" class=\"btn bg-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Exportar Datos a Excel\" onclick=\"javascript: fn_ExportarExcel();\">");
                        sb.append("<i class=\"material-icons\">cloud_download</i>").append("</button> ");
                        sb.append("<button type=\"button\" class=\"btn bg-navy btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Exportar Datos a TXT\" onclick=\"javascript: fn_ExportarTXT();\">");
                        sb.append("<i class=\"material-icons\">library_books</i>").append("</button>");
                        break;
                    case 2:
                        sb.append("<button type=\"button\" class=\"btn bg-green btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Exportar Datos de Excel\" onclick=\"javascript: fn_ImportarExcel();\">");
                        sb.append("<i class=\"material-icons\">cloud_upload</i>").append("</button> ");
                        sb.append("<button type=\"button\" class=\"btn bg-navy btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Importat Datos de TXT\" onclick=\"javascript: fn_ImportarTXT();\">");
                        sb.append("<i class=\"material-icons\">attachment </i>").append("</button>");
                        break;
                    default:
                        break;
                }
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
        }
        return sb;
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
