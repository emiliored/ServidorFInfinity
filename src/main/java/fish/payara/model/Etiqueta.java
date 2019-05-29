package fish.payara.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ETIQUETA", catalog = "payaradb", schema = "")
public class Etiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected EtiquetaPK etiquetaPK;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etiqueta", fetch = FetchType.LAZY)
    private List<Visibilidad> visibilidadList;
    
    @JsonbTransient
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Etiqueta() {
    }

    public Etiqueta(EtiquetaPK etiquetaPK) {
        this.etiquetaPK = etiquetaPK;
    }

    public Etiqueta(int idUsuario, String nombre) {
        this.etiquetaPK = new EtiquetaPK(idUsuario, nombre);
    }

    public EtiquetaPK getEtiquetaPK() {
        return etiquetaPK;
    }

    public void setEtiquetaPK(EtiquetaPK etiquetaPK) {
        this.etiquetaPK = etiquetaPK;
    }

    @JsonbTransient
    public List<Visibilidad> getVisibilidadList() {
        return visibilidadList;
    }

    public void setVisibilidadList(List<Visibilidad> visibilidadList) {
        this.visibilidadList = visibilidadList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        final Etiqueta other = (Etiqueta) obj;
        if (!Objects.equals(this.etiquetaPK, other.etiquetaPK)) {
            return false;
        }
        if (!Objects.equals(this.visibilidadList, other.visibilidadList)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "etiquetaPK=" + etiquetaPK + ", visibilidadList=" + visibilidadList + ", usuario=" + usuario + '}';
    }
}
