package application.entities.ent;

import javax.persistence.*;
import java.util.Collection;

@Entity
@PrimaryKeyJoinColumn(name="mail")
@Table(name = "operador")
public class OperadorEntity extends UsuarioEntity {
    private String formaContactosByMail;
    //private UsuarioEntity usuarioByMail;

    public OperadorEntity(String mail, String usuario, String pw, String formaContactosByMail){ //, UsuarioEntity usuarioByMail) {
        super(mail, usuario, pw);
        this.formaContactosByMail = formaContactosByMail;
        //this.usuarioByMail = usuarioByMail;
    }

    public String getFormaContactosByMail() {
        return formaContactosByMail;
    }

    public void setFormaContactosByMail(String formaContactosByMail) {
        this.formaContactosByMail = formaContactosByMail;
    }

    public OperadorEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperadorEntity that = (OperadorEntity) o;

        if (this.getMail() != null ? !this.getMail().equals(that.getMail()) : that.getMail() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return this.getMail() != null ? this.getMail().hashCode() : 0;
    }

//    @OneToMany(mappedBy = "operadorByMrailOpeador")
//    public Collection<FormaContactoEntity> getFormaContactosByMail() {
//        return formaContactosByMail;
//    }
//
//    public void setFormaContactosByMail(Collection<FormaContactoEntity> formaContactosByMail) {
//        this.formaContactosByMail = formaContactosByMail;
//    }

    /*@OneToOne
    @JoinColumn(name = "mail", referencedColumnName = "mail", nullable = false)
    public UsuarioEntity getUsuarioByMail() {
        return usuarioByMail;
    }

    public void setUsuarioByMail(UsuarioEntity usuarioByMail) {
        this.usuarioByMail = usuarioByMail;
    }*/
}
