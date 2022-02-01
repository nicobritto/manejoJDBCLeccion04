package test;

import datos.Conexion;
import datos.UsuarioJDBC;
import domain.Persona;
import domain.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestManejoUsuarios {

    public static void main(String[] args) {
        //la variable conexion debe estar declarada fuera del try para que podamos utilizarla al final(catch)
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            UsuarioJDBC usuarioDao = new UsuarioJDBC(conexion);

            Usuario modificandoUsuario = new Usuario();
            modificandoUsuario.setIdUsuario(1);
            modificandoUsuario.setUsuario("LapropiaYes");

            usuarioDao.actualizar(modificandoUsuario);
            
            //ELIMINANDO UN USUARIO
            usuarioDao.eliminar(new Usuario(7));
            
            List<Usuario> usuarios = usuarioDao.listar();
            usuarios.forEach((usuario) -> {
                System.out.println("Usuario =" +usuario);
            });

//            Usuario nuevoUsuario = new Usuario();
//            nuevoUsuario.setUsuario("mibuhito");
//            nuevoUsuario.setPassword("0303456");
//            usuarioDao.insertando(nuevoUsuario);
            
            
          

            //llamamos nuestro objeto conexxion y al metodo commit para que se realisen todos los cambios
            //por eso antes pusimos   conexion.setAutoCommit(false) para que no se haaga de forma automatica
            conexion.commit();
            System.out.println("se echo commit de la transaccion");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("entramos al rollBack");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }
}
