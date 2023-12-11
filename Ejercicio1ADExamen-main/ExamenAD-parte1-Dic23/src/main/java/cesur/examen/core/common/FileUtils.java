package cesur.examen.core.common;

import cesur.examen.core.worker.Worker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:Pablo Robles Lorenzo
 * Fecha:
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */
public class FileUtils {

    private static final Logger log = Logger.getLogger(FileUtils.class.getName());

    public static void toCSV(String fileName, List<Worker> workers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Worker worker : workers) {
                String line = worker.getId() + "," + worker.getName() + "," + worker.getDni() + "," + worker.getFrom();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            log.severe("Error writing to CSV file");
            throw new RuntimeException(e);
        }
    }
}




