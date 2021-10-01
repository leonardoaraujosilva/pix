package matera.bootcamp.pix.service.usuario;

import lombok.RequiredArgsConstructor;
import matera.bootcamp.pix.domain.model.Usuario;
import matera.bootcamp.pix.domain.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioCrudService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> buscarPaginado(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

}
