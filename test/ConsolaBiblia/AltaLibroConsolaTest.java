package ConsolaBiblia;

import ClasesBiblia.Libro;
import DAOBiblia.LibroDao;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AltaLibroConsolaTest {

    private static final String TITULO = "El Quijote";
    private static final String AUTOR = "Cervantes";
    private static final String TIPO = "Novela";
    private static final String EDITORIAL = "Planeta";
    private static final String EXISTENCIAS = "3";
    private static final String ANO = "1605";
    private static final String PAGINAS = "863";

    private InputStream entradaOriginal;
    private PrintStream salidaOriginal;

    @Before
    public void guardarFlujosEstandar() {
        entradaOriginal = System.in;
        salidaOriginal = System.out;
    }

    @After
    public void restaurarFlujosEstandar() {
        System.setIn(entradaOriginal);
        System.setOut(salidaOriginal);
    }

    private static class LibroDaoFalso extends LibroDao {

        private Libro libroRecibido;

        @Override
        public void insertarLibro(Libro libro) {
            this.libroRecibido = libro;
        }
    }

    private static String comoEntrada(String... datos) {
        StringBuilder texto = new StringBuilder();
        for (String dato : datos) {
            texto.append(dato).append(System.lineSeparator());
        }
        return texto.toString();
    }

    private String ejecutarMainCon(String... datos) {
        System.setIn(new ByteArrayInputStream(comoEntrada(datos).getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));

        AltaLibroConsola.main(new String[0]);

        return buffer.toString(StandardCharsets.UTF_8);
    }

    private String ejecutarCon(LibroDaoFalso dao, String... datos) {
        Scanner entrada = new Scanner(
                new ByteArrayInputStream(comoEntrada(datos).getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream salida = new PrintStream(buffer, true, StandardCharsets.UTF_8);

        AltaLibroConsola.ejecutar(entrada, salida, dao);

        return buffer.toString(StandardCharsets.UTF_8);
    }

    @Test
    public void mainSolicitaLosSieteDatosDelLibro() {
        String salida = ejecutarMainCon("", "", "", "", "", "", "");

        assertTrue(salida.contains("Titulo:"));
        assertTrue(salida.contains("Autor:"));
        assertTrue(salida.contains("Tipo de libro:"));
        assertTrue(salida.contains("Editorial:"));
        assertTrue(salida.contains("Existencias:"));
        assertTrue(salida.contains("Numero de paginas:"));
    }

    @Test
    public void mainConTituloVacioReportaCamposObligatorios() {
        String salida = ejecutarMainCon("", AUTOR, TIPO, EDITORIAL, EXISTENCIAS, ANO, PAGINAS);

        assertTrue(salida.contains(AltaLibroConsola.MSG_CAMPOS_VACIOS));
        assertFalse(salida.contains(AltaLibroConsola.MSG_EXITO));
    }

    @Test
    public void mainConTextoEnExistenciasReportaErrorDeFormato() {
        String salida = ejecutarMainCon(TITULO, AUTOR, TIPO, EDITORIAL, "abc", ANO, PAGINAS);

        assertTrue(salida.contains(AltaLibroConsola.MSG_FORMATO_NUMERICO));
        assertFalse(salida.contains(AltaLibroConsola.MSG_EXITO));
    }

    @Test
    public void mainConAnoDeMasDeCuatroDigitosReportaLibroDelFuturo() {
        String salida = ejecutarMainCon(TITULO, AUTOR, TIPO, EDITORIAL, EXISTENCIAS, "20255", PAGINAS);

        assertTrue(salida.contains(AltaLibroConsola.MSG_ANO_FUTURO));
        assertFalse(salida.contains(AltaLibroConsola.MSG_EXITO));
    }

    @Test
    public void altaConDatosValidosConfirmaExitoYPersisteElLibro() {
        LibroDaoFalso dao = new LibroDaoFalso();

        String salida = ejecutarCon(dao, TITULO, AUTOR, TIPO, EDITORIAL, EXISTENCIAS, ANO, PAGINAS);

        assertTrue(salida.contains(AltaLibroConsola.MSG_EXITO));
        assertNotNull(dao.libroRecibido);
        assertEquals(TITULO, dao.libroRecibido.getTitulo());
        assertEquals(AUTOR, dao.libroRecibido.getAutor());
        assertEquals(3, dao.libroRecibido.getExistencias());
        assertEquals(1605, dao.libroRecibido.getAnoPublicacion());
        assertEquals(863, dao.libroRecibido.getNumpaginas());
    }

    @Test
    public void altaConCampoVacioNoLlamaAlDao() {
        LibroDaoFalso dao = new LibroDaoFalso();

        ejecutarCon(dao, TITULO, AUTOR, TIPO, "", EXISTENCIAS, ANO, PAGINAS);

        assertNull(dao.libroRecibido);
    }

    @Test
    public void existenciasEnCeroSeAceptanPorElDefectoConocido() {
        LibroDaoFalso dao = new LibroDaoFalso();

        String salida = ejecutarCon(dao, TITULO, AUTOR, TIPO, EDITORIAL, "0", ANO, PAGINAS);

        assertTrue("Defecto D-06: se valida la longitud del texto en vez del valor numerico, "
                + "por lo que un libro sin existencias se acepta",
                salida.contains(AltaLibroConsola.MSG_EXITO));
        assertEquals(0, dao.libroRecibido.getExistencias());
    }

    @Test
    public void paginasEnCeroSeAceptanPorElDefectoConocido() {
        LibroDaoFalso dao = new LibroDaoFalso();

        String salida = ejecutarCon(dao, TITULO, AUTOR, TIPO, EDITORIAL, EXISTENCIAS, ANO, "0");

        assertTrue("Defecto D-07: se valida la longitud del texto en vez del valor numerico, "
                + "por lo que un libro sin paginas se acepta",
                salida.contains(AltaLibroConsola.MSG_EXITO));
        assertEquals(0, dao.libroRecibido.getNumpaginas());
    }

    @Test
    public void libroNuevoSeMarcaDisponiblePorElDefectoConocido() {
        LibroDaoFalso dao = new LibroDaoFalso();

        ejecutarCon(dao, TITULO, AUTOR, TIPO, EDITORIAL, "0", ANO, PAGINAS);

        assertTrue("Defecto D-09: disponible se fija en true sin consultar las existencias",
                dao.libroRecibido.isDisponible());
    }
}
