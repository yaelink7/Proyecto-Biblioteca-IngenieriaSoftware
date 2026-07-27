package GUIBiblia;

import javax.swing.WindowConstants;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * El constructor de GUIUsuario carga los datos desde la base de datos
 * ({@code UsuarioDao.obtenerUsuarios()}), por lo que estas pruebas
 * requieren MySQL accesible en localhost:3306 con la base "biblioteca"
 * (usuario/contraseña root/root, tal como está fijado en ConexionBD).
 *
 * Nota: a diferencia de GUIEmpleado/GUILibro, esta ventana no expone
 * ningún JTable visible (el modelo de tabla que carga nunca se asigna
 * a un componente en pantalla), por eso aquí no se prueba una tabla.
 */
public class GUIUsuarioTest {

    private static final int TIMEOUT_MS = 15000;

    private GUIUsuario ventana;

    @After
    public void cerrarVentana() throws Exception {
        VentanaTestUtils.cerrarEnEdt(ventana);
    }

    @Test(timeout = TIMEOUT_MS)
    public void seConstruyeSinLanzarExcepciones() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIUsuario::new);
        assertNotNull(ventana);
    }

    @Test(timeout = TIMEOUT_MS)
    public void noEsVisiblePorDefecto() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIUsuario::new);
        assertFalse(ventana.isVisible());
    }

    @Test(timeout = TIMEOUT_MS)
    public void tieneUnTamanoDefinidoTrasElPack() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIUsuario::new);
        assertTrue(ventana.getWidth() > 0);
        assertTrue(ventana.getHeight() > 0);
    }

    @Test(timeout = TIMEOUT_MS)
    public void cierraLaAplicacionAlCerrarLaVentana() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(GUIUsuario::new);
        assertEquals(WindowConstants.EXIT_ON_CLOSE, ventana.getDefaultCloseOperation());
    }
}
