package AirlineManegementSystemPackage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Scanner;

public class AeropuertosControl {
    
    public static void AgregarNuevoAeropuerto(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca Ciudad: ");
        String Ciudad = s.next();
        int id;
        
         ArrayList<Aeropuerto> aeropuertos = ConseguirTodosLosAeropuertos(database);
         if (aeropuertos.size()!=0) {
             id = aeropuertos.get(aeropuertos.size()-1).getID()+1;
         } else {
             id = 0;
         }
         
         Aeropuerto aeropuerto = new Aeropuerto();
         aeropuerto.setID(id);
         aeropuerto.setCiudad(Ciudad);
         
         String insert = "INSERT INTO `aeropuertos`(`id`, `Ciudad`) VALUES ('"+aeropuerto.getID()+"','"
                 +aeropuerto.getCiudad()+"');";
         database.getStatement().execute(insert);
         System.out.println();
         System.out.println("-------------------------------");
         System.out.println("!Aeropuerto Agregado con Exito!");
         System.out.println("-------------------------------");
         System.out.println();
    }
    
    public static ArrayList<Aeropuerto> ConseguirTodosLosAeropuertos(Database database) throws SQLException {
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();
        String select = "SELECT * FROM `aeropuertos` ;";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Aeropuerto a = new Aeropuerto();
            a.setID(rs.getInt("id"));
            a.setCiudad(rs.getString("Ciudad"));
            aeropuertos.add(a);
        }
        return aeropuertos;
    }
    
    public static void MostrarTodosLosAeropuertos(Database database) throws SQLException {
        System.out.println("\n-----------------------------------------------");
        System.out.println("id\tCiudad");
        for (Aeropuerto a : ConseguirTodosLosAeropuertos(database)) {
            a.print();
        }
        System.out.println("-----------------------------------------------\n");
    }
    
    public static void EditarAeropuertos(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca ID  del Aeropuerto: \n(-1 Para Mostrar todos los Aeropuertos)");
        int id = s.nextInt();
        if (id == -1) {
            MostrarTodosLosAeropuertos(database);
            System.out.println("Introduzca ID del Aeropuerto: ");
            id = s.nextInt();
        }
        Aeropuerto aeropuerto = ConseguirAeropuerto(database, id);
        System.out.println("Introduzca Ciudad: ");
        String Ciudad = s.next();
        aeropuerto.setCiudad(Ciudad);
        String update = "UPDATE `aeropuertos` SET `id`='"+aeropuerto.getID()+
                "',`Ciudad`='"+aeropuerto.getCiudad()+"' WHERE `id` = "+aeropuerto.getID()+";";
        database.getStatement().execute(update);
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("!Aeropuerto Editado con Exito!");
        System.out.println("------------------------------");
        System.out.println();
    }
    
    public static Aeropuerto ConseguirAeropuerto(Database database, int id) throws SQLException {
        Aeropuerto aeropuerto = new Aeropuerto();
        String select = "SELECT `id`, `Ciudad` FROM `aeropuertos` WHERE `id` ="+id+" ;";
        ResultSet rs = database.getStatement().executeQuery(select);
        rs.next();
        aeropuerto.setID(rs.getInt("id"));
        aeropuerto.setCiudad(rs.getString("Ciudad"));
        return aeropuerto;
    }
    
    public static void EliminarAeropuerto(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca ID  del Aeropuerto: \n(-1 Para Mostrar todos los Aeropuertos)");
        int id = s.nextInt();
        if (id == -1) {
            MostrarTodosLosAeropuertos(database);
            System.out.println("Introduzca ID del Aeropuerto: ");
            id = s.nextInt();
        }
        
        String delete = "DELETE FROM `aeropuertos` WHERE `id` = "+id+" ;";
        database.getStatement().execute(delete);
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("!Aeropuerto Eliminado con Exito!");
        System.out.println("--------------------------------");
        System.out.println();
    }
    
}
