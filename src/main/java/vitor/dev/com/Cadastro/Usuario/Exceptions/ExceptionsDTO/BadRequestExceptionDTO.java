package vitor.dev.com.Cadastro.Usuario.Exceptions.ExceptionsDTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record BadRequestExceptionDTO(

        @Schema(example = "2025-07-17T12:34:31.5619429")
        LocalDateTime timestamp,

        @Schema(example = "400")
        int status,

        @Schema(example = "Bad Request")
        String error,

        @Schema(example = "O parâmetro informado '1' ,não é um id válido! O id deve ser do tipo UUID")
        String message,

        @Schema(example = "/usuarios/1")
        String path
) {}