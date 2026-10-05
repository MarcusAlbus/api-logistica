package pares.api_logistica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pares.api_logistica.dto.ClienteCreateRequest;
import pares.api_logistica.dto.ClientePatch;
import pares.api_logistica.dto.ClienteResponse;
import pares.api_logistica.dto.ClienteUpdate;
import pares.api_logistica.entity.Cliente;
import pares.api_logistica.repository.ClienteRepository;
import pares.api_logistica.service.ClienteService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Tag(
        name = "Cliente",
        description = "Endpoints para cadastro, consulta, atualização e exclusão de clientes"
)

/**
 * Controller responsável pelos endpoints relacionados aos clientes.
 *
 * <p>Esta classe disponibiliza operações HTTP para listar, consultar,
 * cadastrar, atualizar e remover clientes.</p>
 *
 * @author Lucas e Marcus
 * @since 1.0
 */

@RestController
@RequestMapping("api/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    /**
     * Lista Cliente cadastrados
     *
     * <p>Lista todos os clientes </p>
     * @return
     */

    @Operation(
            summary = "lista de clientes",
            description = "Retorna todos os clientes criados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente não encontrado"
    )
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteResponse>> listar(){
        return ResponseEntity.ok(service.listar());
    }


    /**
     * Lista Clientes cadastrados
     *
     * <p>Quando a categoria Id é informada, somente os clientes pertencentes
     * à categoria são retornados.</p>
     *
     * @return resposta HTTP contendo a entidade do id encontrado
     */

    @Operation(
            summary = "CLiente cliente por id",
            description = "Retorna o cliente criado pelo id buscado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente não encontrado"
    )

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse>buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    /**
     * Lista clientes cadastrados
     *
     * <p>Quando a categoria de status é informada, somente os clientes pertencentes
     * à categoria são retornados.</p>
     *
     * @param cpfCnpj categoria utilizada como filtro.
     * @return resposta HTTP contendo a lista das entidades com o cpf/cnpj solicitado
     */

    @Operation(
            summary = "Lista cliente por cpf/cnpj",
            description = "Retorna o cliente com o cpf/cnpj buscado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente não encontrado"
    )

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscarPorCpfCnpj(@RequestParam(required = false) String cpfCnpj){
        return ResponseEntity.ok(service.buscarPorCpfCnpj(cpfCnpj));
    }

    /**
     * cadastra Cliente
     *
     * <p>Cadastra um cliente novo</p>
     *
     * @return Retorna o objeto que foi criado
     */

    @Operation(
            summary = "Cria um cliente",
            description = "Cria um cliente e gera automaticamente o id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente criado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dados Inválidos"
            )
    })

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody ClienteCreateRequest request){
        ClienteResponse cliente = service.cadastrar(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cliente);
    }

    /**
     * atuliza clientes
     *
     * <p>Atualiza um cliente</p>
     *
     * @return Retorna o objeto atualizado
     */

    @Operation(
            summary = "Atualiza um Cliente",
            description = "Atualiza completamente os atributos de um cliente existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrada"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(
            @PathVariable Long id,@RequestBody ClienteUpdate update
    ){

        return ResponseEntity.ok().body(service.atualizarCompleto(id, update));
    }

    /**
     * atuliza alguns atributos desejados não obrigatoriamente todos
     *
     * <p>Atualiza os atributos de um cliente</p>
     *
     * @return Retorna o objeto com os atributos atualizado
     */

    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza somente os atributos informados na requisição"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarParcialmente(
            @PathVariable Long id, @RequestBody ClientePatch patch
            ){
       return  ResponseEntity.ok().body(service.atualizarParcialmente(id, patch));
    }

    /**
     * deleta cliente
     *
     * <p>Deleta um cliente</p>
     *
     */

    @Operation(
            summary = "Remove um cliente",
            description = "Remove um cliente utilizando seu id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrada"
            )
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        boolean removido = service.deletar(id);

        return ResponseEntity.ok("removido " + removido);
    }

}
