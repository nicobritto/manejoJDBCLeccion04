package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;


public class TestManejoPersonas {
    
    public static void main(String[] args) {
        PersonaDAO personaDao=new PersonaDAO();
        /*s*/
        //INSERTANDO UN NUEVO OBJETO DE TIPO PERSONA
       // Persona personaNueva=new Persona("Nico","Lapropia","LArePropia@mail.com","466555454");
       // personaDao.insertar(personaNueva);
        
       //modificando una persona existente
       Persona modifiPersona=new Persona(6, "Computadora", "NicoBritto", "Lapc@m.com", "15222884455");
        personaDao.actualizar(modifiPersona);
        
        //Eliminando una persona de la bdd
        Persona personaEliminar=new Persona(4);
        personaDao.eliminar(personaEliminar);
            
        //Desplegar toda la lista de personas de la bdd
        List<Persona> personas= personaDao.seleccionar();
         /*funcion Lambdas meterle a Lambdas me dijo Marcelito*/
        personas.forEach((persona) -> {
            System.out.println("persona : "+ persona);
        });
        
        /* for each comun el de arriba es Lambdas
        for (Persona persona : personas) {
            System.out.println("persona : "+ persona);
        }
        */
        
    }
}
