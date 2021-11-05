package application.entities.ent;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

public class ReservaEntityPK implements Serializable {
    private String mailTurista;
    private String operadorExperiencia;
    private String nombreExperiencia;
    private Date fechaInicio;

    @Column(name = "mail_turista")
    @Id
    public String getMailTurista() {
        return mailTurista;
    }

    public void setMailTurista(String mailTurista) {
        this.mailTurista = mailTurista;
    }

    @Column(name = "operador_experiencia")
    @Id
    public String getOperadorExperiencia() {
        return operadorExperiencia;
    }

    public void setOperadorExperiencia(String operadorExperiencia) {
        this.operadorExperiencia = operadorExperiencia;
    }

    @Column(name = "nombre_experiencia")
    @Id
    public String getNombreExperiencia() {
        return nombreExperiencia;
    }

    public void setNombreExperiencia(String nombreExperiencia) {
        this.nombreExperiencia = nombreExperiencia;
    }

    @Column(name = "fecha")
    @Id
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fecha) {
        this.fechaInicio = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservaEntityPK that = (ReservaEntityPK) o;

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
}
