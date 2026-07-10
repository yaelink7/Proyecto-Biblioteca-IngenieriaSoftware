/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAOBiblia;

import ClasesBiblia.Empleado;
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

public class EmpleadoDao { 
    private final File archivoEmpleado;
    
    
    public EmpleadoDao(String rutaArchivo){
        this.archivoEmpleado = new File(rutaArchivo);
    }

    
    public EmpleadoDao() {
        this.archivoEmpleado = new File("empleados.csv");
    }
    
    public void insertarEmpleado(Empleado empleado) {
        String sql = "insert into empleados (Nombre, Apellido, Calle, Colonia, Numero, CodigoPostal, Correo, TipoEmpleado) values (?,?,?,?,?,?,?,?)";

        try (Connection conn = new ConexionBD().getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getCalle());
            stmt.setString(4, empleado.getColonia());
            stmt.setInt(5, empleado.getNumero());
            stmt.setLong(6, empleado.getCodigoPostal());
            stmt.setString(7, empleado.getCorreo());
            stmt.setString(8, empleado.getTipoEmpleado());
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    empleado.setId(idGenerado); 
                    System.out.println("Empleado insertado con ID: " + idGenerado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar empleado: " + e.getMessage());
        }
    }

    public List<Empleado> obtenerEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "select * from empleados";

        try (Connection conn = new ConexionBD().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empleado e = new Empleado(
                    rs.getInt("id"),              
                    rs.getString("Nombre"),       
                    rs.getString("Apellido"),
                    rs.getString("Calle"),
                    rs.getString("Colonia"),
                    rs.getInt("Numero"),
                    rs.getLong("CodigoPostal"),   
                    rs.getString("Correo"),       
                    rs.getString("TipoEmpleado")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarEmpleado(Empleado empleado) {
        String sql = "update empleados set Nombre=?, Apellido=?, Calle=?, Colonia=?, Numero=?, CodigoPostal=?, Correo=?, TipoEmpleado=? where id=?";
        try (Connection conn = new ConexionBD().getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getCalle());
            stmt.setString(4, empleado.getColonia());
            stmt.setInt(5, empleado.getNumero());
            stmt.setLong(6, empleado.getCodigoPostal());
            stmt.setString(7, empleado.getCorreo());
            stmt.setString(8, empleado.getTipoEmpleado());           
            stmt.setInt(9, empleado.getId());
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Empleado actualizado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
        }
    }
    
    public void eliminarEmpleado(int idEmpleado) {
        String sql = "delete from empleados where id=?";
        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmpleado);       
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Empleado eliminado.");
            } else {
                System.out.println("No se encontró empleado con ID: " + idEmpleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }
    }

    // Aquí empieza el manejo de CSV
    public List<Empleado> cargarEmpleadoCSV() throws IOException {
        List<Empleado> lista = new ArrayList<>();
        if (!archivoEmpleado.exists()) {
            archivoEmpleado.createNewFile();
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEmpleado))) {
            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                if (primera) {
                    primera = false;
                    continue;
                }
                String[] datosUnidades = linea.split(",");
                if (datosUnidades.length == 9) {
                    Empleado d = new Empleado(
                            Integer.parseInt(datosUnidades[0]),
                            datosUnidades[1],
                            datosUnidades[2],
                            datosUnidades[3],
                            datosUnidades[4],
                            Integer.parseInt(datosUnidades[5]),
                            Long.parseLong(datosUnidades[6]),
                            datosUnidades[7],
                            datosUnidades[8]);
                    lista.add(d);
                }
            }
        } 
        return lista;
    }
    
    public void guardarEmpleadoCSV(List<Empleado> emp) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoEmpleado))) {
            // Encabezado corregido para coincidir con tus 9 campos
            bw.write("id,Nombre,Apellido,Calle,Colonia,Numero,CP,Correo,TipoEmpleado");
            bw.newLine();
            // Filas
            for (Empleado d : emp) {
                bw.write(d.toStringUsuario());
                bw.newLine();
            }
        }
    }
}