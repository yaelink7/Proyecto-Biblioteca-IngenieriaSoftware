/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ClasesBiblia;


public class Publicacion {
    private int idLibro;
    private String titulo, autor;
    private int anoPublicacion;
    private int existencias;

    public Publicacion(int idLibro, String titulo, String autor, int anoPublicacion, int existencias) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.existencias = existencias;
    }
    
    

    public Publicacion(String titulo, String autor, int anoPublicacion, int existencias) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.existencias = existencias;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
    
    
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor=autor;
    }
    
    
    public int getPublicacion(){
        return anoPublicacion;
    }
    public void setPublicacion(int anopubli){
        this.anoPublicacion=anopubli;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    @Override
    public String toString() {
        return idLibro + "," + titulo + "," + autor + "," + anoPublicacion + "," + existencias;
    }
    
    

}