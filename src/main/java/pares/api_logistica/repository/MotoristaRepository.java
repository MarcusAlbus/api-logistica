package pares.api_logistica.repository;

import pares.api_logistica.entity.Cliente;
import pares.api_logistica.entity.Motorista;

import java.util.HashMap;
import java.util.Map;

public class MotoristaRepository {

    private Map<Long, Motorista> motoristas;

    public MotoristaRepository(){

        motoristas = new HashMap<Long, Motorista>();

    }

    public Map<Long, Motorista> getMotoristas(){ return  motoristas;}

    public Motorista getMotoristabyId(Long id){
        for(Motorista motorista : motoristas.values()){
            if(motorista.getId() == id){
                return motorista;
            }
        }
        throw  new RuntimeException();
    }

    public void postMotorista(Motorista motorista){motoristas.put(motorista.getId(), motorista);}

    public Motorista UpdateMotorista(Long id, Motorista motoristaAtualizado){
        Motorista motorista = getMotoristabyId(id);
        motorista.setNome(motoristaAtualizado.getNome());
        motorista.setCnh(motoristaAtualizado.getCnh());
        motorista.setVeiculo(motoristaAtualizado.getVeiculo());
        motorista.setCidadeBase(motoristaAtualizado.getCidadeBase());
        return motorista;
    }

    public Motorista patchMotorista(Long id, Motorista motoristaAtualizadoParcialmente) {
        Motorista motorista = getMotoristabyId(id);
        motorista.setNome(motoristaAtualizadoParcialmente.getNome());
        motorista.setCnh(motoristaAtualizadoParcialmente.getCnh());
        motorista.setCidadeBase(motoristaAtualizadoParcialmente.getCidadeBase());
        motorista.setVeiculo(motoristaAtualizadoParcialmente.getVeiculo());
        return motorista;
    }

    public boolean deleteMotorista(Long id){
        motoristas.remove(id);
        return false;
    }
}

