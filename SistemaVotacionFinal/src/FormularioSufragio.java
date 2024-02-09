import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class FormularioSufragio extends JFrame {
    private JComboBox<String> candidatosComboBox;
    private JButton votarButton;
    private List<Estudiantes> listaCandidatos;
    private Set<String> cedulasVotadas;

    public FormularioSufragio(List<Estudiantes> listaCandidatos, FormularioEstudiantes formularioEstudiantes) {
        this.listaCandidatos = listaCandidatos;
        this.cedulasVotadas = new HashSet<>();

        setTitle("Formulario de Sufragio");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Introduzca su número de cédula:");
        JTextField cedulaTextField = new JTextField();

        candidatosComboBox = new JComboBox<>();
        for (Estudiantes candidato : listaCandidatos) {
            candidatosComboBox.addItem(candidato.getNombre());
        }

        votarButton = new JButton("Votar");
        votarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaTextField.getText();
                if (cedulasVotadas.contains(cedula)) {
                    JOptionPane.showMessageDialog(FormularioSufragio.this, "Esta cédula ya ha votado.");
                } else {
                    Estudiantes estudiante = formularioEstudiantes != null ? formularioEstudiantes.buscarEstudiantePorCedula(cedula) : null;
                    if (estudiante != null) {
                        votar(estudiante);
                    } else {
                        JOptionPane.showMessageDialog(FormularioSufragio.this, "La cédula introducida no coincide con ningún registro.");
                    }
                }
            }
        });

        panel.add(label);
        panel.add(cedulaTextField);
        panel.add(candidatosComboBox);
        panel.add(votarButton);

        getContentPane().add(panel);
    }

    private void votar(Estudiantes estudiante) {
        int selectedIndex = candidatosComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            Estudiantes candidato = listaCandidatos.get(selectedIndex);
            candidato.incrementarVotos();
            cedulasVotadas.add(estudiante.getCedula());
            JOptionPane.showMessageDialog(this, "Ha votado por " + candidato.getNombre() + ".");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un candidato.");
        }
    }
}
