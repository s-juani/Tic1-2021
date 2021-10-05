package application.entities.ent;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "calificacion", schema = "tic1sch")
@IdClass(CalificacionEntityPK.class)
public class CalificacionEntity {
    private String operadorExperiencia;
    private String nombreExperiencia;
    private String mailTurista;
    private byte puntaje;
    private String comentario;
    private Timestamp fechaHora;

    @Id
    @Column(name = "operador_experiencia")
    public String getOperadorExperiencia() {
        return operadorExperiencia;
    }

    public void setOperadorExperiencia(String operadorExperiencia) {
        this.operadorExperiencia = operadorExperiencia;
    }

    @Id
    @Column(name = "nombre_experiencia")
    public String getNombreExperiencia() {
        return nombreExperiencia;
    }

    public void setNombreExperiencia(String nombreExperiencia) {
        this.nombreExperiencia = nombreExperiencia;
    }

    @Id
    @Column(name = "mail_turista")
    public String getMailTurista() {
        return mailTurista;
    }

    public void setMailTurista(String mailTurista) {
        this.mailTurista = mailTurista;
    }

    @Basic
    @Column(name = "puntaje")
    public byte getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(byte puntaje) {
        this.puntaje = puntaje;
    }

    @Basic
    @Column(name = "comentario")
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Basic
    @Column(name = "fecha_hora")
    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalificacionEntity that = (CalificacionEntity) o;

        if (puntaje != that.puntaje) return false;
        if (operadorExperiencia != null ? !operadorExperiencia.equals(that.operadorExperiencia) : that.operadorExperiencia != null)
            return false;
        if (nombreExperiencia != null ? !nombreExperiencia.equals(that.nombreExperiencia) : that.nombreExperiencia != null)
            return false;
        if (mailTurista != null ? !mailTurista.equals(that.mailTurista) : that.mailTurista != null) return false;
        if (comentario != null ? !comentario.equals(that.comentario) : that.comentario != null) return false;
        if (fechaHora != null ? !fechaHora.equals(that.fechaHora) : that.fechaHora != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operadorExperiencia != null ? operadorExperiencia.hashCode() : 0;
        result = 31 * result + (nombreExperiencia != null ? nombreExperiencia.hashCode() : 0);
        result = 31 * result + (mailTurista != null ? mailTurista.hashCode() : 0);
        result = 31 * result + (int) puntaje;
        result = 31 * result + (comentario != null ? comentario.hashCode() : 0);
        result = 31 * result + (fechaHora != null ? fechaHora.hashCode() : 0);
        return result;
    }
}
