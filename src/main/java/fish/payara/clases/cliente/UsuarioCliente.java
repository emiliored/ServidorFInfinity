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

    public UsuarioCliente() {
        
    }
    
    public UsuarioCliente(Integer idUsuario, String apodo) {
        this.idUsuario = idUsuario;
        this.apodo = apodo;
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
}
