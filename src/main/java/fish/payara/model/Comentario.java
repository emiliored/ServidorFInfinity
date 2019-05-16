package fish.payara.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "COMENTARIO", catalog = "payaradb", schema = "")
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ComentarioPK comentarioPK;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "texto")
    private String texto;
    
    @JsonbTransient
    @JoinColumn(name = "idRecurso", referencedColumnName = "idRecurso", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recurso recurso;
    
    @JsonbTransient
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Comentario() {
    }

    public Comentario(ComentarioPK comentarioPK) {
        this.comentarioPK = comentarioPK;
    }

    public Comentario(ComentarioPK comentarioPK, String texto) {
        this.comentarioPK = comentarioPK;
        this.texto = texto;
    }

    public Comentario(int idUsuario, int idRecurso, Date fecha) {
        this.comentarioPK = new ComentarioPK(idUsuario, idRecurso, fecha);
    }

    public ComentarioPK getComentarioPK() {
        return comentarioPK;
    }

    public void setComentarioPK(ComentarioPK comentarioPK) {
        this.comentarioPK = comentarioPK;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.comentarioPK);
        hash = 89 * hash + Objects.hashCode(this.texto);
        hash = 89 * hash + Objects.hashCode(this.recurso);
        hash = 89 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comentario other = (Comentario) obj;
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        if (!Objects.equals(this.comentarioPK, other.comentarioPK)) {
            return false;
        }
        if (!Objects.equals(this.recurso, other.recurso)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comentario{" + "comentarioPK=" + comentarioPK + ", texto=" + texto + ", recurso=" + recurso + ", usuario=" + usuario + '}';
    }
}
