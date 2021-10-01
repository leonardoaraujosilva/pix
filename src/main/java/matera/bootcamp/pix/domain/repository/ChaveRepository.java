package matera.bootcamp.pix.domain.repository;

import matera.bootcamp.pix.domain.model.Chave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChaveRepository extends JpaRepository<Chave, Long> {

    @Query("select c from Chave c " +
            "    inner join ContaCorrente cc on cc.id = c.contaCorrente.id" +
            "    inner join Usuario u on cc.id = u.contaCorrente.id " +
            "    where u.id = :id")
    List<Chave> findAllByUsuarioId(Long id);

    Optional<Chave> findByValor(String valor);

    @Query("select c from Chave c where c.valor = :valor")
    Optional<Chave> findByValorUsandoJPQL(String valor);

}
