package matera.bootcamp.pix.domain.repository;

import matera.bootcamp.pix.domain.model.ContaCorrente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

    Optional<ContaCorrente> findByUsuarioId(Long id);

    Optional<ContaCorrente> findByAgenciaAndConta
            (Long agencia, Long conta);

}
