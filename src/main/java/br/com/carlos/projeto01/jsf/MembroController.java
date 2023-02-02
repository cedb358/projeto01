/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.carlos.projeto01.jsf;

import br.com.carlos.projeto01.dao.MembroDao;
import br.com.carlos.projeto01.model.Membro;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author rodri
 */
@Named("membroController")
@SessionScoped
public class MembroController implements Serializable {
   
    private List<Membro> items = null;
    private Membro selected;
    private MembroDao membroDao;

    public MembroController() {
        membroDao = new MembroDao();
        loadItems();
    }
    
    private void loadItems(){
        items = membroDao.findAll();
    }

    public Membro getSelected() {
        return selected;
    }

    public void setSelected(Membro selected) {
        this.selected = selected;
    }

    public List<Membro> getItems() {
        return items;
    }

    public void setItems(List<Membro> items) {
        this.items = items;
    }
    
    public String editar(Integer idMembro){
        //ir no dao e procurar pelo id e colocar o resultado no selected
        // redirecionar para a pagina de edicao 
        
        System.out.print(idMembro);
        selected = membroDao.findById(idMembro);        
        return "membro_manut?redirect=true";
    }
    
    public String gravar(){
        System.out.println(selected.getNome());
        //gravar no banco de dados
        membroDao.beginTransaction();
        membroDao.saveOrUpdate(selected);
        membroDao.commitTransaction();
        loadItems();
        return "membro_list?redirect=true";
    }
 
    public String excluir(Integer idMembro){
        //ir no dao e procurar pelo id e colocar o resultado no selected
        // redirecionar para a pagina de edicao 
        
        System.out.print(idMembro);
        Membro deleted = membroDao.findById(idMembro);
        membroDao.beginTransaction();
        membroDao.delete(deleted);
        membroDao.commitTransaction();
        loadItems();
        return "membro_list?redirect=true";
    }
     
    public String adicionar(){
        selected = new Membro();        
        return "membro_manut?redirect=true";
    }
     
}
