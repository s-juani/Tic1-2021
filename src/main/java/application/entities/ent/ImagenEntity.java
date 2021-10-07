package application.entities.ent;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "imagen")
public class ImagenEntity {
    private int idimagen;
    private byte[] imagen;
    private ExperienciaEntity experiencia;

    @Id
    @Column(name = "idimagen")
    public int getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(int idimagen) {
        this.idimagen = idimagen;
    }

    @Basic
    @Column(name = "imagen")
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagenEntity that = (ImagenEntity) o;

        if (idimagen != that.idimagen) return false;
        if (!Arrays.equals(imagen, that.imagen)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idimagen;
        result = 31 * result + Arrays.hashCode(imagen);
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "operador_experiencia", referencedColumnName = "operador_turistico", nullable = false), @JoinColumn(name = "nombre_experiencia", referencedColumnName = "nombre", nullable = false)})
    public ExperienciaEntity getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaEntity experiencia) {
        this.experiencia = experiencia;
    }
}
