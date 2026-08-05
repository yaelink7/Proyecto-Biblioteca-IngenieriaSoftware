package GUIBiblia;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

final class VentanaTestUtils {

    private VentanaTestUtils() {
    }

    static <T extends JFrame> T crearEnEdt(Callable<T> fabricaVentana) throws Exception {
        final Object[] resultado = new Object[1];
        final Exception[] error = new Exception[1];
        SwingUtilities.invokeAndWait(() -> {
            try {
                resultado[0] = fabricaVentana.call();
            } catch (Exception e) {
                error[0] = e;
            }
        });
        if (error[0] != null) {
            throw error[0];
        }
        @SuppressWarnings("unchecked")
        T ventana = (T) resultado[0];
        return ventana;
    }

    static void cerrarEnEdt(JFrame ventana) throws Exception {
        if (ventana == null) {
            return;
        }
        SwingUtilities.invokeAndWait(ventana::dispose);
    }

    static <T extends Component> List<T> buscarComponentes(Container contenedor, Class<T> tipo) {
        List<T> encontrados = new ArrayList<>();
        for (Component hijo : contenedor.getComponents()) {
            if (tipo.isInstance(hijo)) {
                encontrados.add(tipo.cast(hijo));
            }
            if (hijo instanceof Container) {
                encontrados.addAll(buscarComponentes((Container) hijo, tipo));
            }
        }
        return encontrados;
    }
}
