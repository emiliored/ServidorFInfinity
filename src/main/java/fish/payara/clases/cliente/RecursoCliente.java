/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.clases.cliente;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class RecursoCliente implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int idRecurso;
    private String nombre;

    public RecursoCliente() {
    }

    public RecursoCliente(int idRecurso, String nombre) {
        this.idRecurso = idRecurso;
        this.nombre = nombre;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "RecursoCliente{" + "idRecurso=" + idRecurso + ", nombre=" + nombre + '}';
    }
}
