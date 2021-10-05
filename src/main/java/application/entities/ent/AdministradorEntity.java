package application.entities.ent;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="mail")
@Table(name = "administrador")
public class AdministradorEntity extends UsuarioEntity{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministradorEntity that = (AdministradorEntity) o;

        if (this.getMail() != null ? !this.getMail().equals(that.getMail()) : that.getMail() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return this.getMail() != null ? this.getMail().hashCode() : 0;
    }
}
