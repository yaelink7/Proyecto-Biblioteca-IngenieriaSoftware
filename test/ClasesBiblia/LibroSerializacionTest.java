package ClasesBiblia;

import org.junit.Test;
import static org.junit.Assert.*;

public class LibroSerializacionTest {

    // TC-05 - camino feliz
    @Test
    public void toStringLibroGeneraNueveCamposSeparadosPorComa() {
        Libro libro = new Libro(1, "El Quijote", "Cervantes", "Novela", "Planeta", 3, 1605, 863, true);
        String[] campos = libro.toStringLibro().split(",");

        assertEquals(9, campos.length);
        assertEquals("1", campos[0]);
        assertEquals("El Quijote", campos[1]);
    }

    // TC-06 - documenta el defecto D-02
    @Test
    public void tituloConComaGeneraMasDeNueveCampos() {
        Libro libro = new Libro(1, "Cien años, edicion especial", "Autor", "Novela", "Planeta", 3, 2000, 300, true);
        String[] campos = libro.toStringLibro().split(",");

        assertEquals("Defecto D-02: coma sin escapar en el titulo genera columnas de mas", 10, campos.length);
    }

    // TC-07 - excepcion: atributos nulos
    @Test
    public void camposNulosNoLanzanExcepcion() {
        Libro libro = new Libro(1, null, null, "Novela", "Planeta", 3, 2000, 300, true);
        String linea = libro.toStringLibro();

        assertNotNull(linea);
        assertTrue(linea.contains("null"));
    }
}
