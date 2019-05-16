package fish.payara.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class VisibilidadPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario")
    private int idUsuario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomEtiqueta")
    private String nomEtiqueta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRecurso")
    private int idRecurso;

    public VisibilidadPK() {
    }

    public VisibilidadPK(int idUsuario, String nomEtiqueta, int idRecurso) {
        this.idUsuario = idUsuario;
        this.nomEtiqueta = nomEtiqueta;
        this.idRecurso = idRecurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomEtiqueta() {
        return nomEtiqueta;
    }

    public void setNomEtiqueta(String nomEtiqueta) {
        this.nomEtiqueta = nomEtiqueta;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idUsuario;
        hash = 11 * hash + Objects.hashCode(this.nomEtiqueta);
        hash = 11 * hash + this.idRecurso;
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
        final VisibilidadPK other = (VisibilidadPK) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        if (!Objects.equals(this.nomEtiqueta, other.nomEtiqueta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VisibilidadPK{" + "idUsuario=" + idUsuario + ", nomEtiqueta=" + nomEtiqueta + ", idRecurso=" + idRecurso + '}';
    }

    
}
