package vitor.dev.com.Cadastro.Usuario.Exceptions.ExceptionsDTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

public record NotFoundExceptionDTO(

        @Schema(example = "2025-07-17T12:50:12.5085243")
        LocalDateTime timestamp,

        @Schema(example = "404")
        int status,

        @Schema(example = "Not Found")
        String error,

        @Schema(example = "Usuário não encontrado com id: 1a5f0081-c931-49d9-b888-fab07fcebd33")
        String message,

        @Schema(example = "/usuarios/1a5f0081-c931-49d9-b888-fab07fcebd33")
        String path
) {}