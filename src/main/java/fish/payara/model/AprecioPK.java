package fish.payara.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class AprecioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario")
    private int idUsuario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRecurso")
    private int idRecurso;

    public AprecioPK() {
    }

    public AprecioPK(int idUsuario, int idRecurso) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idUsuario;
        hash = 79 * hash + this.idRecurso;
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
        final AprecioPK other = (AprecioPK) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        return true;
    } 

    @Override
    public String toString() {
        return "AprecioPK{" + "idUsuario=" + idUsuario + ", idRecurso=" + idRecurso + '}';
    }
}
