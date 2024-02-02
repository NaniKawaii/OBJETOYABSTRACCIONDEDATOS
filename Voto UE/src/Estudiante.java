public class Estudiante {
    private int id;
    private String nombre;
    private Curso curso;

    public Estudiante(int id, String nombre, Curso curso) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getNombreCompleto() {
        return nombre + " - " + curso.getNombre();
    }
}