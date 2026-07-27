package GUIBiblia;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * El constructor de GUIPrestamo llena los combos de libros y usuarios desde
 * la base de datos ({@code LibroDao.obtenerLibros()},
 * {@code UsuarioDao.obtenerUsuarios()}), por lo que estas pruebas requieren
 * MySQL accesible en localhost:3306 con la base "biblioteca" (usuario/
 * contraseña root/root, tal como está fijado en ConexionBD).
 */
public class GUIPrestamoTest {

    private static final int TIMEOUT_MS = 15000;

    private GUIPrestamo ventana;

    @After
    public void cerrarVentana() throws Exception {
        VentanaTestUtils.cerrarEnEdt(ventana);
    }

    @Test(timeout = TIMEOUT_MS)
    public void seConstruyeSinLanzarExcepciones() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIPrestamo::new);
        assertNotNull(ventana);
    }

    @Test(timeout = TIMEOUT_MS)
    public void noEsVisiblePorDefecto() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIPrestamo::new);
        assertFalse(ventana.isVisible());
    }

    @Test(timeout = TIMEOUT_MS)
    public void tieneUnTamanoDefinidoTrasElPack() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIPrestamo::new);
        assertTrue(ventana.getWidth() > 0);
        assertTrue(ventana.getHeight() > 0);
    }

    @Test(timeout = TIMEOUT_MS)
    public void cierraLaAplicacionAlCerrarLaVentana() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIPrestamo::new);
        assertEquals(WindowConstants.EXIT_ON_CLOSE, ventana.getDefaultCloseOperation());
    }

    @Test(timeout = TIMEOUT_MS)
    public void tieneLosOchoCombosDeFechaYSeleccion() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIPrestamo::new);
        List<JComboBox> combos = VentanaTestUtils.buscarComponentes(ventana.getContentPane(), JComboBox.class);
        assertEquals(8, combos.size());
    }
}
