package test;

import java.sql.*;
import java.sql.DriverManager;


public class TestMysqlJDBC {
    public static void main(String[] args) {
        /*linea para conectar ala base*/
        String url="jdbc:mysql://localhost:3306/test?useSSl=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
          /*  Class.forName("com.mysql.cj.jdbc.Drive");//linea no requerida pero suele estar para algunas aplicaciones web*/
            /*con esta clase hay que envolverlas trychach usa un interfase Connection y la implementa con DriverManager*/
            Connection conexion=DriverManager.getConnection(url, "root", "root");
            /*este objeto no va permitir crear sentencias en nuestra base de datos*/
            Statement instruccion=conexion.createStatement();
            /*instruccion a ejecutar*/
            String sql="SELECT id_persona, nombre, apellido, email, telefono FROM persona";
            /*ejecutar la instruccion sql*/
            ResultSet resultado=instruccion.executeQuery(sql);
            while (resultado.next()) {  /*next se  ejecuta siempre que alla un elemento a iterar*/              
                System.out.print("Id persona: " + resultado.getInt("id_persona")+" ");
                System.out.print(" nombre:"+resultado.getString("nombre"));
                System.out.println(" apellido: "+resultado.getString("apellido"));
                System.out.println("");
            }
            resultado.close();
            instruccion.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
