package fish.payara.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "RECURSO", catalog = "payaradb", schema = "")
public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario")
    private int idUsuario;
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRecurso")
    private Integer idRecurso;
 
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 43)
    @Column(name = "filehash")
    private String filehash;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ruta")
    private String ruta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "visibilidad")
    private boolean visibilidad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recurso", fetch = FetchType.LAZY)
    private List<Visibilidad> visibilidadList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recurso", fetch = FetchType.LAZY)
    private List<Comentario> comentarioList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recurso", fetch = FetchType.LAZY)
    private List<Aprecio> aprecioList;
    
    @JsonbTransient
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Recurso() {
    }

    public Recurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Recurso(Integer idUsuario,Integer idRecurso, String nombre, String descripcion, String filehash, String ruta, boolean visibilidad) {
        this.idUsuario=idUsuario;
        this.idRecurso = idRecurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.filehash = filehash;
        this.ruta = ruta;
        this.visibilidad = visibilidad;
    }
    
    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

      public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFilehash() {
        return filehash;
    }

    public void setFilehash(String filehash) {
        this.filehash = filehash;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(boolean visibilidad) {
        this.visibilidad = visibilidad;
    }

    @JsonbTransient
    public List<Visibilidad> getVisibilidadList() {
        return visibilidadList;
    }

    public void setVisibilidadList(List<Visibilidad> visibilidadList) {
        this.visibilidadList = visibilidadList;
    }

    @JsonbTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @JsonbTransient
    public List<Aprecio> getAprecioList() {
        return aprecioList;
    }

    public void setAprecioList(List<Aprecio> aprecioList) {
        this.aprecioList = aprecioList;
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
        final Recurso other = (Recurso) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.visibilidad != other.visibilidad) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.filehash, other.filehash)) {
            return false;
        }
        if (!Objects.equals(this.ruta, other.ruta)) {
            return false;
        }
        if (!Objects.equals(this.idRecurso, other.idRecurso)) {
            return false;
        }
        if (!Objects.equals(this.visibilidadList, other.visibilidadList)) {
            return false;
        }
        if (!Objects.equals(this.comentarioList, other.comentarioList)) {
            return false;
        }
        if (!Objects.equals(this.aprecioList, other.aprecioList)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Recurso{" + "idRecurso=" + idRecurso + '}';
    }

   
    
}
