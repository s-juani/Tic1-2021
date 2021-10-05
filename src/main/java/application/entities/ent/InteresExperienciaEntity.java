package application.entities.ent;

import javax.persistence.*;

@Entity
@Table(name = "interes_experiencia")
@IdClass(InteresExperienciaEntityPK.class)
public class InteresExperienciaEntity {
    private String interes;
    private String operadorExperiencia;
    private String nombreExperiencia;
    private InteresEntity interesByInteres;
    private ExperienciaEntity experiencia;

    @Id
    @Column(name = "interes")
    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    @Id
    @Column(name = "operador_experiencia")
    public String getOperadorExperiencia() {
        return operadorExperiencia;
    }

    public void setOperadorExperiencia(String operadorExperiencia) {
        this.operadorExperiencia = operadorExperiencia;
    }

    @Id
    @Column(name = "nombre_experiencia")
    public String getNombreExperiencia() {
        return nombreExperiencia;
    }

    public void setNombreExperiencia(String nombreExperiencia) {
        this.nombreExperiencia = nombreExperiencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InteresExperienciaEntity that = (InteresExperienciaEntity) o;

        if (interes != null ? !interes.equals(that.interes) : that.interes != null) return false;
        if (operadorExperiencia != null ? !operadorExperiencia.equals(that.operadorExperiencia) : that.operadorExperiencia != null)
            return false;
        if (nombreExperiencia != null ? !nombreExperiencia.equals(that.nombreExperiencia) : that.nombreExperiencia != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interes != null ? interes.hashCode() : 0;
        result = 31 * result + (operadorExperiencia != null ? operadorExperiencia.hashCode() : 0);
        result = 31 * result + (nombreExperiencia != null ? nombreExperiencia.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "interes", referencedColumnName = "nombre", nullable = false)
//    public InteresEntity getInteresByInteres() {
//        return interesByInteres;
//    }
//
//    public void setInteresByInteres(InteresEntity interesByInteres) {
//        this.interesByInteres = interesByInteres;
//    }
//
//    @ManyToOne
//    @JoinColumns({@JoinColumn(name = "operador_experiencia", referencedColumnName = "operador_turistico", nullable = false), @JoinColumn(name = "nombre_experiencia", referencedColumnName = "nombre", nullable = false)})
//    public ExperienciaEntity getExperiencia() {
//        return experiencia;
//    }
//
//    public void setExperiencia(ExperienciaEntity experiencia) {
//        this.experiencia = experiencia;
//    }
}
