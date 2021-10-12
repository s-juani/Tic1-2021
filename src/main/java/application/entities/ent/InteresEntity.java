package application.entities.ent;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "interes")
public class InteresEntity {
    private String nombre;
    private InteresEntity interesByEsSubinteresDe;
    private Collection<InteresEntity> interesByNombre;
    private Collection<InteresExperienciaEntity> interesExperienciasByNombre;
    private Collection<InteresTuristaEntity> interesTuristasByNombre;

    @Id
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InteresEntity that = (InteresEntity) o;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "es_subinteres_de", referencedColumnName = "nombre", nullable = true)
    public InteresEntity getInteresByEsSubinteresDe() {
        return interesByEsSubinteresDe;
    }

    public void setInteresByEsSubinteresDe(InteresEntity interesByEsSubinteresDe) {
        this.interesByEsSubinteresDe = interesByEsSubinteresDe;
    }

//    @OneToMany(mappedBy = "interesByEsSubinteresDe")
//    public Collection<InteresEntity> getInteresByNombre() {
//        return interesByNombre;
//    }
//
//    public void setInteresByNombre(Collection<InteresEntity> interesByNombre) {
//        this.interesByNombre = interesByNombre;
//    }
//
//    @OneToMany(mappedBy = "interesByInteres")
//    public Collection<InteresExperienciaEntity> getInteresExperienciasByNombre() {
//        return interesExperienciasByNombre;
//    }
//
//    public void setInteresExperienciasByNombre(Collection<InteresExperienciaEntity> interesExperienciasByNombre) {
//        this.interesExperienciasByNombre = interesExperienciasByNombre;
//    }
//
//    @OneToMany(mappedBy = "interesByNombreInteres")
//    public Collection<InteresTuristaEntity> getInteresTuristasByNombre() {
//        return interesTuristasByNombre;
//    }
//
//    public void setInteresTuristasByNombre(Collection<InteresTuristaEntity> interesTuristasByNombre) {
//        this.interesTuristasByNombre = interesTuristasByNombre;
//    }
}
