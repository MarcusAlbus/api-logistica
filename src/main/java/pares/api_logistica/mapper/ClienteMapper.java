package pares.api_logistica.mapper;

import org.springframework.stereotype.Component;
import pares.api_logistica.dto.ClienteCreateRequest;
import pares.api_logistica.dto.ClientePatch;
import pares.api_logistica.dto.ClienteResponse;
import pares.api_logistica.dto.ClienteUpdate;
import pares.api_logistica.entity.Cliente;

import java.util.List;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteCreateRequest request){
        Cliente cliente = new Cliente();
        cliente.setNome(request.nome());
        cliente.setCpfCnpj(request.cpfCnpj());
        cliente.setEndereco(request.endereco());
        cliente.setCidade(request.cidade());
        cliente.setEstado(request.estado());
        return cliente;
    }

    public ClienteResponse toDto(Cliente cliente){
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpfCnpj(),
                cliente.getEndereco(),
                cliente.getCidade(),
                cliente.getEstado()
        );
    }

    public List<ClienteResponse> toResponseList(List<Cliente> clientes){
         return clientes
                 .stream()
                 .map(this::toDto)
                 .toList();
    }


    public void toUpdate(ClienteUpdate update, Cliente cliente){
        cliente.setNome(update.nome());
        cliente.setCpfCnpj(update.cpfCnpj());
        cliente.setEndereco(update.endereco());
        cliente.setCidade(update.cidade());
        cliente.setEstado(update.estado());
    }


    public void patchEntity(ClientePatch patch, Cliente cliente){
        if(patch.nome() != null){
            cliente.setNome(patch.nome());
        }
        if (patch.cpfCnpj() != null){
            cliente.setCpfCnpj(patch.cpfCnpj());
        }
        if (patch.endereco() != null){
            cliente.setEndereco(patch.endereco());
        }
        if(patch.cidade() != null){
            cliente.setCidade(patch.cidade());
        }
        if (patch.estado() != null){
            cliente.setEstado(patch.estado());
        }
    }


}
