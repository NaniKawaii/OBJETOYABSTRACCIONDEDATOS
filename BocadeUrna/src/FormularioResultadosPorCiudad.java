import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class FormularioResultadosPorCiudad extends JFrame {
    private Map<String, Integer> resultadosGenerales;
    private String ciudad;

    public FormularioResultadosPorCiudad(Map<String, Integer> resultadosGenerales, String ciudad) {
        this.resultadosGenerales = resultadosGenerales;
        this.ciudad = ciudad;

        setTitle("Resultados por Ciudad");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Resultados para la Ciudad: " + ciudad);
        label.setBounds(20, 20, 250, 20);

        JTextArea resultadosTextArea = new JTextArea();
        resultadosTextArea.setEditable(false);

        // Mostrar los resultados específicos de la ciudad
        StringBuilder resultados = new StringBuilder("==== Resultados por Ciudad ====\n");
        for (Map.Entry<String, Integer> entry : resultadosGenerales.entrySet()) {
            resultados.append(entry.getKey()).append(": ").append(entry.getValue()).append(" votos\n");
        }
        resultadosTextArea.setText(resultados.toString());

        JScrollPane scrollPane = new JScrollPane(resultadosTextArea);
        scrollPane.setBounds(20, 50, 250, 100);

        add(label);
        add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
