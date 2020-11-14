public class Alumno {
    
    private String nombre;
    private String apellido;
    private String noCuenta;
    
    public Alumno( String nombre, String apellido, String noCuenta){
        this.nombre = nombre;
        this.apellido = apellido;
        this.noCuenta = noCuenta;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the noCuenta
     */
    public String getNoCuenta() {
        return this.noCuenta;
    }

    /**
     * @param noCuenta the NoCuenta to set
     */
    public void setNoCuenta(String noCuenta) {
        this.noCuenta = noCuenta;
    }
    public void info(){
        System.out.println("Nombre: "+this.getNombre()+" Apellido: "+this.getApellido()+" Num: "+this.getNoCuenta());
    }  
}
