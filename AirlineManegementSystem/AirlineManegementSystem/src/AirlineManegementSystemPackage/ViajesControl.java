package AirlineManegementSystemPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ViajesControl {
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd::HH:mm:ss");
    
    public static void AgregarNuevoViaje(Database database, Scanner s) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd::HH:mm:ss");
        
       System.out.println("Introduzca ID  del Avion: \n(-1 Para Mostrar todos los Aviones)");
       int avionID = s.nextInt();
       if (avionID == -1) {
            AvionesControl.MostrarTodosAviones(database);
            System.out.println("Introduzca ID  del Avion: ");
            avionID = s.nextInt();
       }
       
       Avion avion = AvionesControl.ConseguirAvionPorID(database, avionID);
        
       System.out.println("Introduzca ID del Aeropuerto de Origen: \n(-1 Para Mostrar todos los Aeropuertos)");
       int OrigenID = s.nextInt();
       if (OrigenID == -1) {
            AeropuertosControl.MostrarTodosLosAeropuertos(database);
            System.out.println("Introduzca ID del Aeropuerto de Origen: ");
            OrigenID = s.nextInt();
       }
       Aeropuerto Origen = AeropuertosControl.ConseguirAeropuerto(database, avionID);
         
       System.out.println("Introduzca ID del Aeropuerto de Destino: \n(-1 Para Mostrar todos los Aeropuertos)");
       int DestinoID = s.nextInt();
       if (DestinoID == -1) {
            AeropuertosControl.MostrarTodosLosAeropuertos(database);
            System.out.println("Introduzca ID del Aeropuerto de Destino: ");
            DestinoID = s.nextInt();
       }
       Aeropuerto Destino = AeropuertosControl.ConseguirAeropuerto(database, DestinoID);
       
       System.out.println("Introduzca Hora de Salida (yyyy-MM-dd::HH:mm:ss): ");
       String SHora = s.next();
       LocalDateTime HoraSalida = LocalDateTime.parse(SHora, formatter);
       
       System.out.println("Introduzca Hora de Llegada (yyyy-MM-dd::HH:mm:ss): ");
       String LHora = s.next();
       LocalDateTime HoraLlegada = LocalDateTime.parse(LHora, formatter);
       
       Viaje viaje = new Viaje();
       viaje.setAvion(avion);
       viaje.setOrigenAeropuerto(Origen);
       viaje.setDestinoAeropuerto(Destino);
       viaje.setHoraSalida(HoraSalida);
       viaje.setHoraLlegada(HoraLlegada);
       
       ArrayList<Viaje> viajes = ConseguirTodosLosViajes(database);
       int id = 0;
       if (viajes.size()!=0) id = viajes.size();
       
       viaje.setID(id);
       
       String insert = "INSERT INTO `viajes`(`id`, `avion`, `Origen`, `Destino`, `HoraSalida`, `HoraLlegada`,"
               + "`Atrasado`, `ReservadoEconomia`, `ReservadoEmpresarial`, `Cosas`, `pasajeros`) VALUES "
               + "('"+viaje.getID()+"','"+avionID+"','"+OrigenID+ "','"+DestinoID+"','"+SHora+"','"+LHora+"','false','0'"
               + ",'0','<%%/>','<%%/>');";
       database.getStatement().execute(insert);
       System.out.println();
       System.out.println("--------------------------");
       System.out.println("!Viaje Agregado con Exito!");
       System.out.println("--------------------------");
       System.out.println();
    }
    
    public static ArrayList<Viaje> ConseguirTodosLosViajes(Database database) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd::HH:mm:ss");
        
        ArrayList<Viaje> viajes = new ArrayList<>();
        String select = "SELECT * FROM `viajes` ;";
        ResultSet rs = database.getStatement().executeQuery(select);
        
        ArrayList<Integer> IDs = new ArrayList<>();
        ArrayList<Integer> avionIDs = new ArrayList<>();
        ArrayList<Integer> OrigenIDs = new ArrayList<>();
        ArrayList<Integer> DestinoIDs = new ArrayList<>();
        ArrayList<String> SalHoras = new ArrayList<>();
        ArrayList<String> LlegHoras = new ArrayList<>();
        ArrayList<String> atras = new ArrayList<>();
        ArrayList<Integer> ReservadoEconomiaAsientos = new ArrayList<>();
        ArrayList<Integer> ReservadoEmpresarialAsientos = new ArrayList<>();
        ArrayList<String> sts = new ArrayList<>();
        ArrayList<String> pass = new ArrayList<>();
        
        while (rs.next()) {
           IDs.add(rs.getInt("id"));
           avionIDs.add(rs.getInt("avion"));
           OrigenIDs.add(rs.getInt("Origen"));
           DestinoIDs.add(rs.getInt("Destino"));
           SalHoras.add(rs.getString("HoraSalida"));
           LlegHoras.add(rs.getString("HoraLlegada"));
           atras.add(rs.getString("Atrasado"));
           ReservadoEconomiaAsientos.add(rs.getInt("ReservadoEconomia"));
           ReservadoEmpresarialAsientos.add(rs.getInt("ReservadoEmpresarial"));
           sts.add(rs.getString("Cosas"));
           pass.add(rs.getString("pasajeros"));
        }
        
        for (int i=0; i<IDs.size(); i++) {
           Viaje viaje = new Viaje();
           viaje.setID(IDs.get(i));
           int avionID = avionIDs.get(i);
           int OrigenID =  OrigenIDs.get(i);
           int DestinoID = DestinoIDs.get(i);
           String SalHora = SalHoras.get(i);
           String LlegHora = LlegHoras.get(i);
           String atra = atras.get(i);
           boolean Atrasado = Boolean.parseBoolean(atra);
           viaje.setReservadoEconomia(ReservadoEconomiaAsientos.get(i));
           viaje.setReservadoEmpresarial(ReservadoEmpresarialAsientos.get(i));
           String st = sts.get(i);
           String pas = pass.get(i);
           
           Avion avion = AvionesControl.ConseguirAvionPorID(database, avionID);
           viaje.setAvion(avion);
           viaje.setOrigenAeropuerto(AeropuertosControl.ConseguirAeropuerto(database, OrigenID));
           viaje.setDestinoAeropuerto(AeropuertosControl.ConseguirAeropuerto(database, DestinoID));
           LocalDateTime HoraSalida = LocalDateTime.parse(SalHora, formatter);
           viaje.setHoraSalida(HoraSalida);
           LocalDateTime HoraLlegada = LocalDateTime.parse(LlegHora, formatter);
           viaje.setHoraLlegada(HoraLlegada);
           
           if (Atrasado) viaje.Atrasado();
           
           String[] CosasID = st.split("<%%/>");
           Empleado[] Cosas = new Empleado[10];
           for (int j=0; j<CosasID.length; j++) {
               int id = Integer.parseInt(CosasID[j]);
               Cosas[j] = EmpleadosControl.ConseguirEmpleadosPorID(database, id);
           }
           viaje.setCosas(Cosas);
           
           String[] pasajerosID = pas.split("<%%/>");
           int CapacidadTotal = avion.getCapacidadEconomica()+avion.getCapacidadEconomica();
           Pasajero[] pasajeros = new Pasajero[CapacidadTotal];
           for (int j=0; j<pasajerosID.length; j++) {
               int id = Integer.parseInt(pasajerosID[j]);
               pasajeros[j] = PasajerosControl.ConseguirPasajerosPorID(database, id);
           }
           viaje.setPasajeros(pasajeros);
           
           viajes.add(viaje);
        }
        
        return viajes;
    }
    
    public static void MostrarTodosLosViajes(Database database) throws SQLException {
        ArrayList<Viaje> viajes = ConseguirTodosLosViajes(database);
        System.out.println();
        for (Viaje f : viajes) {
            f.print();
        }
    }
    
    public static void AtrasarViaje(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca ID del Viaje: \n(-1 Para Mostrar todos los Viajes)");
        int id = s.nextInt();
        if (id == -1) {
            MostrarTodosLosViajes(database);
            System.out.println("Introduzca ID del Viaje: ");
            id = s.nextInt();
        }
        
        String update = "UPDATE `viajes` SET `Atrasado`='true' WHERE `id` = "+id+";";
        database.getStatement().execute(update);
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("!Viaje Atrasado con Exito!");
        System.out.println("--------------------------");
        System.out.println();
    }
    
    public static void ReservarViaje(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca ID del Viaje: \n(-1 Para Mostrar todos los Viajes)");
        int id = s.nextInt();
        if (id == -1) {
            MostrarTodosLosViajes(database);
            System.out.println("Introduzca ID del Viaje: ");
            id = s.nextInt();
        }
        
        Viaje viaje = ConseguirViaje(database, id);
        
        Pasajero pasajero;
        System.out.println("Introduzca ID del Pasajero: \n(-1 Para Mostrar Pasajero mediante el Nombre)");
        int pasajeroID = s.nextInt();
        if (pasajeroID == -1) {
            pasajero = PasajerosControl.ConseguirPasajerosPorNombre(database, s);
        } else {
            pasajero = PasajerosControl.ConseguirPasajerosPorID(database, pasajeroID);
        }
        
        System.out.println("1. Asiento Economico");
        System.out.println("2. Asiento Empresaial");
        int n = s.nextInt();
        
        System.out.println("Introduzca Numero de Asiento: ");
        int num = s.nextInt();
        
        if (n == 1) {
            viaje.setReservadoEconomia(viaje.getReservadoEconomia()+num);
        } else {
            viaje.setReservadoEmpresarial(viaje.getReservadoEmpresarial()+num);
        }
        
        Pasajero[] pasajeros = viaje.getPasajeros();
        for (int i=0; i<pasajeros.length; i++) {
            if (pasajeros[i] == null) {
                pasajeros[i] = pasajero;
                break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (Pasajero p : viaje.getPasajeros()) {
            if (p!=null) sb.append(p.getId()).append("<%%/>");
        }
        
        String update = "UPDATE `viajes` SET `ReservadoEconomia`='"+viaje.getReservadoEconomia()+"',"
                + "`ReservadoEmpresarial`='"+viaje.getReservadoEmpresarial()+"',`pasajeros`='"+sb.toString()+"' WHERE `id` = "+viaje.getID()+";";
        database.getStatement().execute(update);
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("!Reservacion con Exito!");
        System.out.println("-----------------------");
        System.out.println();
    }
    
    public static Viaje ConseguirViaje(Database database, int id) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd::HH:mm:ss");
        
        Viaje viaje = new Viaje();
        String select = "SELECT `id`, `avion`, `Origen`, `Destino`, `HoraSalida`, `HoraLlegada`, `Atrasado`,"
                + " `ReservadoEconomia`, `ReservadoEmpresarial`, `Cosas`, `pasajeros` FROM `viajes`"
                + " WHERE `id` = "+id+";";
        ResultSet rs = database.getStatement().executeQuery(select);
        rs.next();
        int ID = rs.getInt("id");
        int  avionIDs = rs.getInt("avion");
        int OrigenIDs = rs.getInt("Origen");
        int DestinoIDs = rs.getInt("Destino");
        String SalHoras = rs.getString("HoraSalida");
        String LlegHoras = rs.getString("HoraLlegada");
        String atras = rs.getString("Atrasado");
        int ReservadoEconomia = rs.getInt("ReservadoEconomia");
        int ReservadoEmpresarial = rs.getInt("ReservadoEmpresarial");
        String st = rs.getString("Cosas");
        String pas = rs.getString("pasajeros");
        boolean Atrasado = Boolean.parseBoolean(atras);
        
         viaje.setID(ID);
         Avion avion = AvionesControl.ConseguirAvionPorID(database, avionIDs);
         viaje.setAvion(avion);
         viaje.setOrigenAeropuerto(AeropuertosControl.ConseguirAeropuerto(database, OrigenIDs));
         viaje.setDestinoAeropuerto(AeropuertosControl.ConseguirAeropuerto(database, DestinoIDs));
         LocalDateTime HoraSalida = LocalDateTime.parse(SalHoras, formatter);
         viaje.setHoraSalida(HoraSalida);
         LocalDateTime HoraLlegada = LocalDateTime.parse(LlegHoras, formatter);
         viaje.setHoraLlegada(HoraLlegada);
         
         viaje.setReservadoEconomia(ReservadoEconomia);
         viaje.setReservadoEmpresarial(ReservadoEmpresarial);
           
           if (Atrasado) viaje.Atrasado();
           
           String[] CosasID = st.split("<%%/>");
           Empleado[] Cosas = new Empleado[10];
           for (int j=0; j<CosasID.length; j++) {
               int idst = Integer.parseInt(CosasID[j]);
               Cosas[j] = EmpleadosControl.ConseguirEmpleadosPorID(database, idst);
           }
           viaje.setCosas(Cosas);
           
           String[] pasajerosID = pas.split("<%%/>");
           int CapacidadTotal = avion.getCapacidadEconomica()+avion.getCapacidadEconomica();
           Pasajero[] pasajeros = new Pasajero[CapacidadTotal];
           for (int j=0; j<pasajerosID.length; j++) {
               int idpass = Integer.parseInt(pasajerosID[j]);
               pasajeros[j] = PasajerosControl.ConseguirPasajerosPorID(database, idpass);
           }
           viaje.setPasajeros(pasajeros);
        
        return viaje;
    }
    
    public static void ColocarObjetosDeViaje(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca ID del Viaje: \n(-1 Para Mostrar todos los Viajes)");
        int id = s.nextInt();
        if (id == -1) {
            MostrarTodosLosViajes(database);
            System.out.println("Introduzca ID del Viaje: ");
            id = s.nextInt();
        }
        
        Viaje viaje = ConseguirViaje(database, id);
        
        System.out.println("1. Mostrar Todos los Empleados");
        System.out.println("2. Continuar");
        int j = s.nextInt();
        if (j == 1) EmpleadosControl.MostrarTodosLosEmpleados(database);
        System.out.println("Introduzca IDs de los Empleados: ");
        Empleado[] empleados = new Empleado[10];
        for (int i=0; i<10; i++) {
            System.out.println("id " + (i+1)+ "/10");
            int ID = s.nextInt();
            empleados[i] = EmpleadosControl.ConseguirEmpleadosPorID(database, ID);
        }
        
        viaje.setCosas(empleados);
        
        StringBuilder bd = new StringBuilder();
        for (Empleado e : viaje.getCosas()) {
            if (e!=null) bd.append(e.getId()).append("<%%/>");
        }
        
        String update = "UPDATE `viajes` SET `Cosas`='"+bd.toString()
                +"' WHERE `id` = "+viaje.getID()+";";
        database.getStatement().execute(update);
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("!Objetos Actualizados con Exito!");
        System.out.println("--------------------------------");
        System.out.println();
        }
    
        public static void CancelarViaje(Database database, Scanner s) throws SQLException {
            System.out.println("Introduzca ID del Viaje: \n(-1 Para Mostrar todos los Viajes)");
            int id = s.nextInt();
            if (id == -1) {
                MostrarTodosLosViajes(database);
                System.out.println("Introduzca ID del Viaje: ");
                id = s.nextInt();
            }

            String delete = "DELETE FROM `viajes` WHERE `id` = "+id+";";
            database.getStatement().execute(delete);
            System.out.println();
            System.out.println("---------------------------");
            System.out.println("!Viaje Cancelado con Exito!");
            System.out.println("---------------------------");
            System.out.println();
        }
        
        public static void MostrarObjetosDeViaje(Database database, Scanner s) throws SQLException {
            System.out.println("Introduzca ID del Viaje: \n(-1 Para Mostrar todos los Viajes)");
            int id = s.nextInt();
            if (id == -1) {
            MostrarTodosLosViajes(database);
            System.out.println("Introduzca ID del Viaje: ");
            id = s.nextInt();
            }
            Viaje f = ConseguirViaje(database, id);
            
            System.out.println("\n-----------------------------------------------");
            for (Empleado e : f.getCosas()) {
                if (e!=null) {
                    System.out.println();
                    System.out.println("id: "+e.getId());
                    System.out.println("Primer Nombre: "+e.getPrimerNombre());
                    System.out.println("Ultimo Nombre: "+e.getUltimoNombre());
                    System.out.println("Correo: "+e.getCorreo());
                    System.out.println("Telefono: "+e.getTelefono());
                    System.out.println("Posicion: "+e.getPosicion());
                }
            }
        }
        
        public static void MostrarPasajerosDeViaje(Database database, Scanner s) throws SQLException {
            System.out.println("Introduzca ID del Viaje: \n(-1 Para Mostrar todos los Viajes)");
            int id = s.nextInt();
            if (id == -1) {
            MostrarTodosLosViajes(database);
            System.out.println("Introduzca ID del Viaje: ");
            id = s.nextInt();
            }
            Viaje f = ConseguirViaje(database, id);
            
            System.out.println("\n-----------------------------------------------");
             for (Pasajero p : f.getPasajeros()) {
                if (p!=null) {
                    System.out.println("id: "+p.getId()+"\t");
                    System.out.println("Primer Nombre: "+p.getPrimerNombre()+"\t");
                    System.out.println("Ultimo Nombre: "+p.getUltimoNombre()+"\t");
                    System.out.println("Correo: "+p.getCorreo()+"\t");
                    System.out.println("Telefono: "+p.getTelefono()+"\t");
                    System.out.println();
                }
            }
        }
        
    }