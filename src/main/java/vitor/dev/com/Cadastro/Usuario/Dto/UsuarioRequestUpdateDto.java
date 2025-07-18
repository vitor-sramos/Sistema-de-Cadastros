package vitor.dev.com.Cadastro.Usuario.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestUpdateDto(

        @NotBlank(message = "Usuário é obrigatório")
        String usuario,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "E-mail é obrigatório")
        String email
) {
}
