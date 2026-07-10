/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAOBiblia;

import ClasesBiblia.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.JOptionPane;

public class UsuarioDao {
    private final File archivoUsuario;

    // Constructor para manejar el CSV
    public UsuarioDao(String rutaArchivo) {
        this.archivoUsuario = new File(rutaArchivo);
    }

    // Constructor por defecto sugerido en caso de que solo quieras usar la Base de Datos
    public UsuarioDao() {
        this.archivoUsuario = new File("usuarios.csv");
    }

    public void insertarUsuario(Usuario usuario) {
        String sql = "insert into usuarios (Nombre, Apellido, Calle, Colonia, Numero, CodigoPostal, Telefono, Correo) values (?,?,?,?,?,?,?,?)";

        try (Connection conn = new ConexionBD().getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCalle());
            stmt.setString(4, usuario.getColonia());
            stmt.setInt(5, usuario.getNumero());
            stmt.setLong(6, usuario.getCodigoPostal());
            stmt.setLong(7, usuario.getTelefono());
            stmt.setString(8, usuario.getCorreo());
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    usuario.setId(idGenerado); 
                    System.out.println("Usuario insertado con ID: " + idGenerado);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar usuario: " + e.getMessage());
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "select * from usuarios";

        try (Connection conn = new ConexionBD().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("id"),              
                    rs.getString("Nombre"),       
                    rs.getString("Apellido"),
                    rs.getString("Calle"),
                    rs.getString("Colonia"),
                    rs.getInt("Numero"),
                    rs.getLong("CodigoPostal"),   
                    rs.getLong("Telefono"),       
                    rs.getString("Correo")
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarUsuario(Usuario usuario) {
        String sql = "update usuarios set Nombre=?, Apellido=?, Calle=?, Colonia=?, Numero=?, CodigoPostal=?, Telefono=?, Correo=? where id=?";

        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCalle());
            stmt.setString(4, usuario.getColonia());
            stmt.setInt(5, usuario.getNumero());
            stmt.setLong(6, usuario.getCodigoPostal());
            stmt.setLong(7, usuario.getTelefono());
            stmt.setString(8, usuario.getCorreo());
            stmt.setInt(9, usuario.getId()); 

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminarUsuario(int idUsuario) {
        String sql = "delete from usuarios where id=?";

        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario eliminado.");
            } else {
                System.out.println("No se encontró usuario con ID: " + idUsuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }
    }

    // --- Manejo de Archivos CSV ---
    
    public List<Usuario> cargarUsuariosCSV() throws IOException {
        List<Usuario> lista = new ArrayList<>();
        if (!archivoUsuario.exists()) {
            archivoUsuario.createNewFile();
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuario))) {
            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                if (primera) {
                    primera = false;
                    continue;
                }
                String[] datosUnidades = linea.split(",");
                if (datosUnidades.length == 9) {
                    Usuario d = new Usuario(
                            Integer.parseInt(datosUnidades[0]),
                            datosUnidades[1],
                            datosUnidades[2],
                            datosUnidades[3],
                            datosUnidades[4],
                            Integer.parseInt(datosUnidades[5]),
                            Long.parseLong(datosUnidades[6]),
                            Long.parseLong(datosUnidades[7]),
                            datosUnidades[8]
                    );
                    lista.add(d);
                }
            }
        } 
        return lista;
    }
    
    public void guardarUsuarioCSV(List<Usuario> usu) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuario))) {
            // Encabezado corregido para que coincida con los 9 atributos
            bw.write("id,Nombre,Apellido,Calle,Colonia,Numero,CodigoPostal,Telefono,Correo");
            bw.newLine();
            // Filas
            for (Usuario d : usu) {
                bw.write(d.toStringUsuario());
                bw.newLine();
            }
        }
    }
}