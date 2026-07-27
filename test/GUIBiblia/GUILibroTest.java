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
 * El constructor de GUILibro carga los datos desde la base de datos
 * ({@code LibroDao.obtenerLibros()}), por lo que estas pruebas requieren
 * MySQL accesible en localhost:3306 con la base "biblioteca" (usuario/
 * contraseña root/root, tal como está fijado en ConexionBD).
 *
 * Nota: pese a construir un {@code DefaultTableModel} con los libros,
 * esta ventana nunca lo asocia a un JTable visible (confirmado al
 * ejecutar las pruebas), por eso aquí no se comprueba ninguna tabla.
 */
public class GUILibroTest {

    private static final int TIMEOUT_MS = 15000;

    private GUILibro ventana;

    @After
    public void cerrarVentana() throws Exception {
        VentanaTestUtils.cerrarEnEdt(ventana);
    }

    @Test(timeout = TIMEOUT_MS)
    public void seConstruyeSinLanzarExcepciones() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUILibro::new);
        assertNotNull(ventana);
    }

    @Test(timeout = TIMEOUT_MS)
    public void noEsVisiblePorDefecto() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUILibro::new);
        assertFalse(ventana.isVisible());
    }

    @Test(timeout = TIMEOUT_MS)
    public void tieneUnTamanoDefinidoTrasElPack() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUILibro::new);
        assertTrue(ventana.getWidth() > 0);
        assertTrue(ventana.getHeight() > 0);
    }

    @Test(timeout = TIMEOUT_MS)
    public void cierraLaAplicacionAlCerrarLaVentana() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUILibro::new);
        assertEquals(WindowConstants.EXIT_ON_CLOSE, ventana.getDefaultCloseOperation());
    }

    @Test(timeout = TIMEOUT_MS)
    public void elComboDeTipoDeLibroTieneLosOnceGenerosDefinidos() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUILibro::new);
        List<JComboBox> combos = VentanaTestUtils.buscarComponentes(ventana.getContentPane(), JComboBox.class);
        assertEquals(1, combos.size());
        assertEquals(11, combos.get(0).getItemCount());
    }
}
