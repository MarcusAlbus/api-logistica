package pares.api_logistica.repository;

import pares.api_logistica.entity.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ClienteRepository {

    private List<Cliente> clientes;
    private final AtomicLong sequencialId = new AtomicLong(1);

    public ClienteRepository(){
        clientes = new ArrayList<Cliente>();
    }

    public List<Cliente> getClientes(){
        return  clientes;
    }
    public Cliente postCliente(Cliente cliente){
    cliente.setId(sequencialId.getAndIncrement());
    clientes.add(cliente);
    return cliente;
    }

    public boolean deleteCliente(Long id){
        return  clientes.removeIf( cliente -> cliente.getId().equals(id));
    }

}
