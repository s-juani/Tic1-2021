package application.entities.ent;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CalificacionEntityPK implements Serializable {
    private String operadorExperiencia;
    private String nombreExperiencia;
    private String mailTurista;

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

    @Column(name = "mail_turista")
    @Id
    public String getMailTurista() {
        return mailTurista;
    }

    public void setMailTurista(String mailTurista) {
        this.mailTurista = mailTurista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalificacionEntityPK that = (CalificacionEntityPK) o;

        if (operadorExperiencia != null ? !operadorExperiencia.equals(that.operadorExperiencia) : that.operadorExperiencia != null)
            return false;
        if (nombreExperiencia != null ? !nombreExperiencia.equals(that.nombreExperiencia) : that.nombreExperiencia != null)
            return false;
        if (mailTurista != null ? !mailTurista.equals(that.mailTurista) : that.mailTurista != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operadorExperiencia != null ? operadorExperiencia.hashCode() : 0;
        result = 31 * result + (nombreExperiencia != null ? nombreExperiencia.hashCode() : 0);
        result = 31 * result + (mailTurista != null ? mailTurista.hashCode() : 0);
        return result;
    }
}
