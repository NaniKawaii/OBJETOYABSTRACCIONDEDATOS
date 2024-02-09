import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FormularioResultMesas extends JFrame {
    private DefaultTableModel tableModel;

    public FormularioResultMesas(List<Mesas> listaMesas, List<Estudiantes> listaEstudiantes, FormularioSufragio formularioSufragio) {
        setTitle("Resultados por Mesas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creamos un mapa para almacenar el recuento de votos por mesa y candidato
        Map<String, Map<String, Integer>> resultadosPorMesas = new HashMap<>();

        // Procesamos los votos para obtener los resultados
        for (Estudiantes estudiante : listaEstudiantes) {
            if (estudiante.isEsCandidato()) {
                String mesa = estudiante.getMesaSeleccionada();
                String candidato = estudiante.getNombre();
                // Si la mesa ya está en el mapa, actualizamos su recuento de votos para este candidato
                if (resultadosPorMesas.containsKey(mesa)) {
                    Map<String, Integer> votosPorCandidato = resultadosPorMesas.get(mesa);
                    votosPorCandidato.put(candidato, votosPorCandidato.getOrDefault(candidato, 0) + 1);
                } else { // Si la mesa no está en el mapa, creamos una nueva entrada y actualizamos su recuento de votos para este candidato
                    Map<String, Integer> votosPorCandidato = new HashMap<>();
                    votosPorCandidato.put(candidato, 1);
                    resultadosPorMesas.put(mesa, votosPorCandidato);
                }
            }
        }

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Crear la tabla y el modelo de tabla
        tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar columnas a la tabla (nombre de candidatos)
        tableModel.addColumn("Mesa");
        for (Estudiantes estudiante : listaEstudiantes) {
            if (estudiante.isEsCandidato()) {
                tableModel.addColumn(estudiante.getNombre());
            }
        }

        // Agregar las mesas incluso si no tienen votos asociados
        for (Mesas mesa : listaMesas) {
            if (!resultadosPorMesas.containsKey(mesa.getNombre())) {
                resultadosPorMesas.put(mesa.getNombre(), new HashMap<>());
            }
        }

        // Llenar la tabla con los resultados
        for (String mesa : resultadosPorMesas.keySet()) {
            Object[] rowData = new Object[tableModel.getColumnCount()];
            rowData[0] = mesa;
            Map<String, Integer> votosPorCandidato = resultadosPorMesas.get(mesa);
            for (int i = 1; i < tableModel.getColumnCount(); i++) {
                String candidato = tableModel.getColumnName(i);
                rowData[i] = votosPorCandidato.getOrDefault(candidato, 0);
            }
            tableModel.addRow(rowData);
        }

        panel.add(scrollPane, BorderLayout.CENTER);
    }
}
