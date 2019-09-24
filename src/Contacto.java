public class Contacto {
    private int id;
    private String foto;
    private String nombre;
    private String apellido;
    private String compania;
    private String posicion;
    private String email;
    private String telefono;

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", foto='" + foto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", compania='" + compania + '\'' +
                ", posicion='" + posicion + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", notas='" + notas + '\'' +
                '}';
    }

    public Contacto(int id, String foto, String nombre, String apellido, String compania, String posicion, String email, String telefono, String notas) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.compania = compania;
        this.posicion = posicion;
        this.email = email;
        this.telefono = telefono;
        this.notas = notas;
    }

    private String notas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
