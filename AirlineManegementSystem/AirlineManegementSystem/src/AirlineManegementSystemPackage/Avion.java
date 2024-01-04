package AirlineManegementSystemPackage;

public class Avion {
    
    private int id;
    private int CapacidadEconomica;
    private int CapacidadEmpresarial;
    private String Modelo;
    
    public Avion() {}
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setCapacidadEconomica(int CapacidadEconomica) {
        this.CapacidadEconomica = CapacidadEconomica;
    }
    
    public int getCapacidadEconomica() {
        return CapacidadEconomica;
    }
    
     public void setCapacidadEmpresarial(int CapacidadEmpresarial) {
        this.CapacidadEmpresarial = CapacidadEmpresarial;
    }
    
    public int getCapacidadEmpresarial() {
        return CapacidadEmpresarial;
    }
    
    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }
    
    public String getModelo() {
        return Modelo;
    }
    
     public void print() {
       System.out.println("id: "+ id);
       System.out.println("Capacidad Economica: "+CapacidadEconomica);
       System.out.println("Capacidad Empresarial: "+ CapacidadEmpresarial);
       System.out.println("Modelo: "+Modelo);
       System.out.println();
    }
    
}
