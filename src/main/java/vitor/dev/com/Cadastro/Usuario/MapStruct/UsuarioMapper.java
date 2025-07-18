package vitor.dev.com.Cadastro.Usuario.MapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioRequestDto;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioRequestUpdateDto;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioResponseDto;
import vitor.dev.com.Cadastro.Usuario.Model.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    Usuario paraUsuario(UsuarioRequestDto requestDto);

    UsuarioResponseDto paraUsuarioResponseDto(Usuario usuario);

    List<UsuarioResponseDto> listUsuarioResponseDto(List<Usuario> list);

    @Mapping(target = "id", ignore = true)
    void usuarioUpdate(UsuarioRequestUpdateDto requestUpdateDto, @MappingTarget Usuario usuario);
}