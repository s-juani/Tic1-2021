package application.entities.ent;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

public class ReservaEntityPK implements Serializable {
    private String mailTurista;
    private String operadorExperiencia;
    private String nombreExperiencia;
    private Date fecha;

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

        ReservaEntityPK that = (ReservaEntityPK) o;

        if (mailTurista != null ? !mailTurista.equals(that.mailTurista) : that.mailTurista != null) return false;
        if (operadorExperiencia != null ? !operadorExperiencia.equals(that.operadorExperiencia) : that.operadorExperiencia != null)
            return false;
        if (nombreExperiencia != null ? !nombreExperiencia.equals(that.nombreExperiencia) : that.nombreExperiencia != null)
            return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mailTurista != null ? mailTurista.hashCode() : 0;
        result = 31 * result + (operadorExperiencia != null ? operadorExperiencia.hashCode() : 0);
        result = 31 * result + (nombreExperiencia != null ? nombreExperiencia.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
