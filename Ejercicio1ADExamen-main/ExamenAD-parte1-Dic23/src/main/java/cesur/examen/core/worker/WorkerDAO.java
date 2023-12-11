package cesur.examen.core.worker;

import cesur.examen.core.common.DAO;
import cesur.examen.core.common.JDBCUtils;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Pablo Robles Lorenzo
 * Fecha: 11/12/2023
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */
@Log
public class WorkerDAO implements DAO<Worker> {

    private final String QUERY_ORDER_BY = "SELECT * FROM trabajador ORDER BY desde";
    private final String QUERY_BY_DNI = "SELECT * FROM trabajador WHERE dni=?";
    private final String UPDATE_BY_ID = "UPDATE trabajador SET desde=? WHERE dni=?";

    @Override
    public Worker save(Worker worker) {
        return null;
    }

    @Override
    public Worker update(Worker worker) {
        Worker out = null;

        try (PreparedStatement st = JDBCUtils.getConn().prepareStatement(UPDATE_BY_ID)) {
            st.setDate(1, JDBCUtils.dateUtilToSQL(new Date(System.currentTimeMillis())));
            st.setString(2, worker.getDni());

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                out = worker;
            }
        } catch (SQLException e) {
            log.severe("Error in updateWorker()");
            throw new RuntimeException(e);
        }

        return out;
    }

    @Override
    public boolean remove(Worker worker) {
        return false;
    }

    @Override
    public Worker get(Long id) {
        return null;
    }

    public Worker getWorkerByDNI(String dni) {
        if (JDBCUtils.getConn() == null) {
            throw new RuntimeException("Connection is not created!");
        }

        Worker out = null;

        try (PreparedStatement st = JDBCUtils.getConn().prepareStatement(QUERY_BY_DNI)) {
            st.setString(1, dni);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Worker w = new Worker();
                w.setId(rs.getLong("id"));
                w.setName(rs.getString("nombre"));
                w.setDni(rs.getString("dni"));
                w.setFrom(rs.getDate("desde"));
                out = w;
            }
        } catch (SQLException e) {
            log.severe("Error in getWorkerByDNI()");
            throw new RuntimeException(e);
        }
        return out;
    }

    @Override
    public List<Worker> getAll() {
        return null;
    }

    public List<Worker> getAllOrderByFrom() {
        ArrayList<Worker> out = new ArrayList<>();

        try (Statement st = JDBCUtils.getConn().createStatement()) {
            ResultSet rs = st.executeQuery(QUERY_ORDER_BY);
            while (rs.next()) {
                Worker w = new Worker();
                w.setId(rs.getLong("id"));
                w.setName(rs.getString("nombre"));
                w.setDni(rs.getString("dni"));
                w.setFrom(rs.getDate("desde"));
                out.add(w);
            }
        } catch (SQLException e) {
            log.severe("Error in getAllOrderByFrom()");
            throw new RuntimeException(e);
        }

        return out;
    }
}

