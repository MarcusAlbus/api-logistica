package pares.api_logistica.repository;

import pares.api_logistica.entity.Cliente;

import java.lang.ref.Cleaner;
import java.util.HashMap;
import java.util.Map;

public class ClienteRepository {

    private Map<Long, Cliente> clientes;

    public ClienteRepository(){
        clientes = new HashMap<Long,Cliente>();
    }

    public Map<Long,Cliente> getClientes(){return  clientes;}

    public Cliente getClientebyId(Long id){
        for(Cliente cliente : clientes.values()){
            if(cliente.getId() == id){
                return cliente;
            }
        }
        throw new RuntimeException();
    }

    public void postCliente(Cliente cliente){clientes.put(cliente.getId(),  cliente);}

    public Cliente updateCliente(Long id, Cliente clienteAtualizado){
        Cliente cliente = getClientebyId(id);
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setCpfCnpj(clienteAtualizado.getCpfCnpj());
        cliente.setEndereco(clienteAtualizado.getEndereco());
        cliente.setCidade(clienteAtualizado.getCidade());
        cliente.setEstado(clienteAtualizado.getEstado());

        return cliente;
    }

    public Cliente patchCliente(Long id, Cliente clienteAtualizadoParcialmente){
        Cliente cliente = getClientebyId(id);
        cliente.setNome(clienteAtualizadoParcialmente.getNome());
        cliente.setCpfCnpj(clienteAtualizadoParcialmente.getCpfCnpj());
        cliente.setEndereco(clienteAtualizadoParcialmente.getEndereco());
        cliente.setCidade(clienteAtualizadoParcialmente.getCidade());
        cliente.setEstado(clienteAtualizadoParcialmente.getEstado());

        return cliente;
    }

    public boolean deleteCliente(Long id){
        clientes.remove(id);
        return false;
    }

}
