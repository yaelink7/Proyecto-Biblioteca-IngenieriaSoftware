package ClasesBiblia;

import org.junit.Test;
import static org.junit.Assert.*;

public class HerenciaPersonaTest {

    // TC-11 - camino feliz
    @Test
    public void usuarioSerializaNueveCamposConTelefonoEnOctavaPosicion() {
        Usuario u = new Usuario(1, "Ana", "Ruiz", "Calle", "Colonia", 10, 91700L, 2281234567L, "ana@correo.com");
        String[] campos = u.toStringUsuario().split(",");

        assertEquals(9, campos.length);
        assertEquals("2281234567", campos[7]);
    }

    // TC-12 - camino feliz
    @Test
    public void empleadoSerializaNueveCamposConTipoEmpleadoEnNovenaPosicion() {
        Empleado e = new Empleado(1, "Luis", "Diaz", "Calle", "Colonia", 10, 91700L, "luis@correo.com", "Bibliotecario");
        String[] campos = e.toStringUsuario().split(",");

        assertEquals(9, campos.length);
        assertEquals("Bibliotecario", campos[8]);
    }

    // TC-13 - excepcion: alta de persona sin identificador
    @Test
    public void constructorSinIdDejaIdEnCero() {
        Persona p = new Persona("Ana", "Ruiz", "Calle", "Colonia", 10, 91700L, "ana@correo.com");

        assertEquals(0, p.getId());
    }
}
