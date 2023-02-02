package br.com.carlos.projeto01.dao.database;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

/**
 * Classe utilizada para fornecer a sessao para acesso ao banco de dados
 *
 * @author rodrigo
 */
public class HibernateUtil {
    private static  SessionFactory sessionFactory;
    private static  Configuration cfg;
      
    public static SessionFactory getSessionFactory() {
        if( sessionFactory == null){
           loadHibernate(); 
        }
        return sessionFactory;
    }

    public static Session openSession(){
        return getSessionFactory().openSession();
    }

    public static void installDataBase(){
        if( cfg == null){
           loadHibernate(); 
        }
        SchemaUpdate su = new SchemaUpdate(cfg);
        su.execute(true, true);
    }
    
    private static void loadHibernate(){
       cfg = new AnnotationConfiguration().configure();
       sessionFactory = cfg.buildSessionFactory();    
       installDataBase();
    }
 
}
