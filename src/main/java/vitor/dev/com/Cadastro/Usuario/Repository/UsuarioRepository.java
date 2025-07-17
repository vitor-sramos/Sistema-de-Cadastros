package vitor.dev.com.Cadastro.Usuario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitor.dev.com.Cadastro.Usuario.Model.Usuario;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByUsuario(String usuario);
    boolean existsByEmail(String email);
}
