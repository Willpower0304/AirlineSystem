package AirlineManegementSystemPackage;

public class Pasajero {
    
    private int id;
    private String PrimerNombre;
    private String UltimoNombre;
    private String Telefono;
    private String Correo;
    
    public Pasajero() {}
    
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
    
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    public String getTelefono() {
        return Telefono;
    }
    
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
    public String getCorreo() {
        return Correo;
    }
    
    public void print() {
       System.out.println("id: "+getId());
       System.out.println("Nombre: "+getPrimerNombre()+" "+getUltimoNombre());
       System.out.println("Telefono: "+getTelefono());
       System.out.println("Correo: "+getCorreo());
       System.out.println();
    }
    
}
