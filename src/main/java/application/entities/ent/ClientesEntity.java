package application.entities.ent;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes", schema = "tic1sch")
public class ClientesEntity extends UsuarioEntity {

    @Id
    @GeneratedValue(generator = "client_id")
    @GenericGenerator(name = "clien_id", strategy = "increment")
    public long id;

    private String usuario;

    private String mail;

    private String nombre;

    private LocalDate fechaNacimiento;

    private String pw;

    public ClientesEntity(String mail, String usuario, String nombre, LocalDate fechaNacimiento, String pw) {
        this.mail = mail;
        this.usuario = usuario;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.pw = pw;
    }

    public ClientesEntity() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

        ClientesEntity that = (ClientesEntity) o;

        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;
        if (pw != null ? !pw.equals(that.pw) : that.pw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (pw != null ? pw.hashCode() : 0);
        return result;
    }
}
