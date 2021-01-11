/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserServices.Resoluciones;

import BusinessServices.Beans.BeanSentencias;
import BusinessServices.Beans.BeanUsuario;
import DataService.Despachadores.CombosDAO;
import DataService.Despachadores.Impl.CombosDAOImpl;
import DataService.Despachadores.Impl.SentenciasDAOImpl;
import DataService.Despachadores.SentenciasDAO;
import Utiles.ExportarExcel;
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

/**
 *
 * @author H-URBINA-M
 */
@WebServlet(name = "SentenciasPlanillaMCPPServlet", urlPatterns = {"/SentenciasPlanillaMCPP"})
public class SentenciasPlanillaMCPPServlet extends HttpServlet {

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
        objBnSentencias.setTipoRemuneracion(request.getParameter("concepto"));
        objBnSentencias.setUnidad(request.getParameter("numero"));
        objDsSentencias = new SentenciasDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnSentencias.getMode().equals("planillaMCPP")) {
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
            result = "" + TablaSentenciasMovimiento(objDsSentencias.getListaResolucionesPlanillaMCPP(objBnSentencias));
        }
        if (objBnSentencias.getMode().equals("B")) {
            result = "" + TablaSentenciasPendientes(objDsSentencias.getListaResolucionesPlanillaMCPP(objBnSentencias));
        }
        if (objBnSentencias.getMode().equals("E")) {
            int nombreArchivo = (int) Math.floor(Math.random() * 100000000);
            objConsulta = objDsSentencias.getListaResolucionesMovimientoValidacion(objBnSentencias);
            ExportarExcel exportExcel = new ExportarExcel();
            String archivo = "C:/SISEJE/Temporal/" + nombreArchivo + ".xlsx";
            exportExcel.GenerarArchivoValidacionPlanilla("" + archivo, objConsulta);
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
            case "planillaMCPP":
                dispatcher = request.getRequestDispatcher("Resoluciones/SentenciasPlanillaMCPP.jsp");
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
            sb.append("<th style=\"text-align: center\">CIP</th>");
            sb.append("<th style=\"text-align: center\">PERSONAL</th>");
            sb.append("<th style=\"text-align: center\">DNI</th>");
            sb.append("<th style=\"text-align: center\">DNI BENEFICIARO(A)</th>");
            sb.append("<th style=\"text-align: center\">BENEFICIARO(A)</th>");
            sb.append("<th style=\"text-align: center\">BANCO</th>");
            sb.append("<th style=\"text-align: center\">NRO CTA</th>");
            sb.append("<th style=\"text-align: center\">TIPO PAGO</th>");
            sb.append("<th style=\"text-align: center\">IMPORTE</th>");
            sb.append("<th style=\"text-align: center\">ACCIÓN</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanSentencias sentencia = (BeanSentencias) lista.get(k);
                sb.append("<tr>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getCIP()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getPersonal()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getJuez()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getExpediente()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getArma()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getOficio()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getNumeroResolucion()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getTipoPago()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getMonto()).append("</td>");
                sb.append("<td style=\"text-align: center\">");
                if (!objBnSentencias.getUnidad().equals("0")) {
                    sb.append("<button type=\"button\" class=\"btn bg-pink btn-xs waves-effect\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Eliminar Registro\" onclick=\"javascript: fn_EliminarRegistro(");
                    sb.append(sentencia.getCIP()).append(",").append(sentencia.getSentencia()).append(",").append(sentencia.getResolucion()).append(");\">");
                    sb.append("<i class=\"material-icons\">delete</i>").append("</button>").append("&nbsp;");
                }
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
        }
        return sb;
    }
    
     private StringBuilder TablaSentenciasPendientes(List lista) {
        StringBuilder sb = new StringBuilder();
        if (lista != null) {
            sb.append("<table class=\"table table-striped table-hover\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"text-align: center\">CIP</th>");
            sb.append("<th style=\"text-align: center\">PERSONAL</th>");
            sb.append("<th style=\"text-align: center\">DNI</th>");
            sb.append("<th style=\"text-align: center\">DNI BENEFICIARO(A)</th>");
            sb.append("<th style=\"text-align: center\">BENEFICIARO(A)</th>");
            sb.append("<th style=\"text-align: center\">NRO CTA</th>");
            sb.append("<th style=\"text-align: center\">TIPO PAGO</th>");
            sb.append("<th style=\"text-align: center\">IMPORTE</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            for (int k = 0; k < lista.size(); k++) {
                BeanSentencias sentencia = (BeanSentencias) lista.get(k);
                sb.append("<tr>");
                sb.append("<td><input type=\"checkbox\" id=\"div_checkbox_").append(sentencia.getCIP()).append("\" class=\"chk-col-blue\" value=\"").append(sentencia.getCIP()).append("---").append(sentencia.getSentencia()).append("---").append(sentencia.getResolucion()).append("\" /><label for=\"div_checkbox_").append(sentencia.getCIP()).append("\">").append(sentencia.getCIP()).append("</label></td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getPersonal()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getJuez()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getExpediente()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getArma()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getNumeroResolucion()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getTipoPago()).append("</td>");
                sb.append("<td style=\"text-align: center\">").append(sentencia.getMonto()).append("</td>");
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
