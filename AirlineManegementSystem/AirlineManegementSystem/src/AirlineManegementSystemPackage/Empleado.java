package AirlineManegementSystemPackage;

public class Empleado {
    
    private int id;
    private String PrimerNombre;
    private String UltimoNombre;
    private String Correo;
    private String Telefono;
    private double Salario;
    private String Posicion;
    
    public Empleado() {}
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setPrimerNombre(String PrimerNombre) {
        this.PrimerNombre = PrimerNombre;
    }
    
    public String getPrimerNombre() {
        return PrimerNombre;
    }
    
    public void setUltimoNombre(String UltimoNombre) {
        this.UltimoNombre = UltimoNombre;
    }
    
    public String getUltimoNombre() {
        return UltimoNombre;
    }
    
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
    public String getCorreo() {
        return Correo;
    }
    
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    public String getTelefono() {
        return Telefono;
    }
    
    public void setSalario(double Salario) {
        this.Salario = Salario;
    }
    
    public double getSalario() {
        return Salario;
    }
    
     public void setPosicion(String Posicion) {
        this.Posicion = Posicion;
    }
    
    public String getPosicion() {
        return Posicion;
    }
    
    public void print() {
       System.out.println("id: "+id);
       System.out.println("Nombre: "+PrimerNombre+" "+UltimoNombre);
       System.out.println("Correo: "+Correo);
       System.out.println("Telefono: "+Telefono);
       System.out.println("Salario: "+Salario);
       System.out.println("Posicion: "+Posicion);
       System.out.println();
    }
    
}
