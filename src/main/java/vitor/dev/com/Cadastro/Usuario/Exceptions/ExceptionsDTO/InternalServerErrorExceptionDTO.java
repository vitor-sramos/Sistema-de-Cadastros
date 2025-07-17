package vitor.dev.com.Cadastro.Usuario.Exceptions.ExceptionsDTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record InternalServerErrorExceptionDTO(

        @Schema(example = "2025-07-17T12:36:58.3151371")
        LocalDateTime timestamp,

        @Schema(example = "500")
        int status,

        @Schema(example = "Internal Server Error")
        String error,

        @Schema(example = "Erro interno do servidor")
        String message,

        @Schema(example = "/usuarios/")
        String path
) {}