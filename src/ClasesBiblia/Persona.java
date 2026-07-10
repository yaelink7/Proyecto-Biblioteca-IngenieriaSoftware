/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ClasesBiblia;


public class Persona {
    private int IdUsurero;
    private String Namae;
    private String Apellido;
    private String Calle;
    private String Colonia;
    private int numero;
    private long codigoPostal;
    private String Correo;

    public Persona(int IdUsurero, String Namae, String Apellido, String Calle, String Colonia, int numero, long codigoPostal, String Correo) {
        this.IdUsurero = IdUsurero;
        this.Namae = Namae;
        this.Apellido = Apellido;
        this.Calle = Calle;
        this.Colonia = Colonia;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.Correo = Correo;
    }

    public Persona(String Namae, String Apellido, String Calle, String Colonia, int numero, long codigoPostal, String Correo) {
        this.Namae = Namae;
        this.Apellido = Apellido;
        this.Calle = Calle;
        this.Colonia = Colonia;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.Correo = Correo;
    }
    
    public String getApellido() {
        return Apellido;
    }

    public String getCalle() {
        return Calle;
    }

    public String getColonia() {
        return Colonia;
    }

    public int getNumero() {
        return numero;
    }

    public long getCodigoPostal() {
        return codigoPostal;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCodigoPostal(long codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
    
    public String getNombre(){
        return Namae;
    }
    public void setNombre(String namae){
        this.Namae=namae;
    }
    
    
    public int getId(){
        return IdUsurero;
    }
    public void setId(int id){
        this.IdUsurero=id;
    }

    public String toString() {
        return IdUsurero + "," + Namae + "," + Apellido + "," + Calle + "," + Colonia + "," + numero + "," + codigoPostal  + "," + Correo;
    }

}
