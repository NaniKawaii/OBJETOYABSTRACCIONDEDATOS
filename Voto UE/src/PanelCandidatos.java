import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelCandidatos extends JDialog {
    private ArrayList<String[]> listaCandidatos;

    public PanelCandidatos(JFrame parent, ArrayList<String[]> listaEstudiantesRegistrados) {
        super(parent, "Candidatos Registrados", true);

        // Inicializar la lista de candidatos
        listaCandidatos = new ArrayList<>();

        // Filtrar la lista de estudiantes para obtener candidatos
        for (String[] estudiante : listaEstudiantesRegistrados) {
            if (Boolean.parseBoolean(estudiante[4])) { // Verificar si es candidato (columna 4)
                listaCandidatos.add(estudiante);
            }
        }

        // Crear el modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Cédula");
        model.addColumn("Curso");
        model.addColumn("Número de Mesa");

        // Agregar filas a la tabla con los candidatos registrados
        for (String[] candidato : listaCandidatos) {
            model.addRow(new Object[]{candidato[0], candidato[1], candidato[2], candidato[3]});
        }

        // Crear la tabla y mostrarla en un cuadro de diálogo
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }
}
