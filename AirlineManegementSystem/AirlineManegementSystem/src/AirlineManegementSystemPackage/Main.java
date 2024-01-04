package AirlineManegementSystemPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Database database = new Database();
        Scanner s = new Scanner(System.in);
        
        int i = 0;
        
        do {
            System.out.println("\n-----------------------------------------------");
            System.out.println("Bienvenido al Sistema de Registro de Aeropuetos");
            System.out.println("-----------------------------------------------");
            System.out.println("01. Agregar Nuevo Pasajero");
            System.out.println("02. Mostrar Pasajeros mediante el Nombre");
            System.out.println("03. Mostrar todos los Pasajeros");
            System.out.println("04. Editar Pasajero");
            System.out.println("05. Eliminar Pasajero");
            System.out.println("-----------------------------------------------");
            System.out.println("-----------------------------------------------");
            System.out.println("06. Agregar Nuevo Empleado");
            System.out.println("07. Mostrar Empleado mediante el Nombre");
            System.out.println("08. Mostrar todos los Empleados");
            System.out.println("09. Editar Empleado");
            System.out.println("10. Despedir Empleado");
            System.out.println("-----------------------------------------------");
            System.out.println("-----------------------------------------------");
            System.out.println("11. Agregar Nuevo Avion");
            System.out.println("12. Mostrar todos los Aviones");
            System.out.println("13. Editar Avion");
            System.out.println("14. Eliminar Avion");
            System.out.println("-----------------------------------------------");
            System.out.println("-----------------------------------------------");
            System.out.println("15. Agregar Nuevo Aeropuerto");
            System.out.println("16. Mostrar todos los Aeropuertos");
            System.out.println("17. Editar Aeropuerto");
            System.out.println("18. Eliminar Aeropuerto");
            System.out.println("-----------------------------------------------");
            System.out.println("-----------------------------------------------");
            System.out.println("19. Agregar Nuevo Viaje");
            System.out.println("20. Mostrar todos los Viajes");
            System.out.println("21. Atrasar Viaje");
            System.out.println("22. Reservar Viaje");
            System.out.println("23. Establecer Objetos del Viaje");
            System.out.println("24. Cancelar Viaje");
            System.out.println("25. Mostrar Objetos del Viaje");
            System.out.println("26. Mostrar Pasajeros del Viaje");
            System.out.println("27. Salir del Sistema");
            System.out.println("-----------------------------------------------");
            System.out.println("Eliga la opcion que Desea: ");
            
            if (!s.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                s.nextLine(); 
                continue; 
            }
            
            i = s.nextInt();
            
            switch (i) {
                case 1:
                    PasajerosControl.AgregarNuevoPasajero(database, s);
                    break;
                case 2:
                    PasajerosControl.EncontrarPasajerosPorNombre(database, s);
                    break;
                case 3:
                    PasajerosControl.MostrarTodosLosPasajeros(database);
                    break;
                case 4:
                    PasajerosControl.EditarPasajeros(database, s);
                    break;
                case 5:
                    PasajerosControl.EliminarPasajeros(database, s);
                    break;
                case 6: 
                    EmpleadosControl.AgregarNuevoEmpleado(database, s);
                    break;
                case 7: 
                    EmpleadosControl.EncontrarEmpleadoPorNombre(database, s);
                    break;
                case 8:
                    EmpleadosControl.MostrarTodosLosEmpleados(database);
                    break;
                case 9:
                    EmpleadosControl.EditarEmpleado(database, s);
                    break;
                case 10: 
                    EmpleadosControl.EliminarEmpleado(database, s);
                    break;
                case 11:
                    AvionesControl.AgregarNuevoAvion(database, s);
                    break;
                case 12:
                    AvionesControl.MostrarTodosAviones(database);
                    break;
                case 13:
                    AvionesControl.EditarAviones(database, s);
                    break;
                case 14:
                    AvionesControl.EliminarAvion(database, s);
                    break;
                case 15:
                    AeropuertosControl.AgregarNuevoAeropuerto(database, s);
                    break;
                case 16:
                    AeropuertosControl.MostrarTodosLosAeropuertos(database);
                    break;
                case 17:
                    AeropuertosControl.EditarAeropuertos(database, s);
                    break;
                case 18:
                    AeropuertosControl.EliminarAeropuerto(database, s);
                    break;
                case 19:
                    ViajesControl.AgregarNuevoViaje(database, s);
                    break;
                case 20:
                    ViajesControl.MostrarTodosLosViajes(database);
                    break;
                case 21:
                    ViajesControl.AtrasarViaje(database, s);
                    break;
                case 22:
                    ViajesControl.ReservarViaje(database, s);
                    break;
                case 23:
                    ViajesControl.ColocarObjetosDeViaje(database,s);
                    break;
                case 24:
                    ViajesControl.CancelarViaje(database, s);
                    break;
                case 25:
                    ViajesControl.MostrarObjetosDeViaje(database, s);
                    break;
                case 26:
                    ViajesControl.MostrarPasajerosDeViaje(database, s);
                    break;
                case 27:
                    break;
                default: 
                    System.out.println("Opción aún no implementada. Elige otra opción.");
                    System.out.println("Presiona ENTER para continuar...");
                    br.readLine();
                    break;
            }
        } while (i!=27);
    }
}
