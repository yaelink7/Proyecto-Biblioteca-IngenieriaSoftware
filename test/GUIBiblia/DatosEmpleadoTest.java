package GUIBiblia;

import java.util.List;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DatosEmpleadoTest {

    private static final int TIMEOUT_MS = 15000;

    private DatosEmpleado ventana;

    @After
    public void cerrarVentana() throws Exception {
        VentanaTestUtils.cerrarEnEdt(ventana);
    }

    @Test(timeout = TIMEOUT_MS)
    public void seConstruyeSinLanzarExcepciones() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(DatosEmpleado::new);
        assertNotNull(ventana);
    }

    @Test(timeout = TIMEOUT_MS)
    public void noEsVisiblePorDefecto() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(DatosEmpleado::new);
        assertFalse(ventana.isVisible());
    }

    @Test(timeout = TIMEOUT_MS)
    public void tieneUnTamanoDefinidoTrasElPack() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(DatosEmpleado::new);
        assertTrue(ventana.getWidth() > 0);
        assertTrue(ventana.getHeight() > 0);
    }

    @Test(timeout = TIMEOUT_MS)
    public void cierraLaAplicacionAlCerrarLaVentana() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(DatosEmpleado::new);
        assertEquals(WindowConstants.EXIT_ON_CLOSE, ventana.getDefaultCloseOperation());
    }

    @Test(timeout = TIMEOUT_MS)
    public void laTablaDeEmpleadosTieneLasNueveColumnasEsperadas() throws Exception {
        ventana = VentanaTestUtils.crearEnEdt(DatosEmpleado::new);
        List<JTable> tablas = VentanaTestUtils.buscarComponentes(ventana.getContentPane(), JTable.class);
        assertEquals(1, tablas.size());
        assertEquals(9, tablas.get(0).getColumnCount());
    }
}
