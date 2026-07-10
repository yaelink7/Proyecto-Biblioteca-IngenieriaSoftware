/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAOBiblia;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    
    public Connection getConexion() {
        Connection conexionNueva = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String usuario = "root"; 
            String password = "root";
            String baseDeDatos = "biblioteca"; 
            
            // Creamos una conexión fresca
            conexionNueva = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + baseDeDatos, usuario, password);
            System.out.println("Conexión exitosa a la base de datos");
            
        } catch (Exception ex) {
            System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
        }
        
        return conexionNueva;
    }
}