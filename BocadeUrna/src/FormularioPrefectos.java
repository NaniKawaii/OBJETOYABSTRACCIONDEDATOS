import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPrefectos extends JFrame {
    private JTextField nombreTextField;
    private JTextField cedulaTextField;
    private JTextField edadTextField;
    private JTextField partidoTextField;
    private JComboBox<String> provinciaComboBox;
    private GuardarResultadosListener guardarResultadosListener;

    public FormularioPrefectos() {
        setTitle("Formulario de Prefectos");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Creación de componentes
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel cedulaLabel = new JLabel("Cédula:");
        JLabel edadLabel = new JLabel("Edad:");
        JLabel partidoLabel = new JLabel("Partido Político:");
        JLabel provinciaLabel = new JLabel("Provincia:");

        nombreTextField = new JTextField();
        cedulaTextField = new JTextField();
        edadTextField = new JTextField();
        partidoTextField = new JTextField();
        provinciaComboBox = new JComboBox<>();

        JButton guardarButton = new JButton("Guardar");

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPrefecto();
            }
        });

        // Configuración de componentes
        nombreLabel.setBounds(20, 20, 80, 20);
        nombreTextField.setBounds(120, 20, 150, 20);
        cedulaLabel.setBounds(20, 50, 80, 20);
        cedulaTextField.setBounds(120, 50, 150, 20);
        edadLabel.setBounds(20, 80, 80, 20);
        edadTextField.setBounds(120, 80, 150, 20);
        partidoLabel.setBounds(20, 110, 100, 20);
        partidoTextField.setBounds(120, 110, 150, 20);
        provinciaLabel.setBounds(20, 140, 80, 20);
        provinciaComboBox.setBounds(120, 140, 150, 20);
        guardarButton.setBounds(100, 180, 100, 30);

        // Agregar opciones de prueba al JComboBox (provinciaComboBox)
        provinciaComboBox.addItem("Azuay");
        provinciaComboBox.addItem("Bolivar");
        provinciaComboBox.addItem("Cañar");
        provinciaComboBox.addItem("Carchi");
        provinciaComboBox.addItem("Chimborazo");
        provinciaComboBox.addItem("Cotopaxi");
        provinciaComboBox.addItem("El Oro");
        provinciaComboBox.addItem("Esmeraldas");
        provinciaComboBox.addItem("Galápagos");
        provinciaComboBox.addItem("Guayas");
        provinciaComboBox.addItem("Imbabura");
        provinciaComboBox.addItem("Loja");
        provinciaComboBox.addItem("Los Rios");
        provinciaComboBox.addItem("Manabi");
        provinciaComboBox.addItem("Morona Santiago");
        provinciaComboBox.addItem("Napo");
        provinciaComboBox.addItem("Orellana");
        provinciaComboBox.addItem("Pastaza");
        provinciaComboBox.addItem("Pichincha");
        provinciaComboBox.addItem("Santa Elena");
        provinciaComboBox.addItem("Santo Domingo");
        provinciaComboBox.addItem("Sucumbíos");
        provinciaComboBox.addItem("Tungurahua");
        provinciaComboBox.addItem("Zamora Chinchipe");

        // Agregar componentes al formulario
        add(nombreLabel);
        add(nombreTextField);
        add(cedulaLabel);
        add(cedulaTextField);
        add(edadLabel);
        add(edadTextField);
        add(partidoLabel);
        add(partidoTextField);
        add(provinciaLabel);
        add(provinciaComboBox);
        add(guardarButton);

        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    private void guardarPrefecto() {
        // Obtener los datos ingresados en el formulario
        String nombre = nombreTextField.getText();
        String cedula = cedulaTextField.getText();
        String edad = edadTextField.getText();
        String partido = partidoTextField.getText();
        String provincia = (String) provinciaComboBox.getSelectedItem();

        // Mostrar un mensaje con los datos ingresados (puedes ajustar según tus necesidades)
        JOptionPane.showMessageDialog(this, "Prefecto guardado:\nNombre: " + nombre +
                "\nCédula: " + cedula + "\nEdad: " + edad + "\nPartido: " + partido +
                "\nProvincia: " + provincia);

        // Notificar al listener (SistemaBocaDeUrnaUI) sobre el guardado del prefecto
        if (guardarResultadosListener != null) {
            guardarResultadosListener.guardarResultado(nombre);
            // También agregamos el nombre del prefecto a la lista simulada en SistemaBocaDeUrnaData
            SistemaBocaDeUrnaData.agregarPrefecto(nombre);
        }

        // Puedes agregar más lógica según tus necesidades, como reiniciar los campos, cerrar el formulario, etc.
        // Lógica para notificar al listener (SistemaBocaDeUrnaUI) sobre el guardado del prefecto
        if (guardarResultadosListener != null) {
            guardarResultadosListener.guardarResultado(nombre);
        }
    }

    // Setter para establecer el listener
    public void setGuardarResultadosListener(GuardarResultadosListener listener) {
        this.guardarResultadosListener = listener;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioPrefectos();
            }
        });
    }
}
