package application.entities.ent;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ExperienciaEntityPK implements Serializable {
    private String operadorTuristico;
    private String nombre;

    @Column(name = "operador_turistico")
    @Id
    public String getOperadorTuristico() {
        return operadorTuristico;
    }

    public void setOperadorTuristico(String operadorTuristico) {
        this.operadorTuristico = operadorTuristico;
    }

    @Column(name = "nombre")
    @Id
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

        ExperienciaEntityPK that = (ExperienciaEntityPK) o;

        if (operadorTuristico != null ? !operadorTuristico.equals(that.operadorTuristico) : that.operadorTuristico != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operadorTuristico != null ? operadorTuristico.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
