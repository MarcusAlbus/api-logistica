package pares.api_logistica.repository;

import pares.api_logistica.entity.Cliente;
import pares.api_logistica.entity.Motorista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MotoristaRepository {
    private final AtomicLong sequencialId = new AtomicLong(1);
    private List<Motorista> motoristas;

    public MotoristaRepository(){

        motoristas = new ArrayList<Motorista>();

    }

    public List<Motorista> getMotoristas(){ return  motoristas;}


    public Motorista postMotorista(Motorista motorista){
        motorista.setId(sequencialId.getAndIncrement());
        motoristas.add(motorista);
        return motorista;
    }

    public boolean deleteMotorista(Long id){
        motoristas.remove(id);
        return false;
    }
}

