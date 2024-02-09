import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaVotacion extends JFrame {
    private FormularioCursos formularioCursos;
    private FormularioMesas formularioMesas;
    private FormularioEstudiantes formularioEstudiantes;
    private Candidatos candidatos;
    private FormularioSufragio formularioSufragio;

    public SistemaVotacion() {
        setTitle("Sistema de Votación");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu archivoMenu = new JMenu("Archivo");
        JMenuItem salirMenuItem = new JMenuItem("Salir");
        salirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        archivoMenu.add(salirMenuItem);

        // Menú Administración
        JMenu administracionMenu = new JMenu("Administración");
        JMenuItem mesasMenuItem = new JMenuItem("Mesas");
        JMenuItem cursosMenuItem = new JMenuItem("Cursos");
        JMenuItem estudiantesMenuItem = new JMenuItem("Estudiantes");
        JMenuItem candidatosMenuItem = new JMenuItem("Candidatos");
        administracionMenu.add(mesasMenuItem);
        administracionMenu.add(cursosMenuItem);
        administracionMenu.add(estudiantesMenuItem);
        administracionMenu.add(candidatosMenuItem);

        // Instanciar FormularioCursos al seleccionar la opción de menú "Cursos"
        formularioCursos = new FormularioCursos();
        cursosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formularioCursos.setVisible(true);
            }
        });

        // Instanciar FormularioMesas al seleccionar la opción de menú "Mesas"
        formularioMesas = new FormularioMesas();
        mesasMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formularioMesas.setVisible(true);
            }
        });

        // Instanciar FormularioEstudiantes al seleccionar la opción de menú "Estudiantes"
        estudiantesMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (formularioEstudiantes == null) {
                    formularioEstudiantes = new FormularioEstudiantes(formularioCursos.obtenerListaCursos(), formularioMesas.obtenerListaMesas());
                }
                formularioEstudiantes.setVisible(true);
            }
        });

        // Instanciar FormularioCandidatos al seleccionar la opción de menú "Candidatos"
        candidatosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (formularioEstudiantes != null) {
                    List<Estudiantes> listaEstudiantes = formularioEstudiantes.getEstudiantes();
                    FormularioCandidatos formularioCandidatos = new FormularioCandidatos(listaEstudiantes);
                    formularioCandidatos.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(SistemaVotacion.this, "Primero debe abrir el formulario de estudiantes.");
                }
            }
        });

        // Inicializar la lista de candidatos
        candidatos = new Candidatos();

        // Menú Proceso
        JMenu procesoMenu = new JMenu("Proceso");
        JMenuItem sufragarMenuItem = new JMenuItem("Sufragar");
        sufragarMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormularioSufragio();
            }
        });
        procesoMenu.add(sufragarMenuItem);

        // Menú Reportes
        JMenu reportesMenu = new JMenu("Reportes");
        JMenuItem padronElectoralMenuItem = new JMenuItem("Padrón electoral");
        JMenuItem resultadosGeneralesMenuItem = new JMenuItem("Resultados generales");
        JMenuItem resultadosPorMesasMenuItem = new JMenuItem("Resultados por mesas");
        JMenuItem resultadosGeneralesGraficoMenuItem = new JMenuItem("Resultados generales en gráfico de barras");
        reportesMenu.add(padronElectoralMenuItem);
        reportesMenu.add(resultadosGeneralesMenuItem);
        reportesMenu.add(resultadosPorMesasMenuItem);
        reportesMenu.add(resultadosGeneralesGraficoMenuItem);

        menuBar.add(archivoMenu);
        menuBar.add(administracionMenu);
        menuBar.add(procesoMenu);
        menuBar.add(reportesMenu);

        setJMenuBar(menuBar);

        padronElectoralMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (formularioEstudiantes != null) {
                    List<Estudiantes> listaEstudiantes = formularioEstudiantes.getEstudiantes();
                    FormularioReportePatronal formularioReportePatronal = new FormularioReportePatronal(listaEstudiantes);
                    formularioReportePatronal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(SistemaVotacion.this, "Primero debe abrir el formulario de estudiantes.");
                }
            }
        });

        resultadosGeneralesMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (formularioEstudiantes != null) {
                    List<Estudiantes> listaEstudiantes = formularioEstudiantes.getEstudiantes();
                    FormularioResultGen formularioResultGen = new FormularioResultGen(listaEstudiantes);
                    formularioResultGen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(SistemaVotacion.this, "Primero debe abrir el formulario de estudiantes.");
                }
            }
        });

        resultadosPorMesasMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (formularioEstudiantes != null && formularioSufragio != null) {
                    List<Estudiantes> listaEstudiantes = formularioEstudiantes.getEstudiantes();
                    List<Mesas> listaMesas = formularioMesas.getMesas();
                    FormularioResultMesas formularioResultMesas = new FormularioResultMesas(listaMesas, listaEstudiantes, formularioSufragio);
                    formularioResultMesas.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(SistemaVotacion.this, "Primero debe abrir el formulario de estudiantes y sufragar.");
                }
            }
        });

       resultadosGeneralesGraficoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (formularioEstudiantes != null) {
                    List<Estudiantes> listaEstudiantes = formularioEstudiantes.getEstudiantes();
                    FormularioGrafico formularioGrafico = new FormularioGrafico(listaEstudiantes);
                    formularioGrafico.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(SistemaVotacion.this, "Primero debe abrir el formulario de estudiantes.");
                }
            }
        });
    }

    private void abrirFormularioSufragio() {
        if (formularioEstudiantes != null) {
            List<Estudiantes> listaEstudiantes = formularioEstudiantes.getEstudiantes();
            if (!listaEstudiantes.isEmpty()) {
                List<Estudiantes> listaCandidatos = new ArrayList<>();
                for (Estudiantes estudiante : listaEstudiantes) {
                    if (estudiante.isEsCandidato()) {
                        listaCandidatos.add(estudiante);
                    }
                }
                if (!listaCandidatos.isEmpty()) {
                    // Aquí se proporcionan ambos parámetros al constructor de FormularioSufragio
                    formularioSufragio = new FormularioSufragio(listaCandidatos, formularioEstudiantes);
                    formularioSufragio.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay candidatos registrados.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No hay estudiantes registrados.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Primero debe abrir el formulario de estudiantes.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SistemaVotacion().setVisible(true);
            }
        });
    }
}
