package br.com.carlos.projeto01.dao;

import br.com.carlos.projeto01.dao.database.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Vidal
 */
public class GenericDao<T> {

    protected Session  session;
    protected Class<T> entityClass;
    public    Integer  maximoResultado = 10;

    public GenericDao(Class<T> entity) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.entityClass = entity;
    }


    /**
     * Retorna todos os registros
     * já filtrando por idEmpresa
     * @return 
     */
    public List<T> findAll() {
        return findAllRange(0, 1000000);
    }

    
    /**
     * Retorna range de registros
     * já filtrando por idEmpresa
     * @return 
     */
    public List<T> findAllRange(Integer qtdInicial, Integer qtdFinal) {
        String hql = " from " + entityClass.getSimpleName() ;
        
        return session.createQuery(hql)
                .setMaxResults(qtdFinal - qtdInicial)
                .setFirstResult(qtdInicial)
                .list();
    }
    
    /**
     * Deleta todos os registro de determinada empresa
     */
    public void deleteAll() {
        String query = "Delete from " + entityClass.getSimpleName();
        session.createQuery(query).executeUpdate();        
    }
    
    /**
     * Metodo responsavel por inserir o obj no BD, porem sem transação
     *
     * @param obj
     */
    public void save(T obj) {
        session.save(obj);
    }

    public void update(T obj) {
        session.update(obj);
    }

    public void delete(T obj) {
        session.delete(obj);       
    }

    public void saveOrUpdate(T obj) {
        session.saveOrUpdate(obj);
    }

   
 

   /**
    * Conta Quantidade de Registros 
    * filtrando idEmpresa
    * @return 
    */
    public Long count() {
        String hql = "select count(*) from " + entityClass.getSimpleName();
        return (Long) session.createQuery(hql).list().get(0);
    }
    
    public void beginTransaction() {
        session.getTransaction().begin();
    }

    public void commitTransaction() {
        session.getTransaction().commit();
    }

    public void rollbackTransaction() {
        session.getTransaction().rollback();
    }

    
    
}
