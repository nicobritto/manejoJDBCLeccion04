package test;

import datos.Conexion;
import datos.PersonaJDBC;
import domain.Persona;
import java.sql.Connection;
import java.sql.*;
import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaJDBC personaJDBC = new PersonaJDBC(conexion);

            Persona modificarPersona = new Persona();
            modificarPersona.setIdPersona(2);
            modificarPersona.setNombre("CARLITA");
            modificarPersona.setApellido("asd");
            modificarPersona.setEmail("ddd@gmail.com");
            modificarPersona.setTelefono("4615732");

            personaJDBC.actualizar(modificarPersona);

            //terminada esta ejecucion ahora vamos a hacer ahora un insert
//            Persona nuevaPersona = new Persona();
//            nuevaPersona.setNombre("Batman");
//            nuevaPersona.setApellido("bruno");
//            personaJDBC.insertar(nuevaPersona);

            //ELIMINANDO PERSONAS
                personaJDBC.eliminar(new Persona(9));

                
            List<Persona> personas = personaJDBC.seleccionar();
            for (Persona persona : personas) {
                System.out.println("persona =" + persona);
            }

            conexion.commit();
            System.out.println("se ha echo commit de la transaccion");

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            //al hacer rollback significa que si hay un error al pasarle los datos no se van a guardar en la bdd
            System.out.println("Entramos al rollBack");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }

        }

    }
}
