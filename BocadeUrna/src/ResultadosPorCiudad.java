import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ResultadosPorCiudad extends JFrame {
    public ResultadosPorCiudad(Map<String, Integer> resultadosPorCiudad) {
        setTitle("Resultados por Ciudad");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Crear un panel para contener las barras de progreso
        JPanel panel = new JPanel();

        // Iterar sobre los resultados y agregar barras de progreso para cada prefecto
        for (Map.Entry<String, Integer> entry : resultadosPorCiudad.entrySet()) {
            String ciudad = entry.getKey();
            Integer votos = entry.getValue();

            // Crear una etiqueta para mostrar la ciudad
            JLabel label = new JLabel(ciudad);

            // Crear una barra de progreso y establecer su valor según los votos
            JProgressBar progressBar = new JProgressBar(0, 100); // El rango puede ajustarse según tus necesidades
            progressBar.setValue(votos);

            // Configurar la barra de progreso para que sea visible solo si hay votos
            progressBar.setStringPainted(true);
            progressBar.setVisible(votos > 0);

            // Agregar la etiqueta y la barra de progreso al panel
            panel.add(label);
            panel.add(progressBar);
        }

        // Mostrar el panel en un cuadro de diálogo
        JOptionPane.showMessageDialog(this, panel, "Resultados por Ciudad", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        Map<String, Integer> resultadosPorCiudad = new HashMap<>();
        resultadosPorCiudad.put("Quito", 3);
        resultadosPorCiudad.put("Guayaquil", 1);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ResultadosPorCiudad(resultadosPorCiudad);
            }
        });
    }
}
