package datos;

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAO {

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona( nombre, apellido, email, telefono)VALUES(?,?,?,?)";//cada signo de pregunta=1.nombre, 2.apellido, 3.email, 4.telefono
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ? ";//cada signo de pregunta=nombre, apellido, email, telefono
    private static final String SQL_ELIMINAR = "DELETE FROM persona  WHERE id_persona = ? ";

    
    public List<Persona> seleccionar() {
        Connection conn = null;
        /*este objeto(PreparedStatement)no va permitir crear sentencias en nuestra base de datos*/
        PreparedStatement stmt = null;
        /*ResultSet ejecutar la instruccion sql*/
        ResultSet rs = null;/*rs = resultado*/
        Persona persona = null;/*Creamos un objeto de tipo Persona */
        List<Persona> personas = new ArrayList<>();/*creamos una lista de personas parair guardando todas las personas*/
        try {
            /*realizando la coneccion ala bdd*/
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            /*ejecutar la instruccion sql*/
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                /*sacamos los datos de la bdd y lo convertimos en un objeto*/
                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                /*agregamos la persona ala lista de personas*/
                personas.add(persona);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {/*con el bloque finaly se ejecuta si o si y cerramos los objetos que esten abiertos*/
            try {
                // cerrar los objetos en el orden inverso de que se fueron abriendo
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return personas;
    }
  /*---------------------------------------------------------------*/
    public int actualizar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5,persona.getIdPersona() );
            
            registros = stmt.executeUpdate();/*el metodo executeUpdate modifica,guarda ,actualiza los datos en bdd em el metodo de arriba pusimos executeQuery pero este solo la ejecuta */

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);

            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
    /*---------------------------------------------------------------*/
     public int eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR);
            stmt.setInt(1,persona.getIdPersona() );
            
            registros = stmt.executeUpdate();/*el metodo executeUpdate modifica,guarda ,actualiza,elimina los datos en bdd em el metodo de arriba pusimos executeQuery pero este solo la ejecuta */

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);

            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

     /*--------------------------------------------------------------------------*/
    public int insertar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());

            registros = stmt.executeUpdate();/*el metodo executeUpdate modifica los datos en bdd em el metodo de arriba pusimos executeQuery pero este solo la ejecuta */

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);

            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
}
