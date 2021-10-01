package matera.bootcamp.pix.domain.repository;

import matera.bootcamp.pix.domain.model.Chave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChaveRepository extends JpaRepository<Chave, Long> {

}
