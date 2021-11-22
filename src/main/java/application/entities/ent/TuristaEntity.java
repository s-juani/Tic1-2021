package application.entities.ent;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@PrimaryKeyJoinColumn(name="mail")
@Table(name = "clientes")
public class TuristaEntity extends UsuarioEntity {
    private String nombre;
    private LocalDate fechaNacimiento;
    private boolean tipoDocumento;
    private String nroDocumento;

    private PaisEntity nacionalidad;
    private PaisEntity origenDocumento;
    private Collection<InteresEntity> intereses;

    public TuristaEntity(String mail, String usuario, String nombre, LocalDate fechaNacimiento, String pw)  {
        super(mail, usuario, pw);
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nroDocumento = null;
    }

//    @Id
//    @GeneratedValue(generator = "client_id")
//    @GenericGenerator(name = "clien_id", strategy = "increment")
    private long id;

    public TuristaEntity() {

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

    @ManyToOne(targetEntity = PaisEntity.class)
    @JoinColumn(name = "nacionalidad", referencedColumnName = "nombre", nullable = false)
    public PaisEntity getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(PaisEntity nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    @ManyToOne(targetEntity = PaisEntity.class)
    @JoinColumn(name = "origen_documento", referencedColumnName = "nombre", nullable = false)
    public PaisEntity getOrigenDocumento() {
        return origenDocumento;
    }

    public void setOrigenDocumento(PaisEntity origenDocumento) {
        this.origenDocumento = origenDocumento;
    }

    @ManyToMany(targetEntity = InteresEntity.class,fetch = FetchType.EAGER)
    @JoinTable(name="interes_turista",
            joinColumns = @JoinColumn(insertable = false,updatable = false,name = "mail_turista", referencedColumnName = "mail", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "nombre_interes", referencedColumnName = "nombre", nullable = false))
    public Collection<InteresEntity> getIntereses() {
        return intereses;
    }

    public void setIntereses(Collection<InteresEntity> intereses) {
        this.intereses = intereses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TuristaEntity that = (TuristaEntity) o;

        if (id != that.id) return false;
        if (tipoDocumento != that.tipoDocumento) return false;
        if (this.getMail() != null ? !this.getMail().equals(that.getMail()) : that.getMail() != null) return false;
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
        int result = this.getMail() != null ? this.getMail().hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (nroDocumento != null ? nroDocumento.hashCode() : 0);
        result = 31 * result + (nacionalidad != null ? nacionalidad.hashCode() : 0);
        result = 31 * result + (origenDocumento != null ? origenDocumento.hashCode() : 0);
        return result;
    }
}
