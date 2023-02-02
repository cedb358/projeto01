/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.carlos.projeto01.dao;

import br.com.carlos.projeto01.model.Tipo;
import java.util.List;

/**
 *
 * @author rodri
 */
public class TipoDao extends GenericDao<Tipo>{
    
    public TipoDao() {
        super(Tipo.class);
    }
 
    public Tipo findById(Integer id){
        String sql = "select m from Membro m where m.id =:id";
        List<Tipo> lista = session.createQuery(sql).setParameter("id", id).list();
        if(lista != null && lista.size()>0){
            return lista.get(0);
        }
        return null;
    }
}
