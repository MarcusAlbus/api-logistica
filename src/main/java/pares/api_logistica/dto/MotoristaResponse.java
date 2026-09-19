package pares.api_logistica.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MotoristaResponse(
        @Schema(
                description = "Id",
                example = "Exemplo 1"
        )
        Long id,
        @Schema(
                description = "Nome",
                example = "Exemplo Lucas"
        )
        String nome,
        @Schema(
                description = "cnh",
                example = "434353293"
        )
        String cnh,
        @Schema(
                description = "Veiculo",
                example = "Carro"
        )
        String veiculo,
        @Schema(
                description = "CidadeBase",
                example = "Jaragua do sul"
        )
        String cidadeBase
) {
}
