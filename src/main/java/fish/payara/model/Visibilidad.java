package fish.payara.model;

import java.io.Serializable;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "VISIBILIDAD", catalog = "payaradb", schema = "")
public class Visibilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected VisibilidadPK visibilidadPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "visibilidad")
    private boolean visibilidad;
    
    @JsonbTransient
    @JoinColumns({
        @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false),
        @JoinColumn(name = "nomEtiqueta", referencedColumnName = "nombre", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Etiqueta etiqueta;
    
    @JsonbTransient
    @JoinColumn(name = "idRecurso", referencedColumnName = "idRecurso", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recurso recurso;

    public Visibilidad() {
    }

    public Visibilidad(VisibilidadPK visibilidadPK) {
        this.visibilidadPK = visibilidadPK;
    }

    public Visibilidad(VisibilidadPK visibilidadPK, boolean visibilidad) {
        this.visibilidadPK = visibilidadPK;
        this.visibilidad = visibilidad;
    }

    public Visibilidad(int idUsuario, String nomEtiqueta, int idRecurso) {
        this.visibilidadPK = new VisibilidadPK(idUsuario, nomEtiqueta, idRecurso);
    }

    public VisibilidadPK getVisibilidadPK() {
        return visibilidadPK;
    }

    public void setVisibilidadPK(VisibilidadPK visibilidadPK) {
        this.visibilidadPK = visibilidadPK;
    }

    public boolean getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(boolean visibilidad) {
        this.visibilidad = visibilidad;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.visibilidadPK);
        hash = 47 * hash + (this.visibilidad ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.etiqueta);
        hash = 47 * hash + Objects.hashCode(this.recurso);
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
        final Visibilidad other = (Visibilidad) obj;
        if (this.visibilidad != other.visibilidad) {
            return false;
        }
        if (!Objects.equals(this.visibilidadPK, other.visibilidadPK)) {
            return false;
        }
        if (!Objects.equals(this.etiqueta, other.etiqueta)) {
            return false;
        }
        if (!Objects.equals(this.recurso, other.recurso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Visibilidad{" + "visibilidadPK=" + visibilidadPK + ", visibilidad=" + visibilidad + ", etiqueta=" + etiqueta + ", recurso=" + recurso + '}';
    }
    
    
}
