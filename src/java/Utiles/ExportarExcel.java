package Utiles;

import BusinessServices.Beans.BeanSentencias;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportarExcel {

    public void GenerarArchivoDescuentos(String nombreArchivo, List objConsulta) {
        File archivo = new File(nombreArchivo);
        Workbook workbook = new XSSFWorkbook();
        Sheet pagina = workbook.createSheet("Descuentos");
        String[] titulos = {"Periodo", "Mes", "TipoPlanilla", "CodDescuento", "CIP", "Porcentaje", "Fijo"};
        Row fila = pagina.createRow(0);
        for (int i = 0; i < titulos.length; i++) {
            Cell celda = fila.createCell(i);
            celda.setCellValue(titulos[i]);
        }
        for (int i = 0; i < objConsulta.size(); i++) {
            BeanSentencias proceso = (BeanSentencias) objConsulta.get(i);
            fila = pagina.createRow(1 + i);
            fila.createCell(0).setCellValue(proceso.getPeriodo());
            fila.createCell(1).setCellValue(proceso.getMes());
            fila.createCell(2).setCellValue(proceso.getTipoRemuneracion());
            fila.createCell(3).setCellValue(proceso.getExpediente());
            fila.createCell(4).setCellValue(proceso.getCIP());
            fila.createCell(5).setCellValue(proceso.getPorcentaje());
            fila.createCell(6).setCellValue(proceso.getMonto());
        }
        for (int colNum = 0; colNum < fila.getLastCellNum(); colNum++) {
            workbook.getSheetAt(0).autoSizeColumn(colNum);
        }
        try {
            FileOutputStream salida = new FileOutputStream(archivo);
            workbook.write(salida);
            workbook.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void GenerarArchivoMovimientos(String nombreArchivo, List objConsulta) {
        File archivo = new File(nombreArchivo);
        Workbook workbook = new XSSFWorkbook();
        Sheet pagina = workbook.createSheet("Descuentos");
        String[] titulos = {"Periodo", "Mes", "Tipo", "Numero", "Cod Adminstrativo",
            "Nombre", "DNI Demandado", "Juzgado", "Descripcion", "DNI Demandado",
            "Beneficiario", "Tipo Pago", "Nro Cuenta", "Banco", "Importe"};
        Row fila = pagina.createRow(0);
        for (int i = 0; i < titulos.length; i++) {
            Cell celda = fila.createCell(i);
            celda.setCellValue(titulos[i]);
        }
        for (int i = 0; i < objConsulta.size(); i++) {
            BeanSentencias proceso = (BeanSentencias) objConsulta.get(i);
            fila = pagina.createRow(1 + i);
            fila.createCell(0).setCellValue(proceso.getPeriodo());
            fila.createCell(1).setCellValue(proceso.getMes());
            fila.createCell(2).setCellValue(proceso.getTipo());
            fila.createCell(3).setCellValue(proceso.getUnidad());
            fila.createCell(4).setCellValue(proceso.getCIP());
            fila.createCell(5).setCellValue(proceso.getPersonal());
            fila.createCell(6).setCellValue(proceso.getJuez());
            fila.createCell(7).setCellValue(proceso.getJuzgado());
            fila.createCell(8).setCellValue(proceso.getTipoRemuneracion());
            fila.createCell(9).setCellValue(proceso.getExpediente());
            fila.createCell(10).setCellValue(proceso.getSituacion());
            fila.createCell(11).setCellValue(proceso.getTipoPago());
            fila.createCell(12).setCellValue(proceso.getNumeroResolucion());
            fila.createCell(13).setCellValue(proceso.getOficio());
            fila.createCell(14).setCellValue(proceso.getMonto());
        }
        for (int colNum = 0; colNum < fila.getLastCellNum(); colNum++) {
            workbook.getSheetAt(0).autoSizeColumn(colNum);
        }
        try {
            FileOutputStream salida = new FileOutputStream(archivo);
            workbook.write(salida);
            workbook.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
