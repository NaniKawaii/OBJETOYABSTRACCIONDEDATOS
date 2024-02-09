import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FormularioMesas extends JFrame {
    private List<String> listaMesas;
    private JTextArea mesasTextArea;

    public FormularioMesas() {
        setTitle("Formulario de Mesas");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modificado para no cerrar el programa al cerrar este formulario

        listaMesas = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

        JLabel mesaLabel = new JLabel("Nombre de la mesa:");
        JTextField mesaTextField = new JTextField();
        JButton agregarMesaButton = new JButton("Agregar Mesa");
        agregarMesaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarMesa(mesaTextField.getText());
            }
        });

        mesasTextArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(mesasTextArea);
        mesasTextArea.setEditable(false);

        panel.add(mesaLabel);
        panel.add(mesaTextField);
        panel.add(agregarMesaButton);
        panel.add(scrollPane);
    }

    private void agregarMesa(String nombreMesa) {
        listaMesas.add(nombreMesa);
        actualizarListaMesas();
    }

    private void actualizarListaMesas() {
        mesasTextArea.setText("");
        for (String mesa : listaMesas) {
            mesasTextArea.append(mesa + "\n");
        }
    }

    public List<String> obtenerListaMesas() {
        return listaMesas;
    }
    public List<Mesas> getMesas() {
        List<Mesas> mesas = new ArrayList<>();
        // Agrega aquí la lógica para obtener las mesas desde tu formulario
        // Por ejemplo, si tienes una lista de mesas en el formulario, podrías hacer algo como:
        // mesas.addAll(listaDeMesas);
        return mesas;
    }
}
