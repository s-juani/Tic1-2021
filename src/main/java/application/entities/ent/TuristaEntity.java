package application.entities.ent;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes", schema = "tic1sch")
public class TuristaEntity extends UsuarioEntity {
    private String mail;
    private String nombre;
    private LocalDate fechaNacimiento;
    private boolean tipoDocumento;
    private String nroDocumento;
    private String nacionalidad;
    private String origenDocumento;

    @Id
    @GeneratedValue(generator = "client_id")
    @GenericGenerator(name = "clien_id", strategy = "increment")
    private long id;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public boolean getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(boolean tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }


    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    public String getOrigenDocumento() {
        return origenDocumento;
    }

    public void setOrigenDocumento(String origenDocumento) {
        this.origenDocumento = origenDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TuristaEntity that = (TuristaEntity) o;

        if (id != that.id) return false;
        if (tipoDocumento != that.tipoDocumento) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;
        if (nroDocumento != null ? !nroDocumento.equals(that.nroDocumento) : that.nroDocumento != null) return false;
        if (nacionalidad != null ? !nacionalidad.equals(that.nacionalidad) : that.nacionalidad != null) return false;
        if (origenDocumento != null ? !origenDocumento.equals(that.origenDocumento) : that.origenDocumento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (nroDocumento != null ? nroDocumento.hashCode() : 0);
        result = 31 * result + (nacionalidad != null ? nacionalidad.hashCode() : 0);
        result = 31 * result + (origenDocumento != null ? origenDocumento.hashCode() : 0);
        return result;
    }
}
