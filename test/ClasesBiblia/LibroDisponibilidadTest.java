package ClasesBiblia;

import org.junit.Test;
import static org.junit.Assert.*;

public class LibroDisponibilidadTest {

    private Libro libroConExistencias(int existencias) {
        return new Libro(1, "El Quijote", "Cervantes", "Novela", "Planeta", existencias, 1605, 863);
    }

    // TC-01 - camino feliz: existencias positivas
    @Test
    public void setDisponibleConExistenciasPositivasQuedaDisponible() {
        Libro libro = libroConExistencias(5);
        libro.setDisponible(true);
        assertTrue(libro.isDisponible());
    }

    // TC-02 - excepcion: sin existencias
    @Test
    public void setDisponibleSinExistenciasQuedaAgotado() {
        Libro libro = libroConExistencias(0);
        libro.setDisponible(true);
        assertFalse(libro.isDisponible());
    }

    // TC-03 - excepcion: existencias negativas (dato corrupto en BD)
    @Test
    public void setDisponibleConExistenciasNegativasQuedaAgotado() {
        Libro libro = libroConExistencias(-3);
        libro.setDisponible(true);
        assertFalse(libro.isDisponible());
    }

    // TC-04 - documenta el defecto D-01
    @Test
    public void setDisponibleIgnoraElParametroRecibido() {
        Libro libro = libroConExistencias(5);
        libro.setDisponible(false);

        assertTrue("Defecto D-01: setDisponible ignora su parametro de entrada", libro.isDisponible());
    }
}
