package pares.api_logistica.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pares.api_logistica.dto.ClienteCreateRequest;
import pares.api_logistica.dto.ClientePatch;
import pares.api_logistica.dto.ClienteResponse;
import pares.api_logistica.dto.ClienteUpdate;
import pares.api_logistica.entity.Cliente;
import pares.api_logistica.mapper.ClienteMapper;
import pares.api_logistica.repository.ClienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository = new ClienteRepository();
    private final ClienteMapper mapper;

    public ClienteResponse cadastrar(ClienteCreateRequest request){
        Cliente clienteCriado = repository.postCliente(mapper.toEntity(request));
        return mapper.toDto(clienteCriado);
    }

    public List<ClienteResponse> listar(){
        List<Cliente> clientes = repository.getClientes();
        return mapper.toResponseList(clientes);
    }

    public ClienteResponse buscarPorId(Long id){
        Cliente cliente = repository.getClientes().stream().filter(cliente1 -> cliente1.getId().equals(id)).findAny().orElseThrow(() -> new RuntimeException());

        return mapper.toDto(cliente);
    }

    public List<ClienteResponse> buscarPorCpfCnpj(String cpfCnpj){
        return repository.getClientes()
                .stream()
                .filter(cliente -> cpfCnpj == null || cpfCnpj.equals(cpfCnpj))
                .map(mapper::toDto)
                .toList();
    }

    public ClienteResponse atualizarCompleto(Long id, ClienteUpdate update){
        Cliente cliente = repository.getClientes()
                .stream()
                .filter(cliente1 -> cliente1.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new RuntimeException());

        mapper.toUpdate(update, cliente);
        return mapper.toDto(cliente);
    }

    public ClienteResponse atualizarParcialmente(Long id, ClientePatch patch){
        Cliente cliente = repository.getClientes()
                .stream()
                .filter(cliente1 -> cliente1.equals(id))
                .findAny()
                .orElseThrow(() -> new RuntimeException());

        mapper.patchEntity(patch, cliente);
        return mapper.toDto(cliente);
    }

    public boolean deletar(Long id){
        return repository.deleteCliente(id);
    }



}
