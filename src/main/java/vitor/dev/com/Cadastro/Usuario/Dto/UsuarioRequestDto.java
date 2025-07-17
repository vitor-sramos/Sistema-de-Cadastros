package vitor.dev.com.Cadastro.Usuario.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDto(

        @Schema(example = "Vitor Ramos")
        @NotBlank(message = "Usuário é obrigatório")
        String usuario,

        @Schema(example = "vitor@gmail.com")
        @Email(message = "E-mail inválido")
        @NotBlank(message = "E-mail é obrigatório")
        String email,

        @Schema(example = "12345")
        @NotBlank(message = "Senha é obrigatório")
                @Size(min = 5, message = "A senha deve conter no mínimo 5 caracteres")
        String senha
) {}