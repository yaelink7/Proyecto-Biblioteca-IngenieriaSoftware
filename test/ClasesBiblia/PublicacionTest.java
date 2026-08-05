package ClasesBiblia;

import org.junit.Test;
import static org.junit.Assert.*;

public class PublicacionTest {

    // TC-08 - camino feliz: publicacion recuperada de la base con id
    @Test
    public void constructorConIdAsignaIdYExistencias() {
        Publicacion p = new Publicacion(10, "Titulo", "Autor", 2020, 4);

        assertEquals(10, p.getIdLibro());
        assertEquals(4, p.getExistencias());
    }

    // TC-09 - excepcion: publicacion nueva sin id de base de datos
    @Test
    public void constructorSinIdDejaIdEnCero() {
        Publicacion p = new Publicacion("Titulo", "Autor", 2020, 4);

        assertEquals(0, p.getIdLibro());
    }

    // TC-10 - documenta el defecto D-03
    @Test
    public void getPublicacionYGetAnoPublicacionDevuelvenElMismoValor() {
        Publicacion p = new Publicacion("Titulo", "Autor", 1999, 4);

        assertEquals(1999, p.getPublicacion());
        assertEquals(p.getAnoPublicacion(), p.getPublicacion());
    }
}
