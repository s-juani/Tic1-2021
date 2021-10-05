package application.entities.ent;

import javax.persistence.*;

@Entity
@Table(name = "forma_contacto", schema = "tic1sch", catalog = "")
public class FormaContactoEntity {
    private String idForma;
    private String tipo;
    private String contacto;
    private OperadorEntity operadorByMailOperador;

    @Id
    @Column(name = "idForma")
    public String getIdForma() {
        return idForma;
    }

    public void setIdForma(String idForma) {
        this.idForma = idForma;
    }

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "contacto")
    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormaContactoEntity that = (FormaContactoEntity) o;

        if (idForma != null ? !idForma.equals(that.idForma) : that.idForma != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (contacto != null ? !contacto.equals(that.contacto) : that.contacto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idForma != null ? idForma.hashCode() : 0;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (contacto != null ? contacto.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "mail_operador", referencedColumnName = "mail", nullable = false)
    public OperadorEntity getOperadorByMailOperador() {
        return operadorByMailOperador;
    }

    public void setOperadorByMailOperador(OperadorEntity operadorByMailOperador) {
        this.operadorByMailOperador = operadorByMailOperador;
    }
}
