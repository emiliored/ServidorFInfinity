package fish.payara.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Embeddable
public class ComentarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario")
    private int idUsuario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRecurso")
    private int idRecurso;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public ComentarioPK() {
    }

    public ComentarioPK(int idUsuario, int idRecurso, Date fecha) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.idUsuario;
        hash = 41 * hash + this.idRecurso;
        hash = 41 * hash + Objects.hashCode(this.fecha);
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
        final ComentarioPK other = (ComentarioPK) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ComentarioPK{" + "idUsuario=" + idUsuario + ", idRecurso=" + idRecurso + ", fecha=" + fecha + '}';
    }
    
    
}
