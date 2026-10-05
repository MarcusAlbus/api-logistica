package pares.api_logistica.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pares.api_logistica.dto.*;
import pares.api_logistica.entity.Motorista;
import pares.api_logistica.mapper.MotoristaMapper;
import pares.api_logistica.repository.MotoristaRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoristaService {

    private final MotoristaRepository repository = new MotoristaRepository();
    private final MotoristaMapper mapper;

    public MotoristaResponse cadastrar(MotoristaCreateRequest request){
        Motorista clienteCriado = repository.postMotorista(mapper.toEntity(request));
        return mapper.toDto(clienteCriado);
    }

    public List<MotoristaResponse> listar(){
        List<Motorista> motoristas = repository.getMotoristas();
        return mapper.toResponseList(motoristas);
    }

    public  MotoristaResponse buscarPorId(Long id){
        Motorista motorista = repository.getMotoristas().stream().filter(motorista1 -> motorista1.getId().equals(id)).findAny().orElseThrow(() -> new RuntimeException());

        return mapper.toDto(motorista);
    }

    public MotoristaResponse atualizarCompleto(Long id, MotoristaUpdate update){
        Motorista motorista = repository.getMotoristas()
                .stream()
                .filter(motorista1 -> motorista1.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new RuntimeException());

        mapper.toUpdate(update, motorista);
        return mapper.toDto(motorista);
    }

    public MotoristaResponse atualizarParcialmente(Long id, MotoristaPatch patch){
    Motorista motorista = repository.getMotoristas()
                .stream()
                .filter(motorista1 -> motorista1.equals(id))
                .findAny()
                .orElseThrow(() -> new RuntimeException());

        mapper.pathEntity(patch, motorista);
        return mapper.toDto(motorista);
    }

    public boolean deletar(Long id){
        return repository.deleteMotorista(id);
    }

}
