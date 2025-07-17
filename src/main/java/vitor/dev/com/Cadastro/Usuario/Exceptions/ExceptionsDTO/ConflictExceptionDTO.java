package vitor.dev.com.Cadastro.Usuario.Exceptions.ExceptionsDTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ConflictExceptionDTO(

        @Schema(example = "2025-07-17T12:42:24.2584204")
        LocalDateTime timestamp,

        @Schema(example = "409")
        int status,

        @Schema(example = "Conflict")
        String error,

        @Schema(example = "Usuário já existe")
        String message,

        @Schema(example = "/usuarios")
        String path
) {}