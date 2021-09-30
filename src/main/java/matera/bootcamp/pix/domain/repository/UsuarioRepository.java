package matera.bootcamp.pix.domain.repository;

import matera.bootcamp.pix.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
