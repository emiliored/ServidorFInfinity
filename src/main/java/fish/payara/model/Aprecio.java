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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "APRECIO", catalog = "payaradb", schema = "")
public class Aprecio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected AprecioPK aprecioPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @JsonbTransient
    @JoinColumn(name = "idRecurso", referencedColumnName = "idRecurso", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recurso recurso;
    
    @JsonbTransient
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Aprecio() {
    }

    public Aprecio(AprecioPK aprecioPK) {
        this.aprecioPK = aprecioPK;
    }

    public Aprecio(AprecioPK aprecioPK, Date fecha) {
        this.aprecioPK = aprecioPK;
        this.fecha = fecha;
    }

    public Aprecio(int idUsuario, int idRecurso) {
        this.aprecioPK = new AprecioPK(idUsuario, idRecurso);
    }

    public AprecioPK getAprecioPK() {
        return aprecioPK;
    }

    public void setAprecioPK(AprecioPK aprecioPK) {
        this.aprecioPK = aprecioPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash = 29 * hash + Objects.hashCode(this.aprecioPK);
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + Objects.hashCode(this.recurso);
        hash = 29 * hash + Objects.hashCode(this.usuario);
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
        final Aprecio other = (Aprecio) obj;
        if (!Objects.equals(this.aprecioPK, other.aprecioPK)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
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
        return "Aprecio{" + "aprecioPK=" + aprecioPK + ", fecha=" + fecha + ", recurso=" + recurso + ", usuario=" + usuario + '}';
    }

    
}
