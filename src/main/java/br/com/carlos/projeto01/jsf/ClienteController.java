/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.carlos.projeto01.jsf;

import br.com.carlos.projeto01.dao.ClienteDao;
import br.com.carlos.projeto01.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author rodri
 */
@Named("clienteController")
@SessionScoped
public class ClienteController implements Serializable {
   
    private List<Cliente> items = null;
    private Cliente selected;
    private ClienteDao clienteDao;

    public ClienteController() {
        clienteDao = new ClienteDao();
        loadItems();
    }
    
    private void loadItems(){
        items = clienteDao.findAll();
    }

    public Cliente getSelected() {
        return selected;
    }

    public void setSelected(Cliente selected) {
        this.selected = selected;
    }

    public List<Cliente> getItems() {
        return items;
    }

    public void setItems(List<Cliente> items) {
        this.items = items;
    }
    
    public String editar(Integer idCliente){
        //ir no dao e procurar pelo id e colocar o resultado no selected
        // redirecionar para a pagina de edicao 
        
        System.out.print(idCliente);
        selected = clienteDao.findById(idCliente);        
        return "cliente_manut?redirect=true";
    }
    
    public String gravar(){
        System.out.println(selected.getNome());
        //gravar no banco de dados
        clienteDao.beginTransaction();
        clienteDao.saveOrUpdate(selected);
        clienteDao.commitTransaction();
        loadItems();
        return "cliente_list?redirect=true";
    }
 
    public String excluir(Integer idCliente){
        //ir no dao e procurar pelo id e colocar o resultado no selected
        // redirecionar para a pagina de edicao 
        
        System.out.print(idCliente);
        Cliente deleted = clienteDao.findById(idCliente);
        clienteDao.beginTransaction();
        clienteDao.delete(deleted);
        clienteDao.commitTransaction();
        loadItems();
        return "cliente_list?redirect=true";
    }
     
    public String adicionar(){
        selected = new Cliente();        
        return "cliente_manut?redirect=true";
    }
     
}
