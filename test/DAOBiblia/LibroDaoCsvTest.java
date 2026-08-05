package DAOBiblia;

import ClasesBiblia.Libro;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibroDaoCsvTest {

    private File archivoTemporal;

    @After
    public void limpiar() {
        if (archivoTemporal != null && archivoTemporal.exists()) {
            archivoTemporal.delete();
        }
    }

    private File nuevoArchivoTemporal(String nombre) {
        archivoTemporal = new File(System.getProperty("java.io.tmpdir"), nombre);
        archivoTemporal.delete();
        return archivoTemporal;
    }

    // TC-14 - camino feliz: ida y vuelta completa de dos libros
    @Test
    public void guardarYCargarLibrosConservaLosValores() throws IOException {
        LibroDao dao = new LibroDao(nuevoArchivoTemporal("tc14_libros.csv").getAbsolutePath());
        List<Libro> original = new ArrayList<>();
        original.add(new Libro(1, "El Quijote", "Cervantes", "Novela", "Planeta", 3, 1605, 863, true));
        original.add(new Libro(2, "Rayuela", "Cortazar", "Novela", "Sudamericana", 1, 1963, 600, true));

        dao.guardarLibrosCSV(original);
        List<Libro> recuperados = dao.cargarLibrosCSV();

        assertEquals(2, recuperados.size());
        assertEquals("El Quijote", recuperados.get(0).getTitulo());
        assertEquals("Rayuela", recuperados.get(1).getTitulo());
        assertEquals(3, recuperados.get(0).getExistencias());
    }

    // TC-15 - excepcion: el archivo de respaldo aun no existe
    @Test
    public void cargarSinArchivoLoCreaYDevuelveListaVacia() throws IOException {
        File archivo = nuevoArchivoTemporal("tc15_no_existe.csv");
        LibroDao dao = new LibroDao(archivo.getAbsolutePath());

        List<Libro> resultado = dao.cargarLibrosCSV();

        assertTrue(resultado.isEmpty());
        assertTrue("El DAO deberia crear el archivo si todavia no existe", archivo.exists());
    }

    // TC-16 - documenta el defecto D-04
    @Test
    public void lineaConColumnasFaltantesSeDescartaSinAviso() throws IOException {
        File archivo = nuevoArchivoTemporal("tc16_malformado.csv");
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write("id,Titulo,Autor,TipoLibro,Editorial,Existencias,Año,Paginas,Disponible\n");
            fw.write("1,Titulo Incompleto,Autor,Novela,Editorial,3,2000\n");
        }
        LibroDao dao = new LibroDao(archivo.getAbsolutePath());

        List<Libro> resultado = dao.cargarLibrosCSV();

        assertEquals("Defecto D-04: la linea con columnas faltantes se pierde sin aviso", 0, resultado.size());
    }

    // TC-17 - documenta el defecto D-05
    @Test(expected = NumberFormatException.class)
    public void campoNumericoConTextoPropagaExcepcionSinCapturar() throws IOException {
        File archivo = nuevoArchivoTemporal("tc17_texto_en_numero.csv");
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write("id,Titulo,Autor,TipoLibro,Editorial,Existencias,Año,Paginas,Disponible\n");
            fw.write("1,Titulo,Autor,Novela,Editorial,abc,2000,300,true\n");
        }
        LibroDao dao = new LibroDao(archivo.getAbsolutePath());

        dao.cargarLibrosCSV();
    }
}
