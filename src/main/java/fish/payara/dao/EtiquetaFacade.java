package fish.payara.dao;

import fish.payara.clases.cliente.UsuarioCliente;
import fish.payara.model.Etiqueta;
import fish.payara.model.Usuario;
import fish.payara.model.Visibilidad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class EtiquetaFacade extends AbstractFacade<Etiqueta> {

    @Inject
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private final static Logger LOGGER = Logger.getLogger(RecursoFacade.class.getName());

    public EtiquetaFacade() {
        super(Etiqueta.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected UserTransaction getUserTransaction() {
        return userTransaction;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    /* Ejemplo.
    public List<Usuario> findRange(int start, int count) {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .setMaxResults(count)
                .setFirstResult(start);
        return query.getResultList();
    }
    */
    
    //Obtener una lista de las etiquetas públicas a partir del idUsuario.   PÚBLICAS (Ordenado alfabéticamente por nombre)
    public List<String> etiquetasPublicasUsuario(int idUsuario) throws Exception{
        //Sentencia JQL
        TypedQuery<String> query = entityManager.createQuery("SELECT DISTINCT v.etiqueta.etiquetaPK.nombre FROM Visibilidad v WHERE v.visibilidad = true AND v.visibilidadPK.idUsuario = :idUsuario ORDER BY 1"
                , String.class)
                .setMaxResults(20); //Número máximo de resultados.
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }
    
    //Obtener una lista de las etiquetas privadas a partir del idUsuario.   PRIVADAS (Ordenado alfabéticamente por nombre)
     public List<String> etiquetasPrivadasUsuario(int idUsuario) throws Exception{
        //Sentencia JQL
        TypedQuery<String> query = entityManager.createQuery("SELECT DISTINCT v.etiqueta.etiquetaPK.nombre FROM Visibilidad v WHERE v.visibilidad = false AND v.visibilidadPK.idUsuario = :idUsuario ORDER BY 1"
                , String.class)
                .setMaxResults(20); //Número máximo de resultados.
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }
    

    //Obtener una lista de las etiquetas públicas de todos los usuarios. NOVEDADES
     public List<Etiqueta> etiquetasNovedades() throws Exception{
        int primer=countVisibilidad()-20;
        if(primer<0)
            primer=0;
        //Sentencia JQL
        TypedQuery<Etiqueta> query = entityManager.createQuery("SELECT DISTINCT v.etiqueta FROM Visibilidad v WHERE v.visibilidad = true"
                , Etiqueta.class)
                .setFirstResult(primer)
                .setMaxResults(20); //Número máximo de resultados.
        return query.getResultList();
    } 
     
    private int countVisibilidad(){
        TypedQuery<Integer> query = entityManager.createQuery("SELECT count(V) FROM Visibilidad v WHERE v.visibilidad=true"
                , Integer.class);
        return query.getFirstResult();
    } 
     
    //Obtener una lista de las etiquetas públicas de todos los usuarios. GENERALES (Ordenado alfabéticamente por nombre)
     public List<Etiqueta> etiquetasGenerales() throws Exception{
        //Sentencia JQL
        TypedQuery<Etiqueta> query = entityManager.createQuery("SELECT DISTINCT v.etiqueta FROM Visibilidad v WHERE v.visibilidad = true ORDER BY v.etiqueta.etiquetaPK.nombre"
                , Etiqueta.class);
                //.setMaxResults(20); //Número máximo de resultados.
        return query.getResultList();
    }  
    
    //Obtener una lista de las etiquetas públicas de todos los recursos. Ordenadas por el número de valoraciones que tiene dicho recurso. //Más Valoradas.
     public List<Etiqueta> etiquetasValoradas() throws Exception{
        List<Etiqueta>etiquetas=new ArrayList<Etiqueta>(); 
        String sentencia="SELECT v.idUsuario,v.nomEtiqueta FROM VISIBILIDAD v INNER JOIN ("
                + "SELECT COUNT(*) AS numAprecios,a.idRecurso FROM APRECIO a INNER JOIN("
                    + "SELECT r.idRecurso FROM RECURSO r WHERE visibilidad=TRUE) r "
                + "ON a.idRecurso=r.idRecurso GROUP BY a.idRecurso ORDER BY 1 DESC) r "
                + "ON v.idRecurso=r.idRecurso WHERE v.visibilidad=TRUE;";
        Query query=entityManager.createNativeQuery(sentencia)
                .setMaxResults(20);
        List<Object[]> lista=query.getResultList();
        for(Object[] o:lista){
            etiquetas.add(new Etiqueta((int) o[0], (String) o[1]));
        }
        return etiquetas;
    }  
     
    //Obtener una lista de las etiquetas públicas de un recurso.
     public List<Etiqueta> etiquetasDeUnRecurso(int idRecurso,int idUsuario) throws Exception{
        //(select idUsuario,nomEtiqueta from VISIBILIDAD  where idRecurso=5 and visibilidad=true) UNION DISTINCT(select idUsuario,nomEtiqueta from VISIBILIDAD where idRecurso=5 and visibilidad=false and idUsuario=4); 
       List<Etiqueta>etiquetas=new ArrayList<Etiqueta>(); 
        String sentencia="(SELECT idUsuario,nomEtiqueta FROM VISIBILIDAD WHERE idRecurso='"+idRecurso+"'AND visibilidad=true)"
                + "UNION DISTINCT"
                + "(SELECT idUsuario,nomEtiqueta FROM VISIBILIDAD WHERE idRecurso='"+idRecurso+"'AND visibilidad=false AND idUsuario='"+idUsuario+"')";
        Query query=entityManager.createNativeQuery(sentencia);
        List<Object[]> lista=query.getResultList();
        for(Object[] o:lista){
            etiquetas.add(new Etiqueta((int) o[0], (String) o[1]));
        }
        return etiquetas;
    }   
    
    //Obtener una lista de las etiquetas públicas de todos los recursos. Ordenadas por el número de comentarios que tiene dicho recurso. //POPULARES.
     public List<Etiqueta> etiquetasPopulares() throws Exception{
        List<Etiqueta>etiquetas=new ArrayList<Etiqueta>(); 
        String sentencia="SELECT v.idUsuario,v.nomEtiqueta FROM VISIBILIDAD v INNER JOIN ("
                + "SELECT COUNT(*) AS numComentarios,c.idRecurso FROM COMENTARIO c INNER JOIN("
                + "SELECT r.idRecurso FROM RECURSO r WHERE visibilidad=TRUE) r "
                + "ON c.idRecurso=r.idRecurso GROUP BY c.idRecurso ORDER BY 1 DESC) c "
                + "ON v.idRecurso=c.idRecurso WHERE v.visibilidad=TRUE;";
        Query query=entityManager.createNativeQuery(sentencia)
                .setMaxResults(20);
        List<Object[]> lista=query.getResultList();
        for(Object[] o:lista){
            etiquetas.add(new Etiqueta((int) o[0], (String) o[1]));
        }
        return etiquetas;
    }   
}
