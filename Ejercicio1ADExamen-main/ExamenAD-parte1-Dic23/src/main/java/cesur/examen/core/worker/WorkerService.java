package cesur.examen.core.worker;

import java.util.Date;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Pablo Robles Lorenzo
 * Fecha: 11/12/2023
 *
 *  No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 *  En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 *  o para seguir la traza de ejecución.
 */
/**
 *  Services classes offers methods to our main application, and can
 *  use multiple methods from DAOs and Entities.
 *  It's a layer between Data Access Layer and Aplication Layer (where
 *  Main app and controllers lives)
 *  It helps to encapsulate multiple opperations with DAOs that can be
 *  reused in application layer.
 */
public class WorkerService {

    public static Worker renovateWorker(String dni) {
        Worker out = null;

        WorkerDAO workerDAO = new WorkerDAO();
        Worker workerToUpdate = workerDAO.getWorkerByDNI(dni);

        if (workerToUpdate != null) {
            workerToUpdate.setFrom(new Date());
            out = workerDAO.update(workerToUpdate);
        }

        return out;
    }
}

