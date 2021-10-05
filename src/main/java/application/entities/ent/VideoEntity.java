package application.entities.ent;

import javax.persistence.*;

@Entity
@Table(name = "video", schema = "tic1sch", catalog = "")
public class VideoEntity {
    private String url;
    private ExperienciaEntity experiencia;

    @Id
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoEntity that = (VideoEntity) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
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
