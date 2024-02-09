import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormularioResultGen extends JFrame {
    private JTable tablaResultados;

    public FormularioResultGen(List<Estudiantes> listaCandidatos) {
        setTitle("Resultados Generales");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Crear la tabla y el modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel();
        tablaResultados = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaResultados);

        // Agregar columnas a la tabla
        tableModel.addColumn("Candidato");
        tableModel.addColumn("Votos");

        // Agregar filas a la tabla con los resultados de los votos de los candidatos
        for (Estudiantes candidato : listaCandidatos) {
            if (candidato.isEsCandidato()) {
                tableModel.addRow(new Object[]{candidato.getNombre(), candidato.getVotos()});
            }
        }

        panel.add(scrollPane, BorderLayout.CENTER);
    }
}
