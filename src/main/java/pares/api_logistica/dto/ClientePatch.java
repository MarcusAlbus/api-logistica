package pares.api_logistica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientePatch(

        @Size(min = 3, max = 100 ,message = "nome deve ter entre 3 e 100 carcterres")
        @Schema(
                description = "Nome",
                example = "Exemplo Lucas"
        )
        String nome,

        @Size (min = 11, max = 14 ,message = "cpf deve 11 caracteres e o cpnj deve ter 14 caracteres")
        @Schema(
                description = "CpfCnpj",
                example = "Exemplo 13282105907"
        )
        String cpfCnpj,

        @Size (min = 3, max = 100 ,message = "endereco deve ter entre 3 e 100 carcterres")
        @Schema(
                description = "Endereco",
                example = "Exemplo Rua Angelino Fonseca"
        )
        String endereco,

        @Size (min = 3, max = 70 ,message = "cidade deve ter entre 3 e 70 carcterres")
        @Schema(
                description = "Cidade",
                example = "Exemplo Jaragua do sul"
        )
        String cidade,

        @Size (min = 3, max = 100 ,message = "estado deve ter entre 3 e 100 carcterres")
        @Schema(
                description = "Estdado",
                example = "Exemplo Santa catarina"
        )
        String estado
) {
}
