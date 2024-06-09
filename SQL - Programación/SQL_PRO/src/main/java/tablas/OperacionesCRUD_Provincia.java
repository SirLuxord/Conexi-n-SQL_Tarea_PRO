package tablas;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DatabaseConnection;

public class OperacionesCRUD_Provincia {

	public void insertarProvincia(Provincia provincia) {
        String sql = "INSERT INTO dbo.provincias (NombreProvincia) VALUES (?)";
        
        try (Connection conexion = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, provincia.getNombre_provincia());
            pstmt.executeUpdate();

            // Obtener el ID generado automáticamente
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                	provincia.setId_provincia(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID de la provincia insertada.");
                }
            }
            System.out.println("Provincia insertado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void leerProvincias() {
        String sql = "SELECT * FROM dbo.provincias";
        
        try (Connection conexion = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Id_contacto: " + rs.getInt("Provincia"));
                System.out.println("Nombre de la provincia: " + rs.getString("NombreProvincia"));
                // Imprimir otros campos según sea necesario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void actualizarProvincia(Provincia provincia) {
        String sql = "UPDATE dbo.provincias SET NombreProvincia = ? WHERE Provincia = ?";
        
        try (Connection conexion = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
        	pstmt.setString(1, provincia.getNombre_provincia());
            pstmt.setInt(2, provincia.getId_provincia());
            pstmt.executeUpdate();
            System.out.println("Provincia actualizada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void borrarProvincia(int id_provincia) {
        String sql = "DELETE FROM dbo.provincias WHERE Provincia = ?";
        
        try (Connection conexion = DatabaseConnection.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id_provincia);
            pstmt.executeUpdate();
            System.out.println("Provincia borrada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
