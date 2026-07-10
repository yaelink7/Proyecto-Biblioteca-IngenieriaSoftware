/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ClasesBiblia;


public class Empleado extends Persona{
    private String tipoEmpleado;

    public Empleado(int IdUsurero, String Namae, String Apellido, String Calle, String Colonia, int numero, long codigoPostal, String Correo, String tipoEmpleado) {
        super(IdUsurero, Namae, Apellido, Calle, Colonia, numero, codigoPostal, Correo);
        this.tipoEmpleado = tipoEmpleado;
    }

    public Empleado(String Namae, String Apellido, String Calle, String Colonia, int numero, long codigoPostal, String Correo, String tipoEmpleado) {
        super(Namae, Apellido, Calle, Colonia, numero, codigoPostal, Correo);
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
    
    public String toStringUsuario() {
        return getId() + "," + getNombre() + "," + getApellido() + "," + getCalle() + "," + getColonia() + "," + getNumero() + "," + getCodigoPostal() + "," + getCorreo()+ "," + tipoEmpleado;
    }

}