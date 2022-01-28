package test;

import datos.UsuarioDAO;
import domain.Usuario;
import java.util.List;

public class TestManejoUsuarios {

    public static void main(String[] args) {
        UsuarioDAO usuarioDao = new UsuarioDAO();

        //insertando un nuevo usuario
      //  Usuario insertandoUsuario = new Usuario("Perrito_Negro","64654");
      //  usuarioDao.insertando(insertandoUsuario);
      /*---------------------------------------------------------*/
      Usuario modificarUsuario=new Usuario(2, "Pedro", "666677777");
      usuarioDao.actualizar(modificarUsuario);
      
          /*---------------------------------------------------*/
          Usuario eliminarUsuario=new Usuario(3);
          usuarioDao.eliminar(eliminarUsuario);
      
        
        /*-------------------------------------------------*/
        //Desplegar toda la lista de usuariosde la bdd
        List<Usuario> usuarios = usuarioDao.listar();
        usuarios.forEach((usuario) -> {
            System.out.println("usurios = " + usuario);
        });
        
    

    }
}
