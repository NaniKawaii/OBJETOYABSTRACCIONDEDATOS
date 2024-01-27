import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FormularioBocaDeUrna extends JFrame {
    private JComboBox<String> prefectosComboBox;
    private JTextField ciudadTextField; // Nuevo campo para la ciudad
    private VotarListener votarListener;

    public FormularioBocaDeUrna(Map<String, Integer> resultadosGenerales, String[] strings) {
        setTitle("Boca de Urna");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Seleccione un Prefecto:");
        label.setBounds(20, 20, 150, 20);

        prefectosComboBox = new JComboBox<>();
        prefectosComboBox.setBounds(180, 20, 100, 20);

        JLabel ciudadLabel = new JLabel("Ciudad:");
        ciudadLabel.setBounds(20, 50, 150, 20);

        ciudadTextField = new JTextField();
        ciudadTextField.setBounds(180, 50, 100, 20);

        JButton votarButton = new JButton("Votar");
        votarButton.setBounds(100, 80, 100, 30);

        votarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                votar();
            }
        });

        add(label);
        add(prefectosComboBox);
        add(ciudadLabel);
        add(ciudadTextField);
        add(votarButton);

        // Llenar el ComboBox con los nombres de los prefectos
        for (String prefecto : SistemaBocaDeUrnaData.obtenerNombresPrefectos()) {
            prefectosComboBox.addItem(prefecto);
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void votar() {
        String nombrePrefectoSeleccionado = (String) prefectosComboBox.getSelectedItem();
        String ciudad = ciudadTextField.getText(); // Obtener la ciudad ingresada por el usuario

        if (nombrePrefectoSeleccionado != null && !ciudad.isEmpty()) {
            // Lógica para votar por el prefecto seleccionado
            JOptionPane.showMessageDialog(this, "Voto registrado para: " + nombrePrefectoSeleccionado +
                    "\nCiudad: " + ciudad);
            // Notificar al listener sobre el voto
            if (votarListener != null) {
                votarListener.votarPorPrefecto(nombrePrefectoSeleccionado, ciudad);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un prefecto y escriba su ciudad para votar.");
        }
    }

    // Setter para establecer el listener
    public void setVotarListener(VotarListener listener) {
        this.votarListener = listener;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        Map<String, Integer> resultadosGenerales = new HashMap<>();
        resultadosGenerales.put("Prefecto 1", 0);
        resultadosGenerales.put("Prefecto 2", 0);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioBocaDeUrna(resultadosGenerales, SistemaBocaDeUrnaData.obtenerNombresPrefectos());
            }
        });
    }
}
