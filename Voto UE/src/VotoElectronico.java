import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VotoElectronico extends JFrame {
    private ArrayList<Mesa> listaMesas;
    private ArrayList<Curso> listaCursos;
    private ArrayList<Candidato> listaCandidatos;
    private ArrayList<String[]> listaEstudiantesRegistrados;

    public VotoElectronico() {
        // Inicializar listas y cargar datos
        listaMesas = new ArrayList<>();
        listaCursos = new ArrayList<>();
        listaEstudiantesRegistrados = new ArrayList<>();
        listaCandidatos = new ArrayList<>();
        cargarDatos();

        // Configurar la interfaz gráfica
        setTitle("Sistema de Voto Electrónico 1.0.0");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear menú y submenús
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Cambiar el color de fondo de la barra superior
        menuBar.setBackground(new Color(0xC9FF45));

        // Crear estilos para el texto de los elementos del menú
        Font menuFont = new Font("Calibri", Font.PLAIN, 18);
        UIManager.put("Menu.font", menuFont);

        // Crear estilo para los elementos de menú
        Font menuItemFont = new Font("Calibri", Font.BOLD, 14);

        // Configuración del menú "Archivo"
        JMenu archivoMenu = new JMenu("Archivo");
        archivoMenu.setFont(menuFont);
        JMenuItem salirMenuItem = new JMenuItem("Salir");
        salirMenuItem.setFont(menuItemFont);
        salirMenuItem.setForeground(Color.BLACK);
        salirMenuItem.setBackground(new Color(0x45FFB0));
        salirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        archivoMenu.add(salirMenuItem);

        // Configuración del menú "Administración"
        JMenu adminMenu = new JMenu("Administración");
        adminMenu.setFont(menuFont);
        JMenuItem mesasMenuItem = new JMenuItem("Mesas");
        mesasMenuItem.setFont(menuItemFont);
        mesasMenuItem.setForeground(Color.BLACK);
        mesasMenuItem.setBackground(new Color(0xFF9A45));
        mesasMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelMesas();
            }
        });
        adminMenu.add(mesasMenuItem);

        JMenuItem cursosMenuItem = new JMenuItem("Cursos");
        cursosMenuItem.setFont(menuItemFont);
        cursosMenuItem.setForeground(Color.BLACK);
        cursosMenuItem.setBackground(new Color(0xFF9A45));
        cursosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelCursos();
            }
        });
        adminMenu.add(cursosMenuItem);

        JMenuItem estudiantesMenuItem = new JMenuItem("Estudiantes");
        estudiantesMenuItem.setFont(menuItemFont);
        estudiantesMenuItem.setForeground(Color.BLACK);
        estudiantesMenuItem.setBackground(new Color(0xFF9A45));
        estudiantesMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioEstudiantes();
            }
        });
        adminMenu.add(estudiantesMenuItem);

        JMenuItem candidatosMenuItem = new JMenuItem("Candidatos");
        candidatosMenuItem.setFont(menuItemFont);
        candidatosMenuItem.setForeground(Color.BLACK);
        candidatosMenuItem.setBackground(new Color(0xFF9A45));
        candidatosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelCandidatos();
            }
        });
        adminMenu.add(candidatosMenuItem);

        // Configuración del menú "Reportes"
        JMenu reportesMenu = new JMenu("Reportes");
        reportesMenu.setFont(menuFont);

        JMenuItem padronMenuItem = new JMenuItem("Padrón Electoral");
        padronMenuItem.setFont(menuItemFont);
        padronMenuItem.setForeground(Color.BLACK);
        padronMenuItem.setBackground(new Color(0xFFE045));
        padronMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPadronElectoral();
            }
        });
        reportesMenu.add(padronMenuItem);


        // Agregar menús a la barra de menú
        menuBar.add(archivoMenu);
        menuBar.add(adminMenu);
        menuBar.add(reportesMenu);
    }

    private void mostrarPanelMesas() {
        PanelMesas panelMesas = new PanelMesas(this);
        panelMesas.setVisible(true);
    }

    private void mostrarPanelCursos() {
        PanelCursos panelCursos = new PanelCursos(this);
        panelCursos.setVisible(true);
    }

    private void mostrarFormularioEstudiantes() {
        // Obtener el número de mesas ingresado en PanelMesas
        int numeroMesas = obtenerNumeroMesas();

        // Mostrar el formulario de estudiantes con el número de mesas
        FormularioEstudiantes formularioEstudiantes = new FormularioEstudiantes(this, numeroMesas);
        formularioEstudiantes.setVisible(true);

        // Obtener los datos de estudiantes registrados del formulario
        ArrayList<String[]> estudiantesRegistrados = formularioEstudiantes.getListaEstudiantesRegistrados();

        // Actualizar la lista de estudiantes registrados
        if (!estudiantesRegistrados.isEmpty()) {
            listaEstudiantesRegistrados.addAll(estudiantesRegistrados);
            actualizarTablaEstudiantes();
        }
    }

    private void mostrarPanelCandidatos() {
        PanelCandidatos panelCandidatos = new PanelCandidatos(this, listaEstudiantesRegistrados);
        panelCandidatos.setVisible(true);
    }

    private void mostrarPadronElectoral() {
        // Crear un nuevo modelo de tabla para el padrón electoral
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Cédula");
        model.addColumn("Curso");
        model.addColumn("Número de Mesa");
        model.addColumn("Candidato");

        // Agregar filas a la tabla con los estudiantes registrados
        for (String[] estudiante : listaEstudiantesRegistrados) {
            model.addRow(estudiante);
        }

        // Crear la tabla y mostrarla en un cuadro de diálogo con filtro por número de mesa
        JTable table = new JTable(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem filterMenuItem = new JMenuItem("Filtrar por Número de Mesa");
        filterMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mesa = JOptionPane.showInputDialog(VotoElectronico.this, "Ingrese el número de mesa a filtrar:", "Filtrar por Número de Mesa", JOptionPane.QUESTION_MESSAGE);
                try {
                    int mesaFilter = Integer.parseInt(mesa);
                    sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, mesaFilter, 3));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VotoElectronico.this, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        popupMenu.add(filterMenuItem);
        table.setComponentPopupMenu(popupMenu);

        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(this, scrollPane, "Padrón Electoral", JOptionPane.PLAIN_MESSAGE);
    }


    private void cargarDatos() {
        // Lógica para cargar datos de mesas, cursos, estudiantes y candidatos
        // ...

        // Ejemplo de creación de mesas y cursos
        for (int i = 1; i <= 5; i++) {
            listaMesas.add(new Mesa(i));
        }

        listaCursos.add(new Curso("5to Básica"));
        listaCursos.add(new Curso("6to Básica"));
        listaCursos.add(new Curso("7mo Básica"));
        // ... Agregar más cursos si es necesario
    }

    private void actualizarTablaEstudiantes() {
        // Actualizar la tabla de estudiantes si es necesario
        // Por ejemplo, si hay una tabla en la interfaz gráfica
        // ...

        // Este es un ejemplo básico de cómo podría ser la actualización de la tabla
        // Debes adaptarlo según la estructura específica de tu GUI
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Cédula");
        model.addColumn("Curso");
        model.addColumn("Número de Mesa");
        model.addColumn("Candidato");

        for (String[] estudiante : listaEstudiantesRegistrados) {
            model.addRow(estudiante);
        }

        // Supongamos que tienes un JTable llamado 'tablaEstudiantes' en tu interfaz gráfica
        // Ajusta esto según la estructura de tu interfaz
        // tablaEstudiantes.setModel(model);
    }

    private int obtenerNumeroMesas() {
        PanelMesas panelMesas = new PanelMesas(this);
        panelMesas.setVisible(true);
        return panelMesas.getNumeroMesas();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VotoElectronico().setVisible(true);
        });
    }
}
