package pares.api_logistica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pares.api_logistica.entity.Cliente;
import pares.api_logistica.entity.Motorista;
import pares.api_logistica.repository.ClienteRepository;
import pares.api_logistica.repository.MotoristaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Tag(
        name = "Motorista",
        description = "Endpoints para cadastro, consulta, atualização e exclusão de motorista"
)

/**
 * Controller responsável pelos endpoints relacionados aos motoristas.
 *
 * <p>Esta classe disponibiliza operações HTTP para listar, consultar,
 * cadastrar, atualizar e remover motoristas.</p>
 *
 * @author Lucas e Marcus
 * @since 1.0
 */

@RestController
@RequestMapping("api/v1/motorista")

public class MotoristaController {

    private final MotoristaRepository motoristas = new MotoristaRepository();
    private final AtomicLong sequencialId = new AtomicLong(1);

    /**
     * Lista motorista cadastrados
     *
     * <p>Lista todos os motoristas </p>
     * @return
     */

    @Operation(
            summary = "lista de motoristas",
            description = "Retorna todos os motoristas criados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Motorista encontrado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Motorista não encontrado"
    )
    @GetMapping("")
    public ResponseEntity<Map<Integer, Motorista>> listar(){
        return ResponseEntity.ok(motoristas.getMotoristas());
    }


    /**
     * Lista Motorista cadastrados
     *
     * <p>Quando a categoria Id é informada, somente os motoristas pertencentes
     * à categoria são retornados.</p>
     *
     * @return resposta HTTP contendo a entidade do id encontrado
     */

    @Operation(
            summary = "Motorista motorista por id",
            description = "Retorna o motorista criado pelo id buscado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Motorista encontrado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Motorista não encontrado"
    )

    @GetMapping("/{id}")
    public ResponseEntity<Motorista>buscarPorId(@PathVariable Long id){
        for( Motorista motorista : motoristas.getMotoristas().values()){
            if(motorista.getId().equals(id)){
                return ResponseEntity.ok(motorista);
            }
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * cadastra motorista
     *
     * <p>Cadastra um motorista novo</p>
     *
     * @return Retorna o objeto que foi criado
     */

    @Operation(
            summary = "Cria um motorista",
            description = "Cria um motorista e gera automaticamente o id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Motorista criado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dados Inválidos"
            )
    })

    @PostMapping
    public ResponseEntity<Motorista> cadastrar(@RequestBody Motorista motorista){
        motorista.setId(sequencialId.getAndIncrement());
        motoristas.postMotorista(motorista);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(motorista);
    }

    /**
     * atuliza motorista
     *
     * <p>Atualiza um motorista</p>
     *
     * @return Retorna o objeto atualizado
     */

    @Operation(
            summary = "Atualiza um motorista",
            description = "Atualiza completamente os atributos de um motorista existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Motorista atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motorista não encontrado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Motorista> atualizar(
            @PathVariable Long id,@RequestBody Motorista motoristaAtualizado
    ){

        return ResponseEntity.ok().body(motoristas.UpdateMotorista(id, motoristaAtualizado));
    }

    /**
     * atuliza alguns atributos desejados não obrigatoriamente todos
     *
     * <p>Atualiza os atributos de um motorista</p>
     *
     * @return Retorna o objeto com os atributos atualizado
     */

    @Operation(
            summary = "Atualiza um motorista",
            description = "Atualiza somente os atributos informados na requisição"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Motorista atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motorista não encontrado"
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Motorista> atualizarParcialmente(
            @PathVariable Long id, @RequestBody Motorista motoristaAtualizadoParcialmente
    ) {
        for (Motorista motorista : motoristas.getMotoristas().values()) {
            if (motorista.getId().equals(id)) {
                if (motoristaAtualizadoParcialmente.getNome() != null) {
                    motorista.setNome(motoristaAtualizadoParcialmente.getNome());
                }
                if (motoristaAtualizadoParcialmente.getCnh() != null) {
                    motorista.setCnh(motoristaAtualizadoParcialmente.getCnh());
                }
                if (motoristaAtualizadoParcialmente.getVeiculo() != null) {
                    motorista.setVeiculo(motoristaAtualizadoParcialmente.getVeiculo());
                }
                if (motoristaAtualizadoParcialmente.getCidadeBase() != null) {
                    motorista.setCidadeBase(motoristaAtualizadoParcialmente.getCidadeBase());
                }
                return ResponseEntity.ok(motorista);
            }
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * deleta motorista
     *
     * <p>Deleta um motorista</p>
     *
     */

    @Operation(
            summary = "Remove um motorista",
            description = "Remove um motorista utilizando seu id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Motorista removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motorista não encontrado"
            )
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        boolean removido = motoristas.deleteMotorista(id);

        return ResponseEntity.ok("removido " + id);
    }

}
