package vitor.dev.com.Cadastro.Usuario.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioRequestDto;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioRequestUpdateDto;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioResponseDto;
import vitor.dev.com.Cadastro.Usuario.Exceptions.CustomExceptions.RecursoDuplicadoException;
import vitor.dev.com.Cadastro.Usuario.Exceptions.CustomExceptions.RecursoNaoEncontradoException;
import vitor.dev.com.Cadastro.Usuario.MapStruct.UsuarioMapper;
import vitor.dev.com.Cadastro.Usuario.Model.Usuario;
import vitor.dev.com.Cadastro.Usuario.Repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository repository;
    private UsuarioMapper mapper;


    // CADASTRAR USUÁRIO
    public UsuarioResponseDto usuarioSave(UsuarioRequestDto requestDto) {

        if (repository.existsByUsuario(requestDto.usuario())) {
            throw new RecursoDuplicadoException("Usuário já existe");
        }

        if (repository.existsByEmail(requestDto.email())) {
            throw new RecursoDuplicadoException("E-mail já existe");
        }

        return mapper.paraUsuarioResponseDto(repository.save(
                mapper.paraUsuario(requestDto)
        ));
    }

    //MOSTRAR TODOS OS USUÁRIOS
    public List<UsuarioResponseDto> usuarioList() {
        return mapper.listUsuarioResponseDto(repository.findAll());
    }

    // MOSTRAR USUÁRIO POR ID
    public UsuarioResponseDto usuarioById(UUID id) {
        return mapper.paraUsuarioResponseDto(repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id)));
    }

    // ATUALIZAR USUÁRIO
    public UsuarioResponseDto usuarioUpdate(UsuarioRequestUpdateDto requestDto, UUID id) {
        Usuario usuario = repository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id)
        ) ;

        if(repository.existsByUsuario(requestDto.usuario()) &&
                !usuario.getUsuario().equals(requestDto.usuario())) {
            throw new RecursoDuplicadoException("Usuário já existe");
        }

        if (repository.existsByEmail(requestDto.email()) &&
                !usuario.getEmail().equals(requestDto.email())) {
            throw new RecursoDuplicadoException("E-mail já existe");
        }

        mapper.usuarioUpdate(requestDto, usuario);
        repository.save(usuario);
        return mapper.paraUsuarioResponseDto(usuario);
    }

    // DELETAR USUÁRIO
    public void usuarioDelete(UUID id) {
        usuarioById(id);
        repository.deleteById(id);
    }
}