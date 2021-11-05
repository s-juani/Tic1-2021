package application.entities.ent;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@IdClass(ReservaEntityPK.class)
public class ReservaEntity {
    private String mailTurista;
    private String operadorExperiencia;
    private String nombreExperiencia;
    private Date fecha;
//    private ExperienciaEntity experiencia;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Id
    @Column(name = "mail_turista")
    public String getMailTurista() {
        return mailTurista;
    }

    public void setMailTurista(String mailTurista) {
        this.mailTurista = mailTurista;
    }

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
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservaEntity that = (ReservaEntity) o;

        if (mailTurista != null ? !mailTurista.equals(that.mailTurista) : that.mailTurista != null) return false;
        if (operadorExperiencia != null ? !operadorExperiencia.equals(that.operadorExperiencia) : that.operadorExperiencia != null)
            return false;
        if (nombreExperiencia != null ? !nombreExperiencia.equals(that.nombreExperiencia) : that.nombreExperiencia != null)
            return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mailTurista != null ? mailTurista.hashCode() : 0;
        result = 31 * result + (operadorExperiencia != null ? operadorExperiencia.hashCode() : 0);
        result = 31 * result + (nombreExperiencia != null ? nombreExperiencia.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumns({@JoinColumn(name = "operador_experiencia", referencedColumnName = "operador_turistico", nullable = false), @JoinColumn(name = "nombre_experiencia", referencedColumnName = "nombre", nullable = false)})
//    public ExperienciaEntity getExperiencia() {
//        return experiencia;
//    }
//
//    public void setExperiencia(ExperienciaEntity experiencia) {
//        this.experiencia = experiencia;
//    }
}
