package pares.api_logistica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MotoristaPath(
        @NotBlank(message = "o nome nao pode ser vazio")
        @Size(min = 3, max = 100 ,message = "nome deve ter entre 3 e 100 caracterres")
        @Schema(
                description = "Nome",
                example = "Exemplo Lucas"
        )
        String nome,
        @NotBlank (message = "a cnh nao pode ser vazia")
        @Size (min = 9, max = 9 ,message = "cnh deve ter 9 caracterres")
        @Schema(
                description = "cnh",
                example = "434353293"
        )
        String cnh,
        @NotBlank (message = "o  veiculo nao pode ser vazio")
        @Size (min = 3, max = 100 ,message = "veiculo deve ter entre 3 e 100 carcterres")
        @Schema(
                description = "Veiculo",
                example = "Carro"
        )
        String veiculo,
        @NotBlank (message = "a cidade nao pode ser vazia")
        @Size (min = 3, max = 70 ,message = "cidade deve ter entre 3 e 100 caracterres")
        @Schema(
                description = "CidadeBase",
                example = "Jaragua do sul"
        )
        String cidadeBase
) {
}
