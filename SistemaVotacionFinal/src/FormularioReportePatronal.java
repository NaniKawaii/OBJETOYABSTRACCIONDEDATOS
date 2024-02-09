import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormularioReportePatronal extends JFrame {
    private JTable tablaEstudiantes;

    public FormularioReportePatronal(List<Estudiantes> listaEstudiantes) {
        setTitle("Reporte Padrón Electoral");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Crear la tabla y el modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel();
        tablaEstudiantes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);

        // Agregar columnas a la tabla
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Cédula");
        tableModel.addColumn("Curso");
        tableModel.addColumn("Mesa");
        tableModel.addColumn("Candidato");

        // Agregar filas a la tabla con la información de los estudiantes
        for (Estudiantes estudiante : listaEstudiantes) {
            tableModel.addRow(new Object[]{estudiante.getNombre(), estudiante.getCedula(), estudiante.getCursoSeleccionado(), estudiante.getMesaSeleccionada(), estudiante.isEsCandidato()});
        }

        panel.add(scrollPane, BorderLayout.CENTER);
    }
}
