import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.List;

public class FormularioGrafico extends JFrame {
    public FormularioGrafico(List<Estudiantes> listaCandidatos) {
        setTitle("Resultados Generales - Gráfico de Barras");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear el dataset de los votos por candidato
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Estudiantes candidato : listaCandidatos) {
            if (candidato.isEsCandidato()) {
                dataset.addValue(candidato.getVotos(), "Votos", candidato.getNombre());
            }
        }

        // Crear el gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Resultados de Votación por Candidato", // Título del gráfico
                "Candidato",                            // Etiqueta del eje X
                "Votos",                                // Etiqueta del eje Y
                dataset);                               // Dataset de los datos

        // Configurar aspectos del gráfico
        chart.setBackgroundPaint(Color.white);

        // Crear el panel del gráfico y agregarlo al formulario
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        getContentPane().add(chartPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Ejemplo de uso del formulario
            List<Estudiantes> listaCandidatos = obtenerListaCandidatos(); // Obtener la lista de candidatos
            FormularioGrafico formularioGrafico = new FormularioGrafico(listaCandidatos);
            formularioGrafico.setVisible(true);
        });
    }

    // Método de ejemplo para obtener la lista de candidatos
    private static List<Estudiantes> obtenerListaCandidatos() {
        // Aquí deberías obtener la lista de candidatos desde alguna fuente de datos
        // Por simplicidad, retornamos una lista vacía en este ejemplo
        return new ArrayList<>();
    }
}
