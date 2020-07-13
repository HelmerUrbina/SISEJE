/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import BusinessServices.Beans.BeanSentencias;
import DataService.Despachadores.Impl.SentenciasDAOImpl;
import DataService.Despachadores.SentenciasDAO;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author H-URBINA-M
 */
public class ImportarArchivo {

    private final Connection objConnection;

    public ImportarArchivo(Connection objConnection1) {
        objConnection = objConnection1;
    }

    public String ImportarArchivoExcel (BeanSentencias objBnSentencias, String usuario) throws FileNotFoundException, IOException {
        SentenciasDAO objDsSentencias = new SentenciasDAOImpl(objConnection);
        FileInputStream excelStream = null;
        try {
            String filePath = "C:/SISEJE/Resoluciones/Procesado/" + objBnSentencias.getPeriodo() + "-" + objBnSentencias.getMes() + "-" + objBnSentencias.getTipo() + "-" + objBnSentencias.getArchivo();
            excelStream = new FileInputStream(filePath);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(excelStream);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.iterator();
            rowIterator.next();
            String k;
            while (rowIterator.hasNext()) {
                Row hssfRow = rowIterator.next(); // For each row, iterate through each columns 
                objBnSentencias.setMode("I");
                objBnSentencias.setPeriodo(hssfRow.getCell(0).getStringCellValue().substring(0, 4));
                objBnSentencias.setMes(hssfRow.getCell(0).getStringCellValue().substring(4, 6));
                objBnSentencias.setTipoPago(hssfRow.getCell(1).getStringCellValue());
                objBnSentencias.setTipoRemuneracion("" + hssfRow.getCell(2).getStringCellValue());
                objBnSentencias.setCIP(hssfRow.getCell(3).getStringCellValue());
                objBnSentencias.setMonto(hssfRow.getCell(5).getNumericCellValue());
                objBnSentencias.setRemuneracion(hssfRow.getCell(6).getNumericCellValue());
                objBnSentencias.setJuez(hssfRow.getCell(7).getStringCellValue());
                k = objDsSentencias.iduResolucionesProcesoDescuentos(objBnSentencias, usuario);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Error proceso 1" + fileNotFoundException.getMessage());
            return "No se encontró el fichero: " + fileNotFoundException;
        } catch (IOException ex) {
            System.out.println("Error fichero 1" + ex.getMessage());
            return "Error al procesar el fichero: " + ex.getMessage();
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error fichero 2" + ex.getMessage());
                return "Error al procesar el fichero después de cerrarlo: " + ex.getMessage();
            }
        }
        return null;
    }

    public String ImportarArchivoTXT(BeanSentencias objBnSentencias, String usuario) throws FileNotFoundException, IOException {
        SentenciasDAO objDsSentencias = new SentenciasDAOImpl(objConnection);
        String filePath = "C:/SISEJE/Resoluciones/Procesado/" + objBnSentencias.getPeriodo() + "-" + objBnSentencias.getMes() + "-" + objBnSentencias.getTipo() + "-" + objBnSentencias.getArchivo();
        String cadena;
        String k;
        FileReader f = new FileReader(filePath);
        try (BufferedReader b = new BufferedReader(f)) {
            while ((cadena = b.readLine()) != null) {
                objBnSentencias.setPeriodo(cadena.substring(0, 4));
                objBnSentencias.setMes(cadena.substring(4, 6));
                objBnSentencias.setTipoRemuneracion(cadena.substring(6, 8));
                objBnSentencias.setTipoPago(cadena.substring(8, 12));
                objBnSentencias.setCIP(cadena.substring(12, 21));
                objBnSentencias.setRemuneracion(0.0);
                objBnSentencias.setPorcentaje(Utiles.checkDouble(cadena.substring(21, 28)) / 100);
                objBnSentencias.setMonto(Utiles.checkDouble(cadena.substring(28, 35)) / 100);
                k = objDsSentencias.iduResolucionesProcesoDescuentos(objBnSentencias, usuario);
            }
        }
        return null;
    }
}
