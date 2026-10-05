package pares.api_logistica.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity responsavel por representar uma tabela do banco
 *
 * <p>Esta classe representa os atributos de uma tabela do banco de dados,
 * onde cada variavel seria um atributo da tabela.</p>
 *
 * @author Lucas e Marcus
 * @since 1.0
*/

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Entrega {

    private Long id;
    private Long pedidoId;
    private Long motoristaId;
    private StatusEntrega statusEntrega;
    private String descricao;

}
