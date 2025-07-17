package vitor.dev.com.Cadastro.Usuario.MapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vitor.dev.com.Cadastro.Usuario.Dto.UsuarioRequestDto;
import vitor.dev.com.Cadastro.Usuario.Model.Usuario;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioUpdate {

    void update(UsuarioRequestDto requestDto, @MappingTarget Usuario usuario);
}
