package application.entities.ent;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "operador", schema = "tic1sch")
public class OperadorEntity extends UsuarioEntity {
    private String mail;
    private Collection<FormaContactoEntity> formaContactosByMail;
    private UsuarioEntity usuarioByMail;

    @Id
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperadorEntity that = (OperadorEntity) o;

        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mail != null ? mail.hashCode() : 0;
    }

    @OneToMany(mappedBy = "operadorByMrailOpeador")
    public Collection<FormaContactoEntity> getFormaContactosByMail() {
        return formaContactosByMail;
    }

    public void setFormaContactosByMail(Collection<FormaContactoEntity> formaContactosByMail) {
        this.formaContactosByMail = formaContactosByMail;
    }

    @OneToOne
    @JoinColumn(name = "mail", referencedColumnName = "mail", nullable = false)
    public UsuarioEntity getUsuarioByMail() {
        return usuarioByMail;
    }

    public void setUsuarioByMail(UsuarioEntity usuarioByMail) {
        this.usuarioByMail = usuarioByMail;
    }
}
