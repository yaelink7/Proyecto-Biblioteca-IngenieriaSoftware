/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ClasesBiblia;

public class Libro extends Publicacion{
    private String tipoLibro;
    private String editorial;
    private int Numpaginas;
    private boolean disponible;

    public Libro(int idLibro, String titulo, String autor, String tipoLibro, String editorial, int existencias, int anoPublicacion, int Numpaginas) {
        super(idLibro, titulo, autor, anoPublicacion, existencias);
        this.tipoLibro = tipoLibro;
        this.editorial = editorial;
        this.Numpaginas = Numpaginas;
    }

    public Libro(int idLibro, String titulo, String autor, String tipoLibro, String editorial, int existencias, int anoPublicacion, int Numpaginas ,boolean disponible) {
        super(idLibro, titulo, autor, anoPublicacion, existencias);
        this.tipoLibro = tipoLibro;
        this.editorial = editorial;
        this.Numpaginas = Numpaginas;
        this.disponible = disponible;
    }
    public Libro(String titulo, String autor, String tipoLibro, String editorial, int existencias, int anoPublicacion, int Numpaginas ,boolean disponible) {
        super( titulo, autor, anoPublicacion, existencias);
        this.tipoLibro = tipoLibro;
        this.editorial = editorial;
        this.Numpaginas = Numpaginas;
        this.disponible = disponible;
    }

    public Libro(String tipoLibro, String editorial, int Numpaginas, boolean disponible, String titulo, String autor, int anoPublicacion, int existencias) {
        super(titulo, autor, anoPublicacion, existencias);
        this.tipoLibro = tipoLibro;
        this.editorial = editorial;
        this.Numpaginas = Numpaginas;
        this.disponible = disponible;
    }

    public String getTipoLibro() {
        return tipoLibro;
    }

    public String getEditorial() {
        return editorial;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setTipoLibro(String tipoLibro) {
        this.tipoLibro = tipoLibro;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setDisponible(boolean disponible) {
        if (getExistencias()<=0){
            this.disponible=false;
        }else{
            this.disponible=true;
        }
    }

    public int getNumpaginas() {
        return Numpaginas;
    }

    public void setNumpaginas(int Numpaginas) {
        this.Numpaginas = Numpaginas;
    }

    public String toStringLibro() {
        return getIdLibro()+","+getTitulo()+","+getAutor()+","+tipoLibro + "," + editorial + "," + getExistencias()+","+getAnoPublicacion()+","+Numpaginas + "," + disponible ;
    }
    
    

}
