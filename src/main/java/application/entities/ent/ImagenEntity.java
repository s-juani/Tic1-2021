package application.entities.ent;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

@Entity
@Table(name = "imagen")
public class ImagenEntity {
    private int idimagen;
    private byte[] imagen;
    private ExperienciaEntity experiencia;

    public ImagenEntity(byte[] imagen, ExperienciaEntity experiencia) {
        this.imagen = imagen;
        this.experiencia = experiencia;
    }

    public ImagenEntity() {

    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "idimagen")
    public int getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(int idimagen) {
        this.idimagen = idimagen;
    }

    public WritableImage getJavaFxImage(final int width, final int height){
        WritableImage image = new WritableImage(width, height);
        try{
            ByteArrayInputStream bis = new ByteArrayInputStream(imagen);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read,null);
        }catch (IOException e){}
        return image;
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

        ImagenEntity imagen1 = (ImagenEntity) o;

        if (idimagen != imagen1.idimagen) return false;
        if (!Arrays.equals(imagen, imagen1.imagen)) return false;
        return experiencia != null ? experiencia.equals(imagen1.experiencia) : imagen1.experiencia == null;
    }

    @Override
    public int hashCode() {
        int result = idimagen;
        result = 31 * result + Arrays.hashCode(imagen);
        result = 31 * result + (experiencia != null ? experiencia.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "operador_experiencia", referencedColumnName = "operador", nullable = false), @JoinColumn(name = "nombre_experiencia", referencedColumnName = "nombre", nullable = false)})
    public ExperienciaEntity getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaEntity experiencia) {
        this.experiencia = experiencia;
    }
}
