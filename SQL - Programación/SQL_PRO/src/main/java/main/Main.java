package main;
import java.util.Scanner;
import conexion.DatabaseConnection;
import tablas.Categoria;
import tablas.Contacto;
import tablas.OperacionesCRUD;
import tablas.OperacionesCRUD_Categoria;
import tablas.OperacionesCRUD_Provincia;
import tablas.Provincia;

public class Main {

    public static void main(String[] args) {
    	OperacionesCRUD contacto = new OperacionesCRUD();
    	OperacionesCRUD_Categoria categoria = new OperacionesCRUD_Categoria();
    	OperacionesCRUD_Provincia provincia = new OperacionesCRUD_Provincia();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        System.out.println("\nMenú de Operaciones CRUD");
        System.out.println("1. Insertar Contacto");
        System.out.println("2. Leer Contactos");
        System.out.println("3. Actualizar Contacto");
        System.out.println("4. Borrar Contacto");
        System.out.println("5. Insertar Categoria");
        System.out.println("6. Leer Categoria");
        System.out.println("7. Actualizar Categoria");
        System.out.println("8. Borrar Categoria");
        System.out.println("9. Insertar Provincia");
        System.out.println("10. Leer Provincia");
        System.out.println("11. Actualizar Provincia");
        System.out.println("12. Borrar Provincia");
        System.out.println("13. Salir");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        switch (opcion) {
            case 1:
                insertarContacto(contacto, scanner);
                break;
            case 2:
                contacto.leerContactos();
                break;
            case 3:
                actualizarContacto(contacto, scanner);
                break;
            case 4:
                borrarContacto(contacto, scanner);
                break;
            case 5:
            	insertarCategoria(categoria, scanner);
                break;
            case 6:
            	categoria.leerCategorias();
                break;
            case 7:
                actualizarCategoria(categoria, scanner);
                break;
            case 8:
                borrarCategoria(categoria, scanner);
                break;   
            case 9:
            	insertarProvincia(provincia, scanner);
                break;
            case 10:
            	provincia.leerProvincias();
                break;
            case 11:
                actualizarProvincia(provincia, scanner);
                break;
            case 12:
                borrarProvincia(provincia, scanner);
                break;         
            case 13:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }

        DatabaseConnection.cerrarConexion();
        scanner.close();
    }
    
    // Métodos contactos
    
    private static void insertarContacto(OperacionesCRUD contacto, Scanner scanner) {
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese Apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Ingrese Telefono: Será 555-55-55-55");
        String telefono = "555555555";

        System.out.println("Ingrese Ecorreo: será aa@ss.a ");
        String ecorreo = "aa@ss.a";

        System.out.println("Ingrese Fecha de Cumpleaños (YYYY-MM-DD): Será 2001-02-01");
        String fechacumple = "2001-02-011";

        System.out.print("Ingrese Dirección: ");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese Población: será asd");
        String poblacion = "asd";

        System.out.println("Ingrese Provincia (ID): será 0 ");
        int provincia = 0;

        System.out.println("Ingrese Código Postal: será 12121");
        String codigoPostal = "12121";

        System.out.println("Categoría: será CUMP");
        String categoria = "CUMP";

        System.out.print("Ingrese Deuda: ");
        int deuda = scanner.nextInt();
        scanner.nextLine(); 

        Contacto contacto_ = new Contacto(0, nombre, apellidos, dni, telefono, ecorreo, fechacumple, direccion, poblacion, provincia, codigoPostal, categoria, deuda);
        contacto.insertarContacto(contacto_);
    }

    private static void actualizarContacto(OperacionesCRUD contacto, Scanner scanner) {
        System.out.print("Ingrese Id_contacto del contacto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese nuevo Telefono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese nuevo Ecorreo: ");
        String ecorreo = scanner.nextLine();

        Contacto contacto_ = new Contacto(id, null, null, null, telefono, ecorreo, null, null, null, 0, null, null, 0);
        contacto.actualizarContacto(contacto_);
    }

    private static void borrarContacto(OperacionesCRUD contacto, Scanner scanner) {
        System.out.print("Ingrese Id_contacto del contacto a borrar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        contacto.borrarContacto(id);
    }
    
    // Métodos categoria
    
    private static void insertarCategoria(OperacionesCRUD_Categoria categoria, Scanner scanner) {
        System.out.print("Ingrese el id de la categoria: (Máx 4 caracteres)");
        String id_categoria = scanner.nextLine();

        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();

        Categoria categoria_ = new Categoria(id_categoria, descripcion);
        categoria.insertarCategoria(categoria_);
    }
    
    private static void actualizarCategoria(OperacionesCRUD_Categoria categoria, Scanner scanner) {
        System.out.print("Ingrese Id de la categoria: ");
        String id_categoria = scanner.nextLine();
        
        System.out.print("Ingrese nueva descripción: ");
        String descripcion = scanner.nextLine();

        Categoria categoria_ = new Categoria(id_categoria, descripcion);
        categoria.actualizarCategoria(categoria_);
    }
    
    private static void borrarCategoria(OperacionesCRUD_Categoria categoria, Scanner scanner) {
        System.out.print("Ingrese Id de la categoria a borrar: ");
        String id_categoria = scanner.nextLine();

        categoria.borrarCategoria(id_categoria);
    }
    
    // Métodos provincia
    
    private static void insertarProvincia(OperacionesCRUD_Provincia provincia, Scanner scanner) {
        System.out.print("Ingrese la provincia: ");
        String nom_provincia = scanner.nextLine();

        Provincia provincia_ = new Provincia(0,nom_provincia);
        provincia.insertarProvincia(provincia_);
    }
    
    private static void actualizarProvincia(OperacionesCRUD_Provincia provincia, Scanner scanner) {
        System.out.print("Ingrese Id de la provincia: ");
        int id_provincia = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        
        System.out.print("Ingrese la nueva provincia: ");
        String nom_provincia = scanner.nextLine();

        Provincia provincia_ = new Provincia(id_provincia, nom_provincia);
        provincia.actualizarProvincia(provincia_);
    }
    
    private static void borrarProvincia(OperacionesCRUD_Provincia provincia, Scanner scanner) {
        System.out.print("Ingrese Id de la provincia: ");
        int id_provincia = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        
        provincia.borrarProvincia(id_provincia);
    }
    
}
