package application.entities.ent;

//import javax.persistence.Column;
//import javax.persistence.Id;
//import java.io.Serializable;
//
//public class InteresExperienciaEntityPK implements Serializable {
//    private String interes;
//    private String operadorExperiencia;
//    private String nombreExperiencia;
//
//    @Column(name = "interes")
//    @Id
//    public String getInteres() {
//        return interes;
//    }
//
//    public void setInteres(String interes) {
//        this.interes = interes;
//    }
//
//    @Column(name = "operador_experiencia")
//    @Id
//    public String getOperadorExperiencia() {
//        return operadorExperiencia;
//    }
//
//    public void setOperadorExperiencia(String operadorExperiencia) {
//        this.operadorExperiencia = operadorExperiencia;
//    }
//
//    @Column(name = "nombre_experiencia")
//    @Id
//    public String getNombreExperiencia() {
//        return nombreExperiencia;
//    }
//
//    public void setNombreExperiencia(String nombreExperiencia) {
//        this.nombreExperiencia = nombreExperiencia;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        InteresExperienciaEntityPK that = (InteresExperienciaEntityPK) o;
//
//        if (interes != null ? !interes.equals(that.interes) : that.interes != null) return false;
//        if (operadorExperiencia != null ? !operadorExperiencia.equals(that.operadorExperiencia) : that.operadorExperiencia != null)
//            return false;
//        if (nombreExperiencia != null ? !nombreExperiencia.equals(that.nombreExperiencia) : that.nombreExperiencia != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = interes != null ? interes.hashCode() : 0;
//        result = 31 * result + (operadorExperiencia != null ? operadorExperiencia.hashCode() : 0);
//        result = 31 * result + (nombreExperiencia != null ? nombreExperiencia.hashCode() : 0);
//        return result;
//    }
//}
