import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormularioCandidatos extends JFrame {
    private JTable candidatosTable;
    private DefaultTableModel tableModel;

    public FormularioCandidatos(List<Estudiantes> listaEstudiantes) {
        setTitle("Lista de Candidatos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Crear la tabla para mostrar los candidatos
        candidatosTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Nombre", "Cédula", "Curso", "Mesa"}, 0);
        candidatosTable.setModel(tableModel);

        // Agregar los candidatos a la tabla
        for (Estudiantes estudiantes : listaEstudiantes) {
            if (estudiantes.isEsCandidato()) {
                agregarCandidatoATabla(estudiantes);
            }
        }

        panel.add(new JScrollPane(candidatosTable), BorderLayout.CENTER);

        add(panel);
    }

    private void agregarCandidatoATabla(Estudiantes estudiantes) {
        tableModel.addRow(new Object[]{estudiantes.getNombre(), estudiantes.getCedula(), estudiantes.getCursoSeleccionado(), estudiantes.getMesaSeleccionada()});
    }
}
