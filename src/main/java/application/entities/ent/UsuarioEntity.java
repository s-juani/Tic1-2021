package application.entities.ent;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class UsuarioEntity {
    private String mail;
    private String usuario;
    private String pw;

    public UsuarioEntity(){}

    public UsuarioEntity(String mail, String usuario, String pw) {
        this.mail = mail;
        this.usuario = usuario;
        this.pw = pw;
    }

    @Id
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity that = (UsuarioEntity) o;

        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (pw != null ? !pw.equals(that.pw) : that.pw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (pw != null ? pw.hashCode() : 0);
        return result;
    }
}
