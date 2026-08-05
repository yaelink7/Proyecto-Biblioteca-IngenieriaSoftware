package DAOBiblia;

import ClasesBiblia.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioDaoCsvTest {

    private File archivoTemporal;

    @After
    public void limpiar() {
        if (archivoTemporal != null && archivoTemporal.exists()) {
            archivoTemporal.delete();
        }
    }

    // TC-18 - camino feliz: ida y vuelta de un usuario
    @Test
    public void guardarYCargarUsuarioConservaNombreTelefonoYCorreo() throws IOException {
        archivoTemporal = new File(System.getProperty("java.io.tmpdir"), "tc18_usuarios.csv");
        archivoTemporal.delete();
        UsuarioDao dao = new UsuarioDao(archivoTemporal.getAbsolutePath());

        List<Usuario> original = new ArrayList<>();
        original.add(new Usuario(1, "Ana", "Ruiz", "Calle", "Colonia", 10, 91700L, 2281234567L, "ana@correo.com"));

        dao.guardarUsuarioCSV(original);
        List<Usuario> recuperados = dao.cargarUsuariosCSV();

        assertEquals(1, recuperados.size());
        assertEquals("Ana", recuperados.get(0).getNombre());
        assertEquals(2281234567L, recuperados.get(0).getTelefono());
        assertEquals("ana@correo.com", recuperados.get(0).getCorreo());
    }
}
