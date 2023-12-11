package cesur.examen.core.common;

import lombok.Getter;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

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
public class JDBCUtils {

    @Getter
    private static final Connection conn;

    static {
        try {
            Properties props = new Properties();
            props.load(JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties"));

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            conn = DriverManager.getConnection(url, user, password);

            if (conn == null) log.info("JDBCUtils Not implemented yet!");
            else log.info("Successfully connected!");

        } catch (Exception ex) {
            log.severe("Error connecting to the database");
            throw new RuntimeException("Error connecting to the database");
        }
    }

    public static java.sql.Date dateUtilToSQL(java.util.Date d) {
        return new java.sql.Date(d.getTime());
    }
}
