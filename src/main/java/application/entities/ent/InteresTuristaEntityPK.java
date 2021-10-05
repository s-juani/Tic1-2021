package application.entities.ent;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class InteresTuristaEntityPK implements Serializable {
    private String nombreInteres;
    private String mailTurista;

    @Column(name = "nombre_interes")
    @Id
    public String getNombreInteres() {
        return nombreInteres;
    }

    public void setNombreInteres(String nombreInteres) {
        this.nombreInteres = nombreInteres;
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

        InteresTuristaEntityPK that = (InteresTuristaEntityPK) o;

        if (nombreInteres != null ? !nombreInteres.equals(that.nombreInteres) : that.nombreInteres != null)
            return false;
        if (mailTurista != null ? !mailTurista.equals(that.mailTurista) : that.mailTurista != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nombreInteres != null ? nombreInteres.hashCode() : 0;
        result = 31 * result + (mailTurista != null ? mailTurista.hashCode() : 0);
        return result;
    }
}
