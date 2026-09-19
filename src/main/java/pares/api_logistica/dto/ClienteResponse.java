package pares.api_logistica.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ClienteResponse (
        @Schema(
                description = "ID",
                example = "Exemplo 1"
        )
        Long id,
        @Schema(
                description = "Nome",
                example = "Exemplo Lucas"
        )
        String nome,
        @Schema(
                description = "CpfCnpj",
                example = "Exemplo 13282105907"
        )
        String cpfCnpj,
        @Schema(
                description = "Endereco",
                example = "Exemplo Rua Angelino Fonseca"
        )
        String endereco,
        @Schema(
                description = "Cidade",
                example = "Exemplo Jaragua do sul"
        )
        String cidade,
        @Schema(
                description = "Estdado",
                example = "Exemplo Santa catarina"
        )
        String estado
) {
}
