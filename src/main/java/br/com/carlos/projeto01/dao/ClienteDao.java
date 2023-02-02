/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.carlos.projeto01.dao;

import br.com.carlos.projeto01.model.Cliente;
import java.util.List;

/**
 *
 * @author rodri
 */
public class ClienteDao extends GenericDao<Cliente>{
    
    public ClienteDao() {
        super(Cliente.class);
    }
 
    public Cliente findById(Integer id){
        String sql = "select c from Cliente c where c.id =:id";
        List<Cliente> lista = session.createQuery(sql).setParameter("id", id).list();
        if(lista != null && lista.size()>0){
            return lista.get(0);
        }
        return null;
    }
}
