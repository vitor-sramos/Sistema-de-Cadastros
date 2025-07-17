package vitor.dev.com.Cadastro.Usuario.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record UsuarioResponseDto(

        UUID id,
        @Schema(example = "Vitor Ramos")
        String usuario,
        @Schema(example = "vitor@gmail.com")
        String email
) {
}
