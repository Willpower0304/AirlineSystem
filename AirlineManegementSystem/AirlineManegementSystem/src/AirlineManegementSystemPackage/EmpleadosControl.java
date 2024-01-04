package AirlineManegementSystemPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmpleadosControl {
    
    public static void AgregarNuevoEmpleado(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca Primer Nombre: ");
        String PrimerNombre = s.next();
        System.out.println("Introduzca Ultimo Nombre: ");
        String UltimoNombre = s.next();
        System.out.println("Introduzca Correo Electronico: ");
        String Correo = s.next();
        System.out.println("Introduzca Numero de Telefono: ");
        String Telefono = s.next();
        System.out.println("Introduzca Salario que Gana: ");
        double Salario = s.nextDouble();
        System.out.println("Introduzca Posicion: ");
        String Posicion = s.next();
        
        Empleado empleado = new Empleado();
        empleado.setPrimerNombre(PrimerNombre);
        empleado.setUltimoNombre(UltimoNombre);
        empleado.setCorreo(Correo);
        empleado.setTelefono(Telefono);
        empleado.setSalario(Salario);
        empleado.setPosicion(Posicion);
        
        ArrayList<Empleado> empleados = ConseguirTodosLosEmpleados(database);
        int id;
        if (empleados .size()!=0) {
            id = empleados.get(empleados.size()-1).getId()+1;
        } else {
            id = 0;
        }
        empleado.setId(id);
        
        String insert = "INSERT INTO `empleados`(`id`, `PrimerNombre`, `UltimoNombre`,"
                + "`Telefono`, `Correo`, `Salario`, `Posicion`) VALUES ('"+empleado.getId()+"','"+empleado.getPrimerNombre()+"','"
                +empleado.getUltimoNombre()+"','"+empleado.getTelefono()+"','"+empleado.getCorreo()+"', '"
                +empleado.getSalario()+"','"+empleado.getPosicion()+"');";
        database.getStatement().execute(insert);
        
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("!Empleado Agregado con Exito!");
        System.out.println("-----------------------------");
        System.out.println();
    }
    
    public static void EditarEmpleado(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca el ID del empleado: \n(-1 Para Mostrar todos los Pasajeros por el Nombre)");
        int id = s.nextInt();
        Empleado empleado;
        if (id == -1) {
            empleado =  ConseguirEmpleadoPorNombre(database,  s);
        } else {
             String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Correo`, `Telefono`, `Salario`,"
                     + "`Posicion` FROM `empleados` WHERE `id` = "+id+";";
             ResultSet rs = database.getStatement().executeQuery(get);
             Empleado p = new Empleado();
             rs.next();
             p.setId(Integer.parseInt(rs.getString("id")));
             p.setPrimerNombre(rs.getString("PrimerNombre"));
             p.setUltimoNombre(rs.getString("UltimoNombre"));
             p.setTelefono(rs.getString("Telefono"));
             p.setCorreo(rs.getString("Correo"));
             p.setSalario(rs.getDouble("Salario"));
             p.setPosicion(rs.getString("Posicion"));
             empleado = p;
        }
        
        System.out.println("Introduzca Primer Nombre: \n(-1 para mantener el valor actual)");
        String PrimerNombre = s.next();
        if (PrimerNombre.equals("-1")) PrimerNombre = empleado.getPrimerNombre();
        
        System.out.println("Introduzca Ultimo Nombre: \n(-1 para mantener el valor actual)");
        String UltimoNombre = s.next();
        if (UltimoNombre.equals("-1")) UltimoNombre = empleado.getUltimoNombre();
        
        System.out.println("Introduzca Telefono: \n(-1 para mantener el valor actual)");
        String Telefono = s.next();
        if (Telefono.equals("-1")) Telefono = empleado.getTelefono();
        
        System.out.println("Introduzca Correo: \n(-1 para mantener el valor actual)");
        String Correo = s.next();
        if (Correo.equals("-1")) Correo = empleado.getCorreo();
        
        System.out.println("Introduzca Salario: \n(-1 para mantener el valor actual)");
        double Salario = s.nextDouble();
        if (Salario == -1) Salario = empleado.getSalario();
        
        System.out.println("Introduzca Posicion: \n(-1 para mantener el valor actual)");
        String Posicion = s.next();
        if (Posicion.equals("-1")) Posicion = empleado.getPosicion();
        
        empleado.setPrimerNombre(PrimerNombre);
        empleado.setUltimoNombre(UltimoNombre);
        empleado.setTelefono(Telefono);
        empleado.setCorreo(Correo);
        empleado.setSalario(Salario);
        empleado.setPosicion(Posicion);
        
        String update = "UPDATE `empleados` SET `id`='"+empleado.getId()+"',`PrimerNombre`='"
        +empleado.getPrimerNombre()+"',`UltimoNombre`='"+empleado.getUltimoNombre()+"',`Correo`='"+empleado.getCorreo()+"',`Telefono`='"
        +empleado.getTelefono()+"',`Salario`='"+empleado.getSalario()+"',`Posicion`='"+empleado.getPosicion()+"' WHERE `id` = "+empleado.getId()+";";
        database.getStatement().execute(update);
        
        System.out.println();
        System.out.println("----------------------------");
        System.out.println("!Empleado Editado con Exito!");
        System.out.println("----------------------------");
        System.out.println();
    }
    
    public static Empleado ConseguirEmpleadoPorNombre(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca Primer Nombre: ");
        String PrimerNombre = s.next();
        
        System.out.println("Introduzca Ultimo Nombre: ");
        String UltimoNombre = s.next();
        
        String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Correo`, `Telefono`, `Salario`,"
                + "`Posicion` FROM `empleados` WHERE `PrimerNombre` = \""+PrimerNombre+"\";";
        ResultSet rs = database.getStatement().executeQuery(get);
        Empleado empleado = new Empleado();
        while (rs.next()) {
            //
            Empleado p = new Empleado();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setPrimerNombre(rs.getString("PrimerNombre"));
            p.setUltimoNombre(rs.getString("UltimoNombre"));
            p.setTelefono(rs.getString("Telefono"));
            p.setCorreo(rs.getString("Correo"));
            p.setSalario(rs.getDouble("Salario"));
            p.setPosicion(rs.getString("Posicion"));
            
            if (p.getUltimoNombre().equals(UltimoNombre))  empleado = p; break;
        }
        return empleado;
    }
    
    public static void EncontrarEmpleadoPorNombre(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca Primer Nombre: ");
        String PrimerNombre = s.next();
        System.out.println("Introduzca Ultimo Nombre: ");
        String UltimoNombre = s.next();
        String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Correo`, `Telefono`, `Salario`,"
                + "`Posicion` FROM `empleados` WHERE `PrimerNombre` = \""+PrimerNombre+"\";";
        ResultSet rs = database.getStatement().executeQuery(get);
        Empleado empleado = new Empleado();
        while (rs.next()) {
            Empleado p = new Empleado();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setPrimerNombre(rs.getString("PrimerNombre"));
            p.setUltimoNombre(rs.getString("UltimoNombre"));
            p.setTelefono(rs.getString("Telefono"));
            p.setCorreo(rs.getString("Correo"));
            p.setSalario(rs.getDouble("Salario"));
            p.setPosicion(rs.getString("Posicion"));
            
            if (p.getUltimoNombre().equals(UltimoNombre))  empleado = p; break;
        }
        System.out.println();
        empleado.print();
    }
    
     public static void MostrarTodosLosEmpleados(Database database) throws SQLException {
                ArrayList<Empleado> empleados  = ConseguirTodosLosEmpleados(database);
                System.out.println("\n-----------------------------------------------");
                for (Empleado p : empleados) {
                    p.print();
                }
                System.out.println("-----------------------------------------------\n");
    }
     
     public static void EliminarEmpleado(Database database, Scanner s) throws SQLException {
        System.out.println("Introduzca ID del Empleado: \n(-1 para Eliminar Empleados por el Nombre)");
        int id = s.nextInt();
        Empleado empleado;
        
        if (id == -1) {
            empleado = ConseguirEmpleadoPorNombre(database, s);
        } else {
              String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Correo`, `Telefono`, `Salario`,"
                      + "`Posicion` FROM `empleados` WHERE `id` = "+id+" ;";
              ResultSet rs = database.getStatement().executeQuery(get);
              Empleado p = new Empleado();
              rs.next();
              p.setId(Integer.parseInt(rs.getString("id")));
              p.setPrimerNombre(rs.getString("PrimerNombre"));
              p.setUltimoNombre(rs.getString("UltimoNombre"));
              p.setTelefono(rs.getString("Telefono"));
              p.setCorreo(rs.getString("Correo"));
              p.setSalario(rs.getDouble("Salario"));
              p.setPosicion(rs.getString("Posicion"));
              empleado = p;
        }
        
        String delete = "DELETE FROM `empleados` WHERE `id` = "+empleado.getId()+" ;";
        database.getStatement().execute(delete);
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("!Empleado Eliminado con Exito!");
        System.out.println("------------------------------");
        System.out.println();
    }
    
    private static ArrayList<Empleado> ConseguirTodosLosEmpleados(Database database) throws SQLException {
        String get = "SELECT * FROM `empleados`;";
        ResultSet rs = database.getStatement().executeQuery(get);
        ArrayList<Empleado> empleados = new ArrayList<>();
        while (rs.next()) {
            Empleado p = new Empleado();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setPrimerNombre(rs.getString("PrimerNombre"));
            p.setUltimoNombre(rs.getString("UltimoNombre"));
            p.setTelefono(rs.getString("Telefono"));
            p.setCorreo(rs.getString("Correo"));
            p.setSalario(Double.parseDouble(rs.getString("Salario")));
            p.setPosicion(rs.getString("Posicion"));
            empleados.add(p);
        }
        return empleados;
    }
    
    public static Empleado ConseguirEmpleadosPorID(Database database, int id) throws SQLException {
        String get = "SELECT `id`, `PrimerNombre`, `UltimoNombre`, `Correo`, `Telefono`, `Salario`,"
                + "`Posicion` FROM `empleados` WHERE `id` = "+id+" ;";
        ResultSet rs = database.getStatement().executeQuery(get);
        rs.next();
        Empleado p = new Empleado();
        p.setId(Integer.parseInt(rs.getString("id")));
        p.setPrimerNombre(rs.getString("PrimerNombre"));
        p.setUltimoNombre(rs.getString("UltimoNombre"));
        p.setTelefono(rs.getString("Telefono"));
        p.setCorreo(rs.getString("Correo"));
        p.setSalario(rs.getDouble("Salario"));
        p.setPosicion(rs.getString("Posicion"));
        return p;
    }
    
}
