/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.clases.cliente;

/**
 *
 * @author usuario
 */
public class UsuarioCliente {
    
    private Integer idUsuario;
    private String apodo;
    private int numeroRecursos;
    private int numeroLikes;
    private int numeroComentarios;
    
    
    public UsuarioCliente() {
        
    }
    
    @Deprecated
    public UsuarioCliente(Integer idUsuario, String apodo) {
        this.idUsuario = idUsuario;
        this.apodo = apodo;
    }
    
    public UsuarioCliente(Integer idUsuario, String apodo, long numeroRecursos, long numeroLikes, long numeroComentarios) {
        this.idUsuario = idUsuario;
        this.apodo = apodo;
        this.numeroRecursos = (int) numeroRecursos;
        this.numeroLikes = (int) numeroLikes;
        this.numeroComentarios = (int) numeroComentarios;
    }
    
    public UsuarioCliente(Integer idUsuario, String apodo, int numeroRecursos, int numeroLikes, int numeroComentarios) {
        this.idUsuario = idUsuario;
        this.apodo = apodo;
        this.numeroRecursos = numeroRecursos;
        this.numeroLikes = numeroLikes;
        this.numeroComentarios = numeroComentarios;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getNumeroRecursos() {
        return numeroRecursos;
    }

    public void setNumeroRecursos(int numeroRecursos) {
        this.numeroRecursos = numeroRecursos;
    }

    public int getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public int getNumeroComentarios() {
        return numeroComentarios;
    }

    public void setNumeroComentarios(int numeroComentarios) {
        this.numeroComentarios = numeroComentarios;
    }

    @Override
    public String toString() {
        return "UsuarioCliente{" + "idUsuario=" + idUsuario + ", apodo=" + apodo + ", numeroRecursos=" + numeroRecursos + ", numeroLikes=" + numeroLikes + ", numeroComentarios=" + numeroComentarios + '}';
    }
    
    
}
