
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arturo Gonzalez
 */
public class ficheros {
    
    private String Nombre;
    private String Apellido;
    private String NoCuenta;
    
    public ficheros( String Nombre, String Apellido, String NoCuenta){
    
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.NoCuenta = NoCuenta;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the NoCuenta
     */
    public String getNoCuenta() {
        return NoCuenta;
    }

    /**
     * @param NoCuenta the NoCuenta to set
     */
    public void setNoCuenta(String NoCuenta) {
        this.NoCuenta = NoCuenta;
    }
  
   
}
