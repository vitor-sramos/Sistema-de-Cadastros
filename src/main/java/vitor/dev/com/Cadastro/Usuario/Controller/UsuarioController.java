package vitor.dev.com.Cadastro.Usuario.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioRequestDto;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioResponseDto;
import vitor.dev.com.Cadastro.Usuario.Exceptions.ExceptionsDTO.*;
import vitor.dev.com.Cadastro.Usuario.Service.UsuarioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Cadastro de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(
            summary = "Cadastra usuário",
            description = "Informe os campos para cadastrar o usuário",
            method = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida - campos incorretos ou parâmetro inválido",
                    content = @Content(schema = @Schema(implementation = BadRequestInvalidExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro inválido - tipo incorreto (ex: UUID inválido)",
                    content = @Content(schema = @Schema(implementation = BadRequestExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado ou rota inexistente",
                    content = @Content(schema = @Schema(implementation = NotFoundExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Recurso duplicado",
                    content = @Content(schema = @Schema(implementation = ConflictExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = InternalServerErrorExceptionDTO.class))
            )
    })

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> saveUsuario(@Valid @RequestBody UsuarioRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.usuarioSave(requestDto));
    }

    @Operation(
            summary = "Lista usuários",
            description = "Mostra todos os usuários cadastrados",
            method = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseDto.class))
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado ou rota inexistente",
                    content = @Content(schema = @Schema(implementation = NotFoundExceptionDTO.class))
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = InternalServerErrorExceptionDTO.class))
            )
    })

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> usuarioList() {
        return ResponseEntity.ok(service.usuarioList());
    }

    @Operation(
            summary = "Busca usuário pelo id",
            description = "Informe o id do usuário que deseja buscar",
            method = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseDto.class))
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro inválido - tipo incorreto (ex: UUID inválido)",
                    content = @Content(schema = @Schema(implementation = BadRequestExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado ou rota inexistente",
                    content = @Content(schema = @Schema(implementation = NotFoundExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = InternalServerErrorExceptionDTO.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> usuarioById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.usuarioById(id));
    }

    @Operation(
            summary = "Atualiza usuário pelo id",
            description = "informe o id do usuário que deseja atualizar",
            method = "PATCH"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida - campos incorretos ou parâmetro inválido",
                    content = @Content(schema = @Schema(implementation = BadRequestInvalidExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro inválido - tipo incorreto (ex: UUID inválido)",
                    content = @Content(schema = @Schema(implementation = BadRequestExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado ou rota inexistente",
                    content = @Content(schema = @Schema(implementation = NotFoundExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Recurso duplicado",
                    content = @Content(schema = @Schema(implementation = ConflictExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = InternalServerErrorExceptionDTO.class))
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> usuarioUpdate(@RequestBody UsuarioRequestDto requestDto,
                                                             @PathVariable UUID id) {
        service.usuarioById(id);
        return ResponseEntity.ok(service.usuarioUpdate(requestDto, id));
    }

    @Operation(
            summary = "Deleta usuário pelo id",
            description = "informe o id do usuário que deseja deletar",
            method = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro inválido - tipo incorreto (ex: UUID inválido)",
                    content = @Content(schema = @Schema(implementation = BadRequestExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado ou rota inexistente",
                    content = @Content(schema = @Schema(implementation = NotFoundExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = InternalServerErrorExceptionDTO.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> usuarioDelete(@PathVariable UUID id) {
        service.usuarioDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}