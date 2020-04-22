package Utiles;

import BusinessServices.Beans.BeanSentencias;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportarTXT {

    public void GenerarArchivoDescuentos(String nombreArchivo, List objConsulta) {
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter(nombreArchivo);
            try ( //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                    BufferedWriter bfwriter = new BufferedWriter(flwriter)) {
                int porcentajeEntero = 0;
                int montoEntero = 0;
                for (int i = 0; i < objConsulta.size(); i++) {
                    BeanSentencias proceso = (BeanSentencias) objConsulta.get(i);
                    porcentajeEntero = (int) (proceso.getPorcentaje() * 100);
                    montoEntero = (int) (proceso.getMonto() * 100);
                    bfwriter.write(proceso.getPeriodo() + "" + proceso.getMes() + ""
                            + proceso.getTipoRemuneracion() + ""
                            + proceso.getExpediente() + "" + proceso.getCIP() + ""
                            + Utiles.CompletarCeros("" + porcentajeEntero, 7) + ""
                            + Utiles.CompletarCeros("" + montoEntero, 7));
                    bfwriter.newLine();
                }
            }
        } catch (IOException e) {
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
