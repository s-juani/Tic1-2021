package application.entities.ent;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "experiencia")
@IdClass(ExperienciaEntityPK.class)
public class ExperienciaEntity {

    private String operador;

    private String nombre;
    private String descripcion;
    private String aforo;
    private boolean conReserva;

    public boolean isVacunacion() {
        return vacunacion;
    }

    public void setVacunacion(boolean vacunacion) {
        this.vacunacion = vacunacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    private int latitud;
    private int longitud;
    private Set<ImagenEntity> imagens;
    private Set<CalificacionEntity> calificaciones;
    private Set<InteresEntity> intereses;
    private Set<VideoEntity> videos;
    private Set<ReservaEntity> reservas;
    private boolean vacunacion;
    private String direccion;
//    @OneToMany(mappedBy = "experiencia")


//    @Id
//    @GeneratedValue(generator = "experiencia_id")
//    @GenericGenerator(name = "xp_id", strategy = "increment")
//    public long id;
    @Id
    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador_turistico) {
        this.operador = operador_turistico;
    }
    @Id
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAforo() {
        return aforo;
    }

    public void setAforo(String aforo) {
        this.aforo = aforo;
    }

    public boolean isConReserva() {
        return conReserva;
    }

    public void setConReserva(boolean conReserva) {
        this.conReserva = conReserva;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    @OneToMany(targetEntity = CalificacionEntity.class, fetch = FetchType.EAGER)
    @JoinColumns({@JoinColumn(insertable = false,updatable = false,name = "operador_experiencia", referencedColumnName = "operador", nullable = false), @JoinColumn(insertable = false,updatable = false,name = "nombre_experiencia", referencedColumnName = "nombre", nullable = false)})
    public Set<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(Set<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    @ManyToMany(targetEntity = InteresEntity.class,fetch = FetchType.EAGER)
    @JoinTable(name="interes_experiencia",
            joinColumns = {@JoinColumn(insertable = false,updatable = false,name = "operador_experiencia", referencedColumnName = "operador", nullable = false),
                    @JoinColumn(insertable = false,updatable = false,name = "nombre_experiencia", referencedColumnName = "nombre", nullable = false)},
            inverseJoinColumns = @JoinColumn(name = "interes", referencedColumnName = "nombre", nullable = false))
    public Set<InteresEntity> getIntereses() {
        return intereses;
    }

    public void setIntereses(Set<InteresEntity> intereses) {
        this.intereses = intereses;
    }

    @OneToMany(mappedBy = "experiencia", fetch = FetchType.EAGER)
    public Set<ImagenEntity> getImagens() {
        return imagens;
    }

    public void setImagens(Set<ImagenEntity> imagens) {
        this.imagens = imagens;
    }

    @OneToMany(mappedBy = "experiencia", fetch = FetchType.EAGER)
    public Set<VideoEntity> getVideos() {
        return videos;
    }

    public void setVideos(Set<VideoEntity> videos) {
        this.videos = videos;
    }

    @OneToMany(targetEntity = ReservaEntity.class, fetch = FetchType.EAGER)
    @JoinColumns({@JoinColumn(insertable = false,updatable = false,name = "operador_experiencia", referencedColumnName = "operador", nullable = false),
            @JoinColumn(insertable = false,updatable = false,name = "nombre_experiencia", referencedColumnName = "nombre", nullable = false)})
    public Set<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(Set<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperienciaEntity that = (ExperienciaEntity) o;

        if (conReserva != that.conReserva) return false;
        if (latitud != that.latitud) return false;
        if (longitud != that.longitud) return false;
        if (operador != null ? !operador.equals(that.operador) : that.operador != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (aforo != null ? !aforo.equals(that.aforo) : that.aforo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operador != null ? operador.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (aforo != null ? aforo.hashCode() : 0);
        result = 31 * result + (conReserva ? 1 : 0);
        result = 31 * result + latitud;
        result = 31 * result + longitud;
        return result;
    }

    @Override
    public String toString() {
        String str = "ExperienciaEntity{" +
                "operador_turistico='" + operador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", aforo='" + aforo + '\'' +
                ", conReserva=" + conReserva +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ",idsImágenes:{";
        for (ImagenEntity imagen:this.getImagens()) {
            str += "," + imagen.getIdimagen();
        }
        str += "}, intereses:{ ";
        for (InteresEntity interes: intereses){
            str += "," + interes.getNombre();
        }
        str += "}, videos:{ ";
        for (VideoEntity video: videos){
            str += "," + video.getUrl();
        }
        str += "}, calificaciones:{ ";
        for (CalificacionEntity calificacion: calificaciones){
            str += "," + calificacion;
        }
        str += "}, reservas:{ ";
        for (ReservaEntity reserva: reservas){
            str += "," + reserva;
        }
        str += "}}";
        return str;
    }


//
//    @OneToMany(mappedBy = "experiencia")
//    public Set<InteresExperienciaEntity> getInteresExperiencias() {
//        return interesExperiencias;
//    }
//
//    public void setInteresExperiencias(Set<InteresExperienciaEntity> interesExperiencias) {
//        this.interesExperiencias = interesExperiencias;
//    }
//
//    @OneToMany(mappedBy = "experiencia")
//    public Set<ReservaEntity> getReservas() {
//        return reservas;
//    }
//
//    public void setReservas(Set<ReservaEntity> reservas) {
//        this.reservas = reservas;
//    }
//
//    @OneToMany(mappedBy = "experiencia")
//    public Set<VideoEntity> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(Set<VideoEntity> videos) {
//        this.videos = videos;
//    }
}
