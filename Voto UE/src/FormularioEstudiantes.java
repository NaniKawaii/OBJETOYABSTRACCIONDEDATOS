import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioEstudiantes extends JDialog {
    private JTextField nombreField;
    private JTextField cedulaField;
    private JComboBox<String> cursoComboBox;
    private JComboBox<Integer> mesaComboBox;
    private JCheckBox candidatoCheckBox;

    private ArrayList<String[]> listaEstudiantesRegistrados;
    private int numeroMesas;

    public FormularioEstudiantes(JFrame parent, int numeroMesas) {
        super(parent, "Registrar Estudiante", true);

        // Inicializar la lista de estudiantes registrados
        listaEstudiantesRegistrados = new ArrayList<>();
        this.numeroMesas = numeroMesas;

        // Crear componentes del formulario
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);

        JLabel cedulaLabel = new JLabel("Cédula:");
        cedulaField = new JTextField(10);

        JLabel cursoLabel = new JLabel("Curso:");
        String[] cursos = {"5to Básica", "6to Básica", "7mo Básica", "8vo Básica", "9no Básica", "10mo Básica",
                "1ero Bachillerato", "2do Bachillerato", "3ero Bachillerato"};
        cursoComboBox = new JComboBox<>(cursos);

        JLabel mesaLabel = new JLabel("Número de Mesa:");
        mesaComboBox = new JComboBox<>();
        for (int i = 1; i <= numeroMesas; i++) {
            mesaComboBox.addItem(i);
        }

        candidatoCheckBox = new JCheckBox("Candidato");

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
            }
        });

        // Configurar el diseño del formulario
        setLayout(new GridLayout(6, 2, 5, 5));
        add(nombreLabel);
        add(nombreField);
        add(cedulaLabel);
        add(cedulaField);
        add(cursoLabel);
        add(cursoComboBox);
        add(mesaLabel);
        add(mesaComboBox);
        add(candidatoCheckBox);
        add(registrarButton);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void registrarEstudiante() {
        // Obtener los datos del estudiante
        String nombre = nombreField.getText();
        String cedula = cedulaField.getText();
        String curso = (String) cursoComboBox.getSelectedItem();
        int numeroMesa = (int) mesaComboBox.getSelectedItem();
        boolean esCandidato = candidatoCheckBox.isSelected();

        // Validar datos antes de registrar
        if (nombre.isEmpty() || cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Crear un array con los datos del estudiante
            String[] estudiante = {nombre, cedula, curso, String.valueOf(numeroMesa), String.valueOf(esCandidato)};

            // Agregar el estudiante a la lista
            listaEstudiantesRegistrados.add(estudiante);

            // Limpiar los campos del formulario
            nombreField.setText("");
            cedulaField.setText("");
            cursoComboBox.setSelectedIndex(0);
            mesaComboBox.setSelectedIndex(0);
            candidatoCheckBox.setSelected(false);

            JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public ArrayList<String[]> getListaEstudiantesRegistrados() {
        return listaEstudiantesRegistrados;
    }
}
