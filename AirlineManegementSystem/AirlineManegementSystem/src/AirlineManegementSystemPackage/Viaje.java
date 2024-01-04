package AirlineManegementSystemPackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Viaje {
    
    private int id;
    private Avion avion;
    private Aeropuerto Origen;
    private Aeropuerto Destino;
    private LocalDateTime HoraSalida;
    private LocalDateTime HoraLlegada;
    private boolean Atrasado;
    private int ReservadoEconomia;
    private int ReservadoEmpresarial;
    private Empleado[] Cosas;
    private Pasajero[] pasajeros;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd::HH:mm:ss");

    
    public Viaje() {
        Atrasado = false;
        ReservadoEconomia = 0;
        ReservadoEmpresarial = 0;
        Cosas = new Empleado[10];
    }
    
    public int getID() {
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public Avion getAvion() {
        return avion;
    }
    
    public void setAvion(Avion avion) {
        this.avion = avion;
        int CapacidadTotal = avion.getCapacidadEmpresarial()+avion.getCapacidadEconomica();
        pasajeros = new Pasajero[CapacidadTotal];
    }
    
    public Aeropuerto getOrigenAeropuerto() {
        return Origen;
    }
    
    public void setOrigenAeropuerto(Aeropuerto Origen) {
        this.Origen = Origen;
    }
    
    public Aeropuerto getDestinoAeropuerto() {
        return Destino;
    }
    
    public void setDestinoAeropuerto(Aeropuerto Destino) {
        this.Destino = Destino;
    }
    
    public LocalDateTime getHoraSalida() {
        return HoraSalida;
    }
    
    public void setHoraSalida(LocalDateTime HoraSalida) {
        this.HoraSalida = HoraSalida;
    }
    
    public LocalDateTime getHoraLlegada() {
        return HoraLlegada;
    }
    
    public void setHoraLlegada(LocalDateTime HoraLlegada) {
        this.HoraLlegada = HoraLlegada;
    }
    
    public boolean isAtrasado() {
        return Atrasado;
    }
    
    public void Atrasado() {
        Atrasado = true;
    }
    
    public int getReservadoEconomia() {
        return ReservadoEconomia;
    }
    
    public void setReservadoEconomia(int ReservadoEconomia) {
        this.ReservadoEconomia = ReservadoEconomia;
    }
    
    public int getReservadoEmpresarial() {
        return ReservadoEmpresarial;
    }
    
    public void setReservadoEmpresarial(int ReservadoEmpresarial) {
        this.ReservadoEmpresarial = ReservadoEmpresarial;
    }
    
    public Empleado[] getCosas() {
        return Cosas;
    }
    
    public void setCosas(Empleado[] Cosas) {
        this.Cosas = Cosas;
    }
    
    public Pasajero[] getPasajeros() {
        return pasajeros;
    }
    
    public void setPasajeros(Pasajero[] pasajeros) {
        this.pasajeros = pasajeros;
    }
    
    public void print() {
        System.out.println("id: "+id+"\t");
        System.out.println("Avion: "+avion.getModelo());
        System.out.println("Origen: "+Origen.getCiudad());
        System.out.println("Destino: "+Destino.getCiudad());
        System.out.println("Hora de Salida: "+formatter.format(HoraSalida));
        System.out.println("Hora de Llegada: "+formatter.format(HoraLlegada));
        if (Atrasado) {
            System.out.println("Estado: Atrasado");
        } else {
            System.out.println("Estado: Estimado");
        }
        int disponibleE = avion.getCapacidadEconomica()-ReservadoEconomia;
        System.out.println("Asientos Economicos: "+disponibleE);
        int disponibleP = avion.getCapacidadEmpresarial()-ReservadoEmpresarial;
        System.out.println("Asientos Empresariales: "+disponibleP);
        System.out.println();
    }
    
}
