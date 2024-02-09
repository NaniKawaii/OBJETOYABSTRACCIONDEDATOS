import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FormularioCursos extends JFrame {
    private List<String> listaCursos;
    private JTextArea cursosTextArea;

    public FormularioCursos() {
        setTitle("Formulario de Cursos");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modificado para no cerrar el programa al cerrar este formulario

        listaCursos = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

        JLabel cursoLabel = new JLabel("Nombre del curso:");
        JTextField cursoTextField = new JTextField();
        JButton agregarCursoButton = new JButton("Agregar Curso");
        agregarCursoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarCurso(cursoTextField.getText());
            }
        });

        cursosTextArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(cursosTextArea);
        cursosTextArea.setEditable(false);

        panel.add(cursoLabel);
        panel.add(cursoTextField);
        panel.add(agregarCursoButton);
        panel.add(scrollPane);
    }

    private void agregarCurso(String nombreCurso) {
        listaCursos.add(nombreCurso);
        actualizarListaCursos();
    }

    private void actualizarListaCursos() {
        cursosTextArea.setText("");
        for (String curso : listaCursos) {
            cursosTextArea.append(curso + "\n");
        }
    }

    public List<String> obtenerListaCursos() {
        return listaCursos;
    }
}
