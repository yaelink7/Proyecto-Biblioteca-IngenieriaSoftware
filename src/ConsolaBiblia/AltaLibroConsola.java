package ConsolaBiblia;

import ClasesBiblia.Libro;
import DAOBiblia.LibroDao;
import java.io.PrintStream;
import java.util.Scanner;

public class AltaLibroConsola {

    public static final String MSG_CAMPOS_VACIOS = "Todos los campos se deben de llenar";
    public static final String MSG_SIN_EXISTENCIAS = "No puedes registrar un libro si no hay existencias";
    public static final String MSG_ANO_FUTURO = "No creo que hayas conseguido un libro del futuro";
    public static final String MSG_ANO_NEGATIVO = "Año positivo de favor";
    public static final String MSG_SIN_PAGINAS = "Debe tener al menos una pagina el Libro";
    public static final String MSG_FORMATO_NUMERICO = "Las existencias, Año de publicacion y numero de paginas son numeros";
    public static final String MSG_EXITO = "Libro guardado con exito";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ejecutar(entrada, System.out, new LibroDao());
    }

    public static void ejecutar(Scanner entrada, PrintStream salida, LibroDao dao) {
        salida.println("=== Alta de libro ===");

        salida.print("Titulo: ");
        String titulo = leerLinea(entrada);
        salida.print("Autor: ");
        String autor = leerLinea(entrada);
        salida.print("Tipo de libro: ");
        String tipoLibro = leerLinea(entrada);
        salida.print("Editorial: ");
        String editorial = leerLinea(entrada);
        salida.print("Existencias: ");
        String existencias = leerLinea(entrada);
        salida.print("Año de publicacion: ");
        String anoPublicacion = leerLinea(entrada);
        salida.print("Numero de paginas: ");
        String numeroPaginas = leerLinea(entrada);

        Libro libro = construirLibro(titulo, autor, tipoLibro, editorial,
                existencias, anoPublicacion, numeroPaginas, salida);

        if (libro == null) {
            return;
        }

        dao.insertarLibro(libro);
        salida.println(MSG_EXITO + ". ID: " + libro.getIdLibro());
    }

    public static Libro construirLibro(String titulo, String autor, String tipoLibro,
            String editorial, String existenciasTexto, String anoPublicacionTexto,
            String numeroPaginasTexto, PrintStream salida) {

        if (titulo.isEmpty() || autor.isEmpty() || tipoLibro.isEmpty() || editorial.isEmpty()
                || existenciasTexto.isEmpty() || anoPublicacionTexto.isEmpty() || numeroPaginasTexto.isEmpty()) {
            salida.println(MSG_CAMPOS_VACIOS);
            return null;
        }
        if (existenciasTexto.length() <= 0) {
            salida.println(MSG_SIN_EXISTENCIAS);
            return null;
        }
        if (anoPublicacionTexto.length() > 4) {
            salida.println(MSG_ANO_FUTURO);
            return null;
        }
        if (anoPublicacionTexto.length() < 0) {
            salida.println(MSG_ANO_NEGATIVO);
            return null;
        }
        if (numeroPaginasTexto.length() <= 0) {
            salida.println(MSG_SIN_PAGINAS);
            return null;
        }
        try {
            int existencias = Integer.parseInt(existenciasTexto);
            int anoPublicacion = Integer.parseInt(anoPublicacionTexto);
            int numeroPaginas = Integer.parseInt(numeroPaginasTexto);
            boolean disponible = true;
            return new Libro(titulo, autor, tipoLibro, editorial, existencias,
                    anoPublicacion, numeroPaginas, disponible);
        } catch (NumberFormatException e) {
            salida.println(MSG_FORMATO_NUMERICO);
            return null;
        }
    }

    private static String leerLinea(Scanner entrada) {
        if (!entrada.hasNextLine()) {
            return "";
        }
        return entrada.nextLine().trim();
    }
}
