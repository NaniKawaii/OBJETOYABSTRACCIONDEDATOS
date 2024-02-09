import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormularioEstudiantes extends JFrame {
    private List<Estudiantes> listaEstudiantes;
    private DefaultTableModel tableModel;

    public FormularioEstudiantes(List<String> listaCursos, List<String> listaMesas) {
        setTitle("Formulario de Estudiantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listaEstudiantes = new ArrayList<>();

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Crear la tabla y el modelo de tabla
        tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar columnas a la tabla
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Cédula");
        tableModel.addColumn("Curso");
        tableModel.addColumn("Mesa");
        tableModel.addColumn("Candidato");

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        panel.add(inputPanel, BorderLayout.SOUTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(formPanel);

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreTextField = new JTextField();
        JLabel cedulaLabel = new JLabel("Cédula:");
        JTextField cedulaTextField = new JTextField();
        JLabel cursoLabel = new JLabel("Curso:");
        JComboBox<String> cursoComboBox = new JComboBox<>(listaCursos.toArray(new String[0]));
        JLabel mesaLabel = new JLabel("Mesa:");
        JComboBox<String> mesaComboBox = new JComboBox<>(listaMesas.toArray(new String[0]));
        JCheckBox candidatoCheckBox = new JCheckBox("Candidato");

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreTextField.getText();
                String cedula = cedulaTextField.getText();
                String curso = (String) cursoComboBox.getSelectedItem();
                String mesa = (String) mesaComboBox.getSelectedItem();
                boolean esCandidato = candidatoCheckBox.isSelected();

                Estudiantes estudiante = new Estudiantes(nombre, cedula, curso, mesa, esCandidato);
                listaEstudiantes.add(estudiante);

                // Agregar fila a la tabla
                tableModel.addRow(new Object[]{nombre, cedula, curso, mesa, esCandidato});
            }
        });

        formPanel.add(nombreLabel);
        formPanel.add(nombreTextField);
        formPanel.add(cedulaLabel);
        formPanel.add(cedulaTextField);
        formPanel.add(cursoLabel);
        formPanel.add(cursoComboBox);
        formPanel.add(mesaLabel);
        formPanel.add(mesaComboBox);
        formPanel.add(candidatoCheckBox);

        inputPanel.add(agregarButton);
    }

    public List<Estudiantes> getEstudiantes() {
        return listaEstudiantes;
    }

    public Estudiantes buscarEstudiantePorCedula(String cedula) {
        for (Estudiantes estudiante : listaEstudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }
        return null;
    }
}
