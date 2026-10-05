package pares.api_logistica.mapper;

import org.springframework.stereotype.Component;
import pares.api_logistica.dto.MotoristaCreateRequest;
import pares.api_logistica.dto.MotoristaPatch;
import pares.api_logistica.dto.MotoristaResponse;
import pares.api_logistica.dto.MotoristaUpdate;
import pares.api_logistica.entity.Motorista;

import java.util.List;

@Component
public class MotoristaMapper {

    public MotoristaResponse toDto(Motorista motorista){
        return new MotoristaResponse(
                motorista.getId(),
                motorista.getNome(),
                motorista.getCnh(),
                motorista.getVeiculo(),
                motorista.getCidadeBase()
        );
    }

    public Motorista toEntity(MotoristaCreateRequest request){
        Motorista motorista = new Motorista();
        motorista.setNome(request.nome());
        motorista.setCnh(request.cnh());
        motorista.setVeiculo(request.veiculo());
        motorista.setCidadeBase(request.cidadeBase());
        return motorista;
    }

    public List<MotoristaResponse> toResponseList(List<Motorista> motoristas){
        return motoristas
                .stream()
                .map(this::toDto)
                .toList();
    }

    public void toUpdate(MotoristaUpdate update, Motorista motorista){
        motorista.setNome(update.nome());
        motorista.setCnh(update.cnh());
        motorista.setVeiculo(update.veiculo());
        motorista.setCidadeBase(update.cidadeBase());
    }

    public void pathEntity(MotoristaPatch patch , Motorista motorista){
        if(patch.nome() != null){
            motorista.setNome(patch.nome());
        }
        if(patch.cnh() != null){
            motorista.setCnh(patch.cnh());
        }
        if (patch.veiculo() != null){
            motorista.setVeiculo(patch.veiculo());
        }
        if(patch.cidadeBase() != null){
            motorista.setCidadeBase(patch.cidadeBase());
        }
    }


}
