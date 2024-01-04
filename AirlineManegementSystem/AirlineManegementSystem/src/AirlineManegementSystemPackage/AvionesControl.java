package AirlineManegementSystemPackage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;

public class AvionesControl {
    
    public static void AgregarNuevoAvion(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca Capacidad de Asientos Economicos: ");
        int CapacidadEconomica = s.nextInt();
        System.out.println("Introduzca Capacidad de Asientos Empresariales: ");
        int CapacidadEmpresarial = s.nextInt();
        System.out.println("Introduzca Modelo del Avion: ");
        String Modelo = s.next();
        
        Avion avion = new Avion();
        avion.setCapacidadEconomica(CapacidadEconomica);
        avion.setCapacidadEmpresarial(CapacidadEmpresarial);
        avion.setModelo(Modelo);
        
        int id;
        ArrayList<Avion> nuevoAvion = ConseguirTodosLosAviones(database);
        if (nuevoAvion.size()!=0) {
            id = nuevoAvion.get(nuevoAvion.size()-1).getId()+1;
        } else {
            id = 0;
        }
        avion.setId(id);
        
        String insert = "INSERT INTO `aviones`(`id`, `CapacidadEconomica`, `CapacidadEmpresarial`,"
                + "`Modelo`) VALUES ('"+avion.getId()+"','"+avion.getCapacidadEconomica()
                +"','"+avion.getCapacidadEmpresarial()+"','"+avion.getModelo()+"');";
        
        database.getStatement().execute(insert);
        
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("!Avion Agregado con Exito!");
        System.out.println("--------------------------");
        System.out.println();
    }
    
    public static void MostrarTodosAviones(Database database) throws SQLException {
        System.out.println("\n-----------------------------------------------");
        for (Avion avion : ConseguirTodosLosAviones(database)) {
            avion.print();
        }
        System.out.println("-----------------------------------------------\n");
    }
    
    public static ArrayList<Avion> ConseguirTodosLosAviones(Database database) throws SQLException {
        ArrayList<Avion> nuevoAvion = new ArrayList<>();
        String get = "SELECT * FROM `aviones` ;";
        ResultSet rs = database.getStatement().executeQuery(get);
        while (rs.next()) {
            Avion a = new Avion();
            a.setId(rs.getInt("id"));
            a.setCapacidadEconomica(rs.getInt("CapacidadEconomica"));
            a.setCapacidadEmpresarial(rs.getInt("CapacidadEmpresarial"));
            a.setModelo(rs.getString("Modelo"));
            nuevoAvion.add(a);
        }
        return nuevoAvion;
    }
    
    public static void EditarAviones(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca ID  (int): \n(-1 Para Mostrar todos los Aviones)");
        int id = s.nextInt();
        if (id == -1) {
            MostrarTodosAviones(database);
            System.out.println("Introduzca ID (int): ");
            id = s.nextInt();
        }
        Avion p = ConseguirAvionPorID(database, id);
        
        System.out.println("Introduzca Capacidad Economica (int): \n(-1 para mantener el valor actual)");
        int CapacidadEconomica = s.nextInt();
        if (CapacidadEconomica == -1) CapacidadEconomica = p.getCapacidadEconomica();
        
        System.out.println("Introduzca Capacidad Empresarial (int): \n(-1 para mantener el valor actual)");
        int CapacidadEmpresarial = s.nextInt();
        if (CapacidadEmpresarial == -1) CapacidadEmpresarial = p.getCapacidadEmpresarial();
        
        System.out.println("Introduzca Modelo: \n(-1 para mantener el valor actual)");
        String Modelo = s.next();
        if (Modelo.equals("-1")) Modelo = p.getModelo();
        
        p.setCapacidadEconomica(CapacidadEconomica);
        p.setCapacidadEmpresarial(CapacidadEmpresarial);
        p.setModelo(Modelo);
        
        String update = "UPDATE `aviones` SET `id`='"+p.getId()+"',`CapacidadEconomica`='"
                +p.getCapacidadEconomica()+"',`CapacidadEmpresarial`='"+p.getCapacidadEmpresarial()
                +"',`Modelo`='"+p.getModelo()+"' WHERE `id` = "+p.getId()+" ;";
        
        database.getStatement().execute(update);
        
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("!Avion Editado con Exito!");
        System.out.println("-------------------------");
        System.out.println();
    }
    
    public static Avion ConseguirAvionPorID(Database database, int id) throws SQLException {
        Avion a = new Avion();
        String get = "SELECT `id`, `CapacidadEconomica`, `CapacidadEmpresarial`, `Modelo`"
                + "FROM `aviones` WHERE `id` = "+id+" ;";
        ResultSet rs = database.getStatement().executeQuery(get);
        rs.next();
        a.setId(rs.getInt("id"));
        a.setCapacidadEconomica(rs.getInt("CapacidadEconomica"));
        a.setCapacidadEmpresarial(rs.getInt("CapacidadEmpresarial"));
        a.setModelo(rs.getString("Modelo"));
        return a;
    }
    
    public static void EliminarAvion(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca ID  (int): \n(-1 Para Mostrar todos los Aviones)");
        int id = s.nextInt();
        if (id == -1) {
            MostrarTodosAviones(database);
            System.out.println("Introduzca ID (int): ");
            id = s.nextInt();
        }
        String delete = "DELETE FROM `aviones` WHERE `id` = " + id + " ;";
        database.getStatement().execute(delete);
        
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("!Avion Eliminado con Exito!");
        System.out.println("---------------------------");
        System.out.println();
    }
    
}
