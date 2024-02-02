import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMesas extends JDialog {
    private int numeroMesas;

    public PanelMesas(JFrame parent) {
        super(parent, "Configurar Mesas", true);

        // Crear componentes del panel
        JLabel label = new JLabel("Ingrese el número de mesas:");
        JTextField textField = new JTextField(10);
        JButton aceptarButton = new JButton("Aceptar");

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    numeroMesas = Integer.parseInt(textField.getText());
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PanelMesas.this, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Configurar el diseño del panel
        setLayout(new FlowLayout());
        add(label);
        add(textField);
        add(aceptarButton);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    public int getNumeroMesas() {
        return numeroMesas;
    }
}
