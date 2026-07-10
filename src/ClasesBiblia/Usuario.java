/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ClasesBiblia;


public class Usuario extends Persona{
    private long Telefono;

    public Usuario(int IdUsurero, String Namae, String Apellido, String Calle, String Colonia, int numero, long codigoPostal, long Telefono, String Correo) {
        super(IdUsurero, Namae, Apellido, Calle, Colonia, numero, codigoPostal, Correo);
        this.Telefono = Telefono;
    }

    public Usuario(String Namae, String Apellido, String Calle, String Colonia, int numero, long codigoPostal, long Telefono, String Correo) {
        super(Namae, Apellido, Calle, Colonia, numero, codigoPostal, Correo);
        this.Telefono = Telefono;
    }

    public long getTelefono() {
        return Telefono;
    }

    public void setTelefono(long Telefono) {
        this.Telefono = Telefono;
    }

    public String toStringUsuario() {
        return getId() + "," + getNombre() + "," + getApellido() + "," + getCalle() + "," + getColonia() + "," + getNumero() + "," + getCodigoPostal() + "," + Telefono + "," + getCorreo();
    }
    
    
    
    public void mostrarInfo(){
        System.out.println("Nombre de usuario: "+getNombre());
        System.out.println("ID Usuario: "+getId());
    }
}

