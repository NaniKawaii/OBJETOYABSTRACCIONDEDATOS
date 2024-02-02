public class Candidato {
    private int id;
    private String nombre;
    private String propuesta;

    public Candidato(int id, String nombre, String propuesta) {
        this.id = id;
        this.nombre = nombre;
        this.propuesta = propuesta;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPropuesta() {
        return propuesta;
    }
}