package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import domain.Persona;
import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_usuario, usuario, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(usuario,password) VALUES (? , ?)";
    private static final String SQL_ACTUALIZAR = "UPDATE  usuario SET usuario = ?, password= ? WHERE id_usuario = ? ";
    private static final String SQL_ELIMINAR = "DELETE FROM usuario WHERE id_usuario = ? ";

    public UsuarioDAO() {
    }

    public UsuarioDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Usuario> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;/*este objeto(PreparedStatement)no va permitir crear sentencias en nuestra base de datos*/
        ResultSet rs = null;/*ResultSet ejecutar la instruccion sql*/
        Usuario usuario = null;/*creamos un objeto de tipo usuario */
        List<Usuario> usuarios = new ArrayList<>();/*creamos una lista de usuarios parair guardando todos lo usuarios*/
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();/*realizando la conexion ala bdd*/
            stmt = conn.prepareStatement(SQL_SELECT);/*le pasamos la sentecia a ejecutar y abajo la ejecutamos*/
            rs = stmt.executeQuery();/*ejecutando la instruccion SQL*/

            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String uzuario = rs.getString("usuario");
                String contrasenia = rs.getString("password");

                usuario = new Usuario(idUsuario, uzuario, contrasenia);/*sacamos los datos de la bdd y lo convertimos en un objeto*/
                usuarios.add(usuario);/*y agregamos el usuario ala lista de usuarios*/
            }

      } finally {
            /*con el bloque finaly se ejecuta si o si y cerramos los objetos que esten abiertos*/
            // cerrar los objetos en el orden inverso de que se fueron abriendo

            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return usuarios;
    }

    /*----------------------------------------------------------------*/
    public int insertando(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();/*realizando la conexion ala bdd*/
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());

            registros = stmt.executeUpdate();

       } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;

    }

    /*---------------------------------------------------*/
    public int eliminar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();/*realizando la conexion ala bdd*/
            stmt = conn.prepareStatement(SQL_ELIMINAR);
            stmt.setInt(1, usuario.getIdUsuario());

            registros = stmt.executeUpdate();

       } finally {
            Conexion.close(stmt);
          
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;
    }

    /*---------------------------------------------------*/
    public int actualizar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();/*realizando la conexion ala bdd*/
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());

            registros = stmt.executeUpdate();

      } finally {
          Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;
    }
    /*---------------------------------------------------*/

}
