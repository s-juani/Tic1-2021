package application.entities.ent;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ExperienciaEntityPK implements Serializable {
    private String operador_turistico;
    private String nombre;

    @Column(name = "operador_turistico")
    @Id
    public String getOperador_turistico() {
        return operador_turistico;
    }

    public void setOperador_turistico(String operador_turistico) {
        this.operador_turistico = operador_turistico;
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

        if (operador_turistico != null ? !operador_turistico.equals(that.operador_turistico) : that.operador_turistico != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operador_turistico != null ? operador_turistico.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
