import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SistemaBocaDeUrnaUI {
    private JFrame frame;
    private JComboBox<String> prefectosComboBox;
    private Map<String, Integer> resultadosGenerales;

    public SistemaBocaDeUrnaUI() {
        frame = new JFrame("Sistema Boca de Urna");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu archivoMenu = new JMenu("Archivo");
        JMenuItem salirMenuItem = new JMenuItem("Salir");
        JMenu candidatosMenu = new JMenu("Candidatos");
        JMenuItem prefectosMenuItem = new JMenuItem("Prefectos");
        JMenu procesoMenu = new JMenu("Proceso");
        JMenuItem bocaDeUrnaMenuItem = new JMenuItem("Boca de Urna");
        JMenu reportesMenu = new JMenu("Reportes");
        JMenuItem resultadosGeneralesMenuItem = new JMenuItem("Resultados Generales");
        JMenuItem resultadosCantonCiudadMenuItem = new JMenuItem("Resultados por Ciudades");


        prefectosComboBox = new JComboBox<>();

        resultadosGenerales = new HashMap<>();

        salirMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        prefectosMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioPrefectos();
            }
        });

        bocaDeUrnaMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioBocaDeUrna();
            }
        });

        resultadosGeneralesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarResultadosGenerales();
            }
        });

        resultadosCantonCiudadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarResultadosPorCiudad();
            }
        });

        reportesMenu.add(resultadosGeneralesMenuItem);
        reportesMenu.add(resultadosCantonCiudadMenuItem);

        archivoMenu.add(salirMenuItem);
        candidatosMenu.add(prefectosMenuItem);
        procesoMenu.add(bocaDeUrnaMenuItem);
        reportesMenu.add(resultadosGeneralesMenuItem);

        menuBar.add(archivoMenu);
        menuBar.add(candidatosMenu);
        menuBar.add(procesoMenu);
        menuBar.add(reportesMenu);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    private void mostrarFormularioPrefectos() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioPrefectos formularioPrefectos = new FormularioPrefectos();
                formularioPrefectos.setGuardarResultadosListener(new GuardarResultadosListener() {
                    @Override
                    public void guardarResultado(String nombrePrefecto) {
                        JOptionPane.showMessageDialog(frame, "Prefecto guardado: " + nombrePrefecto);
                        actualizarComboBoxPrefectos();
                    }
                });
            }
        });
    }

    private void mostrarFormularioBocaDeUrna() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioBocaDeUrna formularioBocaDeUrna = new FormularioBocaDeUrna(resultadosGenerales, SistemaBocaDeUrnaData.obtenerNombresPrefectos());
                formularioBocaDeUrna.setVotarListener(new VotarListener() {
                    @Override
                    public void votarPorPrefecto(String nombrePrefecto, String ciudad) {
                        actualizarResultadosGenerales(nombrePrefecto);
                    }
                });
            }
        });
    }

    private void actualizarComboBoxPrefectos() {
        prefectosComboBox.removeAllItems();
        // Obtener los nombres de los prefectos desde la lista simulada
        for (String nombrePrefecto : SistemaBocaDeUrnaData.obtenerNombresPrefectos()) {
            prefectosComboBox.addItem(nombrePrefecto);
        }
    }

    private void actualizarResultadosGenerales(String nombrePrefecto) {
        resultadosGenerales.put(nombrePrefecto, resultadosGenerales.getOrDefault(nombrePrefecto, 0) + 1);
        JOptionPane.showMessageDialog(frame, "Resultados generales actualizados.");
    }

    private void mostrarResultadosGenerales() {
        // Crear un panel para contener las barras de progreso
        JPanel panel = new JPanel();

        // Iterar sobre los resultados y agregar barras de progreso para cada prefecto
        for (Map.Entry<String, Integer> entry : resultadosGenerales.entrySet()) {
            String nombrePrefecto = entry.getKey();
            Integer votos = entry.getValue();

            // Crear una etiqueta para mostrar el nombre del prefecto
            JLabel label = new JLabel(nombrePrefecto);

            // Crear una barra de progreso y establecer su valor según los votos
            JProgressBar progressBar = new JProgressBar(0, 100); // El rango puede ajustarse según tus necesidades
            progressBar.setValue(votos);

            // Configurar la barra de progreso para que sea visible solo si hay votos
            progressBar.setStringPainted(true);
            progressBar.setVisible(votos > 0);

            // Agregar la etiqueta y la barra de progreso al panel
            panel.add(label);
            panel.add(progressBar);
        }

        // Mostrar el panel en un cuadro de diálogo
        JOptionPane.showMessageDialog(frame, panel, "Resultados Generales", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarResultadosPorCiudad() {
        // Obtener la ciudad del usuario (puedes solicitarla mediante un cuadro de diálogo)
        String ciudad = JOptionPane.showInputDialog(frame, "Ingrese su ciudad:");

        // Crear el formulario y mostrar los resultados por ciudad
        new FormularioResultadosPorCiudad(resultadosGenerales, ciudad);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SistemaBocaDeUrnaUI();
            }
        });
    }
}
