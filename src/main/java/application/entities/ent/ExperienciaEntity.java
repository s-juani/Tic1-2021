package application.entities.ent;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "experiencia")
@IdClass(ExperienciaEntityPK.class)
public class ExperienciaEntity {
    @Id
    private String operador_turistico;
    @Id
    private String nombre;
    private String descripcion;
    private String aforo;
    private boolean conReserva;
    private int latitud;
    private int longitud;
    @OneToMany(mappedBy = "experiencia",fetch = FetchType.EAGER)
    private Collection<ImagenEntity> imagens;
//    @OneToMany(mappedBy = "experiencia")
//    private Collection<InteresExperienciaEntity> interesExperiencias;
//    @OneToMany(mappedBy = "experiencia")
//    private Collection<ReservaEntity> reservas;
//    @OneToMany(mappedBy = "experiencia")
//    private Collection<VideoEntity> videos;

//    @Id
//    @GeneratedValue(generator = "experiencia_id")
//    @GenericGenerator(name = "xp_id", strategy = "increment")
//    public long id;

    public String getOperador_turistico() {
        return operador_turistico;
    }

    public void setOperador_turistico(String operador_turistico) {
        this.operador_turistico = operador_turistico;
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
        if (operador_turistico != null ? !operador_turistico.equals(that.operador_turistico) : that.operador_turistico != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (aforo != null ? !aforo.equals(that.aforo) : that.aforo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operador_turistico != null ? operador_turistico.hashCode() : 0;
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
        return "ExperienciaEntity{" +
                "operador_turistico='" + operador_turistico + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", aforo='" + aforo + '\'' +
                ", conReserva=" + conReserva +
                ", latitud=" + latitud +
                ", longitud=" + longitud + '}';
    }

    //    @OneToMany(mappedBy = "experiencia")
    public Collection<ImagenEntity> getImagens() {
        return imagens;
    }

    public void setImagens(Collection<ImagenEntity> imagens) {
        this.imagens = imagens;
    }
//
//    @OneToMany(mappedBy = "experiencia")
//    public Collection<InteresExperienciaEntity> getInteresExperiencias() {
//        return interesExperiencias;
//    }
//
//    public void setInteresExperiencias(Collection<InteresExperienciaEntity> interesExperiencias) {
//        this.interesExperiencias = interesExperiencias;
//    }
//
//    @OneToMany(mappedBy = "experiencia")
//    public Collection<ReservaEntity> getReservas() {
//        return reservas;
//    }
//
//    public void setReservas(Collection<ReservaEntity> reservas) {
//        this.reservas = reservas;
//    }
//
//    @OneToMany(mappedBy = "experiencia")
//    public Collection<VideoEntity> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(Collection<VideoEntity> videos) {
//        this.videos = videos;
//    }
}
