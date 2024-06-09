package tablas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DatabaseConnection;

public class OperacionesCRUD {

    public void insertarContacto(Contacto contacto) {
        String sql = "INSERT INTO dbo.contactos (Nombre, Apellidos, DNI, Telefono, Ecorreo, Fechacumple, Direccion, Poblacion, Provincia, CodigoPostal, Categoria, Deuda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conexion = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, contacto.getNombre());
            pstmt.setString(2, contacto.getApellidos());
            pstmt.setString(3, contacto.getDni());
            pstmt.setString(4, contacto.getTelefono());
            pstmt.setString(5, contacto.getEcorreo());
            pstmt.setDate(6, java.sql.Date.valueOf(contacto.getFechacumple()));
            pstmt.setString(7, contacto.getDireccion());
            pstmt.setString(8, contacto.getPoblacion());
            pstmt.setInt(9, contacto.getProvincia());
            pstmt.setString(10, contacto.getCodigoPostal());
            pstmt.setString(11, contacto.getCategoria());
            pstmt.setInt(12, contacto.getDeuda());
            pstmt.executeUpdate();

            // Obtener el ID generado automáticamente
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contacto.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID del contacto insertado.");
                }
            }
            System.out.println("Contacto insertado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leerContactos() {
        String sql = "SELECT * FROM dbo.contactos";
        
        try (Connection conexion = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Id_contacto: " + rs.getInt("Id_contacto"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Apellidos: " + rs.getString("Apellidos"));
                // Imprimir otros campos según sea necesario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarContacto(Contacto contacto) {
        String sql = "UPDATE dbo.contactos SET Telefono = ?, Ecorreo = ? WHERE Id_contacto = ?";
        
        try (Connection conexion = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, contacto.getTelefono());
            pstmt.setString(2, contacto.getEcorreo());
            pstmt.setInt(3, contacto.getId());
            pstmt.executeUpdate();
            System.out.println("Contacto actualizado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarContacto(int id) {
        String sql = "DELETE FROM dbo.contactos WHERE Id_contacto = ?";
        
        try (Connection conexion = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Contacto borrado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
