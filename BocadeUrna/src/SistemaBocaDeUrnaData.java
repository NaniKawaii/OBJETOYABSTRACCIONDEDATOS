import java.util.ArrayList;
import java.util.List;

public class SistemaBocaDeUrnaData {
    // Lista simulada de prefectos
    private static List<String> nombresPrefectos = new ArrayList<>();

    // Método para agregar un prefecto a la lista
    public static void agregarPrefecto(String nombre) {
        nombresPrefectos.add(nombre);
    }

    // Método para obtener los nombres de todos los prefectos
    public static String[] obtenerNombresPrefectos() {
        return nombresPrefectos.toArray(new String[0]);
    }
}
