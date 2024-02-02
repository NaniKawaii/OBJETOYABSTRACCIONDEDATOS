import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelCursos extends JDialog {
    private ArrayList<String> listaCursosSeleccionados;

    public PanelCursos(JFrame parent) {
        super(parent, "Cursos", true);

        // Inicializar la lista de cursos seleccionados
        listaCursosSeleccionados = new ArrayList<>();

        // Crear componentes del panel
        JLabel label = new JLabel("Cursos a Participar:");
        JPanel buttonPanel = new JPanel();  // Panel para contener los botones

        // Configurar el diseño del panel de botones
        buttonPanel.setLayout(new GridLayout(0, 4, 5, 5));  // GridLayout con 4 columnas

        // Crear botones y añadirlos al panel de botones
        String[] cursos = {"5to Básica", "6to Básica", "7mo Básica", "8vo Básica", "9no Básica", "10mo Básica",
                "1ero Bachillerato", "2do Bachillerato", "3ero Bachillerato"};

        for (String curso : cursos) {
            JButton button = new JButton(curso);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    toggleCursoSeleccionado(curso);
                }
            });
            buttonPanel.add(button);
        }

        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Configurar el diseño del panel principal
        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(aceptarButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void toggleCursoSeleccionado(String curso) {
        if (listaCursosSeleccionados.contains(curso)) {
            listaCursosSeleccionados.remove(curso);
        } else {
            listaCursosSeleccionados.add(curso);
        }
    }

    public ArrayList<String> getListaCursosSeleccionados() {
        return listaCursosSeleccionados;
    }
}
