package AirlineManegementSystemPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasajerosControl {
    
    public static void AgregarNuevoPasajero(Database database, Scanner s) throws SQLException, IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
         System.out.println("Introduzca Primer Nombre: ");
         String PrimerNombre = s.next();
         System.out.println("Introduzca Ultimo Nombre: ");
         String UltimoNombre = s.next();
         System.out.println("Introduzca Numero de Telefono: ");
         String Telefono = s.next();
         System.out.println("Introduzca Correo Electronico: ");
         String Correo = s.next();
        
         Pasajero p = new Pasajero();
         p.setPrimerNombre(PrimerNombre);
         p.setUltimoNombre(UltimoNombre);
         p.setTelefono(Telefono);
         p.setCorreo(Correo);
        
         ArrayList<Pasajero> listaPasajeros  = ConseguirTodosLosPasajeros(database);
         int id;
         if (listaPasajeros .size()!=0) {
            id = listaPasajeros.get(listaPasajeros.size()-1).getId()+1;
         } else {
             id = 0;
         }
         p.setId(id);
        
         String insert = "INSERT INTO `pasajeros`(`id`, `PrimerNombre`, `UltimoNombre`,"
                + "`Telefono`, `Correo`) VALUES ('"+p.getId()+"','"+p.getPrimerNombre()+"','"
                +p.getUltimoNombre()+"','"+p.getTelefono()+"','"+p.getCorreo()+"');";
        database.getStatement().execute(insert);
        
         System.out.println();
         System.out.println("-----------------------------");
         System.out.println("!Pasajero Agregado con Exito!");
         System.out.println("-----------------------------");
         System.out.println();
        
         System.out.println("Presiona ENTER para continuar...");
         br.readLine(); 
    }
    
    public static void EditarPasajeros(Database database, Scanner s) throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id;

            do {
                System.out.println("Introduzca el ID del Pasajero: \n(-1 Para Buscar Pasajeros por el Nombre)");
                id = s.nextInt();
                Pasajero listaPasajeros;

                if (id == -1) {
                    listaPasajeros =  ConseguirPasajerosPorNombre(database, s);
                    if (listaPasajeros == null) {
                        System.out.println("No se encontraron pasajeros con ese nombre. Intente de nuevo.");
                        continue; 
                    }
                } else {
                    String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Telefono`, `Correo` FROM `pasajeros` WHERE `id` = "+id+";";
                    ResultSet rs = database.getStatement().executeQuery(get);

                    if (!rs.next()) {
                        System.out.println();
                        System.out.println("El ID del pasajero no existe. Intente de nuevo.");
                        System.out.println();
                        continue; 
                    }

            Pasajero p = new Pasajero();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setPrimerNombre(rs.getString("PrimerNombre"));
            p.setUltimoNombre(rs.getString("UltimoNombre"));
            p.setTelefono(rs.getString("Telefono"));
            p.setCorreo(rs.getString("Correo"));
            listaPasajeros = p;
        }
        
        System.out.println();
        listaPasajeros.print();
        
        System.out.println("Introduzca Primer Nombre: \n(-1 para mantener el valor actual)");
        String PrimerNombre = s.next();
        if (PrimerNombre.equals("-1")) PrimerNombre = listaPasajeros.getPrimerNombre();
        
        System.out.println("Introduzca Ultimo Nombre: \n(-1 para mantener el valor actual)");
        String UltimoNombre = s.next();
        if (UltimoNombre.equals("-1")) UltimoNombre = listaPasajeros.getUltimoNombre();
        
        System.out.println("Introduzca Telefono: \n(-1 para mantener el valor actual)");
        String Telefono = s.next();
        if (Telefono.equals("-1")) Telefono = listaPasajeros.getTelefono();
        
        System.out.println("Introduzca Correo: \n(-1 para mantener el valor actual)");
        String Correo = s.next();
        if (Correo.equals("-1")) Correo = listaPasajeros.getCorreo();
        
        listaPasajeros.setPrimerNombre(PrimerNombre);
        listaPasajeros.setUltimoNombre(UltimoNombre);
        listaPasajeros.setTelefono(Telefono);
        listaPasajeros.setCorreo(Correo);
        
        String update = "UPDATE `pasajeros` SET `id`='"+listaPasajeros.getId()+"',`PrimerNombre`='"
                    +listaPasajeros.getPrimerNombre()+"',`UltimoNombre`='"+listaPasajeros.getUltimoNombre()+
                    "',`Telefono`='"+listaPasajeros.getTelefono()+"',`Correo`='"+listaPasajeros.getCorreo()+
                    "' WHERE `id` = '"+listaPasajeros.getId()+"';";
        database.getStatement().execute(update);
        
        System.out.println();
        System.out.println("----------------------------");
        System.out.println("!Pasajero Editado con Exito!");
        System.out.println("----------------------------");
        System.out.println();
        
        System.out.println("Presiona ENTER para continuar...");
         br.readLine(); 
         
         break;
         
         } while (id != -1);
    }
    
    public static void EncontrarPasajerosPorNombre(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca Primer Nombre: ");
        String PrimerNombre = s.next();
        System.out.println("Introduzca Ultimo Nombre: ");
        String UltimoNombre = s.next();
        String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Telefono`,"
                + "`Correo` FROM `pasajeros` WHERE `PrimerNombre` = \""+PrimerNombre+"\";";
        ResultSet rs = database.getStatement().executeQuery(get);
        Pasajero pasajeros = new Pasajero();
        while (rs.next()) {
            Pasajero p = new Pasajero();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setPrimerNombre(rs.getString("PrimerNombre"));
            p.setUltimoNombre(rs.getString("UltimoNombre"));
            p.setTelefono(rs.getString("Telefono"));
            p.setCorreo(rs.getString("Correo"));
            
            if (p.getUltimoNombre().equals(UltimoNombre)) pasajeros = p; break;
        }
        System.out.println();
        pasajeros.print();
    }
    
    public static Pasajero ConseguirPasajerosPorNombre(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca Primer Nombre: ");
        String PrimerNombre = s.next();
        
        System.out.println("Introduzca Ultimo Nombre: ");
        String UltimoNombre = s.next();
        
        String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Telefono`,"
                + "`Correo` FROM `pasajeros` WHERE `PrimerNombre` = \"" + PrimerNombre + "\";";
        ResultSet rs = database.getStatement().executeQuery(get);
        
        Pasajero pasajeros = null;
        
        while (rs.next()) {
            Pasajero p = new Pasajero();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setPrimerNombre(rs.getString("PrimerNombre"));
            p.setUltimoNombre(rs.getString("UltimoNombre"));
            p.setTelefono(rs.getString("Telefono"));
            p.setCorreo(rs.getString("Correo"));
            
           if (p.getUltimoNombre().equals(UltimoNombre)) {
            pasajeros = p;
            break;
            }
        }
        return pasajeros;
    }
    
    public static void MostrarTodosLosPasajeros(Database database) throws SQLException, IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                ArrayList<Pasajero> listaPasajeros  = ConseguirTodosLosPasajeros(database);
                System.out.println("\n-----------------------------------------------");
                for (Pasajero p : listaPasajeros) {
                    p.print();
                }
                System.out.println("-----------------------------------------------\n");
                
                System.out.println("Presiona ENTER para continuar...");
                br.readLine(); 
    }
    
    public static void EliminarPasajeros(Database database, Scanner s) throws SQLException, IOException {
        
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int id;

    do {
        System.out.println("Introduzca el ID del Pasajero: \n(-1 Para Eliminar Pasajeros por el Nombre)");
        id = s.nextInt();
        Pasajero listaPasajeros;

        if (id == -1) {
            listaPasajeros = ConseguirPasajerosPorNombre(database, s);
            if (listaPasajeros == null) {
                System.out.println("No se encontraron pasajeros con ese nombre. Intente de nuevo.");
                continue; 
            }
        } else {
            String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Telefono`, `Correo` FROM `pasajeros` WHERE `id` = "+id+";";
            ResultSet rs = database.getStatement().executeQuery(get);

            if (!rs.next()) {
                System.out.println("El ID del pasajero no existe. Intente de nuevo.");
                continue; 
            }

            Pasajero p = new Pasajero();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setPrimerNombre(rs.getString("PrimerNombre"));
            p.setUltimoNombre(rs.getString("UltimoNombre"));
            p.setTelefono(rs.getString("Telefono"));
            p.setCorreo(rs.getString("Correo"));
            listaPasajeros = p;
        }
        
        String delete = "DELETE FROM `pasajeros` WHERE `id`="+listaPasajeros.getId()+ ";";
        database.getStatement().execute(delete);
        
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("!Pasajero Eliminado con Exito!");
        System.out.println("------------------------------");
        System.out.println();
        
        System.out.println("Presiona ENTER para continuar...");
        br.readLine(); 

        break;

    } while (id != -1);
}

    
    public static ArrayList<Pasajero> ConseguirTodosLosPasajeros(Database database) throws SQLException{
        String get = "SELECT * FROM `pasajeros`;";
        ResultSet rs = database.getStatement().executeQuery(get);
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        
        while (rs.next()) {
            Pasajero p = new Pasajero();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setPrimerNombre(rs.getString("PrimerNombre"));
            p.setUltimoNombre(rs.getString("UltimoNombre"));
            p.setTelefono(rs.getString("Telefono"));
            p.setCorreo(rs.getString("Correo"));
            pasajeros.add(p);
        }
        return pasajeros;
    }
    
    public static Pasajero ConseguirPasajerosPorID(Database database, int id) throws SQLException {
        String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Telefono`,"
                + "`Correo` FROM `pasajeros` WHERE `id` = "+id+";";
        ResultSet rs = database.getStatement().executeQuery(get);
        rs.next();
        Pasajero p = new Pasajero();
        p.setId(Integer.parseInt(rs.getString("id")));
        p.setPrimerNombre(rs.getString("PrimerNombre"));
        p.setUltimoNombre(rs.getString("UltimoNombre"));
        p.setTelefono(rs.getString("Telefono"));
        p.setCorreo(rs.getString("Correo"));
        return p;
    }
    
}
