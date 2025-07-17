package vitor.dev.com.Cadastro.Usuario.Exceptions.ExceptionsDTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

public record BadRequestInvalidExceptionDTO(

        @Schema(example = "2025-07-17T12:45:12.4883565")
        LocalDateTime timestamp,

        @Schema(example = "400")
        int status,

        @Schema(
                description = "Campos com erros e mensagens correspondentes",
                example = "{\"email\": \"E-mail é obrigatório\"}"
        )
        Map<String, String> error,

        @Schema(example = "Bad Request")
        String message,

        @Schema(example = "/usuarios")
        String path
) {}