package AirlineManegementSystemPackage;

public class Aeropuerto {
    
    private int id;
    private String Ciudad;
    
    public Aeropuerto() {}
    
    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
    
    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }
    
    public String getCiudad() {
        return Ciudad;
    }
    
    public void print() {
        System.out.println(id+"\t"+Ciudad);
    }
    
}
