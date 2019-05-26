/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.clases;

/**
 * Clase usada para indicar que una contraseña no es válida, es decir, no concuerda con la base de datos.
 * @author usuario
 */
public class BadPasswordException extends Exception{

    public BadPasswordException(){
        super();
    }
    
    public BadPasswordException(String message){
        super(message);
    }
    
    public BadPasswordException(String message, Throwable cause) { 
        super(message, cause); 
    }
    
    public BadPasswordException(Throwable cause) { 
        super(cause); 
    }
}

