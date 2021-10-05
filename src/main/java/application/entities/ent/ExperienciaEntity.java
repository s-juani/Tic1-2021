package application.entities.ent;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "experiencia", schema = "tic1sch")
@IdClass(ExperienciaEntityPK.class)
public class ExperienciaEntity {
    private String operadorTuristico;
    private String nombre;
    private String descripcion;
    private String aforo;
    private boolean conReserva;
    private int latitud;
    private int longitud;
    private Collection<ImagenEntity> imagens;
    private Collection<InteresExperienciaEntity> interesExperiencias;
    private Collection<ReservaEntity> reservas;
    private Collection<VideoEntity> videos;

    @Id
    @GeneratedValue(generator = "experiencia_id")
    @GenericGenerator(name = "xp_id", strategy = "increment")
    public long id;

    public String getOperadorTuristico() {
        return operadorTuristico;
    }

    public void setOperadorTuristico(String operadorTuristico) {
        this.operadorTuristico = operadorTuristico;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperienciaEntity that = (ExperienciaEntity) o;

        if (conReserva != that.conReserva) return false;
        if (latitud != that.latitud) return false;
        if (longitud != that.longitud) return false;
        if (operadorTuristico != null ? !operadorTuristico.equals(that.operadorTuristico) : that.operadorTuristico != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (aforo != null ? !aforo.equals(that.aforo) : that.aforo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operadorTuristico != null ? operadorTuristico.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (aforo != null ? aforo.hashCode() : 0);
        result = 31 * result + (conReserva ? 1 : 0);
        result = 31 * result + latitud;
        result = 31 * result + longitud;
        return result;
    }

    @OneToMany(mappedBy = "experiencia")
    public Collection<ImagenEntity> getImagens() {
        return imagens;
    }

    public void setImagens(Collection<ImagenEntity> imagens) {
        this.imagens = imagens;
    }

    @OneToMany(mappedBy = "experiencia")
    public Collection<InteresExperienciaEntity> getInteresExperiencias() {
        return interesExperiencias;
    }

    public void setInteresExperiencias(Collection<InteresExperienciaEntity> interesExperiencias) {
        this.interesExperiencias = interesExperiencias;
    }

    @OneToMany(mappedBy = "experiencia")
    public Collection<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(Collection<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    @OneToMany(mappedBy = "experiencia")
    public Collection<VideoEntity> getVideos() {
        return videos;
    }

    public void setVideos(Collection<VideoEntity> videos) {
        this.videos = videos;
    }
}
