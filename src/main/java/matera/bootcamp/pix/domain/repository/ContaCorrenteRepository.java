package matera.bootcamp.pix.domain.repository;

import matera.bootcamp.pix.domain.model.ContaCorrente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

    Optional<ContaCorrente> findByUsuarioId(Long id);

    // findAllByAgenciaAndConta e findAllByAgenciaAndContaComJPQL são consultas equivalentes
    // Uma utiliza query methods e a outra jpql
    // As duas, por receberem um pageable retornam uma resposta paginada.

    Page<ContaCorrente> findAllByAgenciaAndConta(Long agencia, Long conta, Pageable pageable);

    @Query("select cc from ContaCorrente cc where cc.agencia = :agencia and cc.conta = :conta")
    Page<ContaCorrente> findAllByAgenciaAndContaComJPQL(Long agencia, Long conta, Pageable pageable);

}
