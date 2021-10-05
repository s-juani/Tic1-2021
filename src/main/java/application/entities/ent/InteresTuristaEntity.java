package application.entities.ent;

import javax.persistence.*;

@Entity
@Table(name = "interes_turista", schema = "tic1sch", catalog = "")
@IdClass(InteresTuristaEntityPK.class)
public class InteresTuristaEntity {
    private String nombreInteres;
    private String mailTurista;
    private InteresEntity interesByNombreInteres;

    @Id
    @Column(name = "nombre_interes")
    public String getNombreInteres() {
        return nombreInteres;
    }

    public void setNombreInteres(String nombreInteres) {
        this.nombreInteres = nombreInteres;
    }

    @Id
    @Column(name = "mail_turista")
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

        InteresTuristaEntity that = (InteresTuristaEntity) o;

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

//    @ManyToOne
//    @JoinColumn(name = "nombre_interes", referencedColumnName = "nombre", nullable = false)
//    public InteresEntity getInteresByNombreInteres() {
//        return interesByNombreInteres;
//    }
//
//    public void setInteresByNombreInteres(InteresEntity interesByNombreInteres) {
//        this.interesByNombreInteres = interesByNombreInteres;
//    }
}
