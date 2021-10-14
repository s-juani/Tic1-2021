package application.entities.ent;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ExperienciaEntityPK implements Serializable {
    private String operador;
    private String nombre;

    public ExperienciaEntityPK(String operador, String nombre) {
        this.operador = operador;
        this.nombre = nombre;
    }

    public ExperienciaEntityPK(){}

    @Column(name = "operador")
    @Id
    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador_turistico) {
        this.operador = operador_turistico;
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

        if (operador != null ? !operador.equals(that.operador) : that.operador != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operador != null ? operador.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
