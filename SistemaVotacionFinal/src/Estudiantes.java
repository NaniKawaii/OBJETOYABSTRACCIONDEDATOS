public class Estudiantes {
    private String nombre;
    private String cedula;
    private String cursoSeleccionado;
    private String mesaSeleccionada;
    private boolean esCandidato;
    private int votos;

    public Estudiantes(String nombre, String cedula, String cursoSeleccionado, String mesaSeleccionada, boolean esCandidato) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cursoSeleccionado = cursoSeleccionado;
        this.mesaSeleccionada = mesaSeleccionada;
        this.esCandidato = esCandidato;
        this.votos = 0;
    }

    public String getNombre() {
        return nombre;
    }


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public String getMesaSeleccionada() {
        return mesaSeleccionada;
    }


    public boolean isEsCandidato() {
        return esCandidato;
    }


    public void incrementarVotos() {
        votos++;
    }

    public int getVotos() {
        return votos;
    }
}
