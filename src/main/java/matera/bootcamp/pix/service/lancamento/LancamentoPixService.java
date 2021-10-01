package matera.bootcamp.pix.service.lancamento;

import lombok.RequiredArgsConstructor;
import matera.bootcamp.pix.domain.model.Chave;
import matera.bootcamp.pix.domain.model.ContaCorrente;
import matera.bootcamp.pix.domain.repository.ChaveRepository;
import matera.bootcamp.pix.domain.repository.ContaCorrenteRepository;
import matera.bootcamp.pix.viewmodel.PixDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LancamentoPixService {

    private final ChaveRepository chaveRepository;
    private final ContaCorrenteRepository contaCorrenteRepository;

    @Transactional
    public void executarPix(PixDTO pixRequest) {
        var chaveRecebedor = consultaEValidaChave(pixRequest);
        var contaCorrentePagador = consultaEValidaContaCorrentePagador(pixRequest);
        var contaCorrenteRecebedor = chaveRecebedor.getContaCorrente();
        removeSaldoPagador(pixRequest, contaCorrentePagador);
        adicionaSaldoRecebedor(pixRequest, contaCorrenteRecebedor);
    }

    private void adicionaSaldoRecebedor(PixDTO pixRequest, ContaCorrente contaCorrenteRecebedor) {
        var novoSaldoRecebedor = contaCorrenteRecebedor
                .getSaldo()
                .add(pixRequest.getValor());
        contaCorrenteRecebedor.setSaldo(novoSaldoRecebedor);
        contaCorrenteRepository.save(contaCorrenteRecebedor);
    }

    private void removeSaldoPagador(PixDTO pixRequest, ContaCorrente contaCorrentePagador) {
        var novoSaldoPagador = contaCorrentePagador
                .getSaldo()
                .subtract(pixRequest.getValor());
        contaCorrentePagador.setSaldo(novoSaldoPagador);
        contaCorrenteRepository.save(contaCorrentePagador);
    }

    private Chave consultaEValidaChave(PixDTO pixRequest) {
        var chaveEncontrada = chaveRepository
                .findByValor(pixRequest.getChave());

        if(chaveEncontrada.isEmpty())
            throw new RuntimeException("Chave não encontrada");

        return chaveEncontrada.get();
    }

    private ContaCorrente consultaEValidaContaCorrentePagador(PixDTO pixRequest) {
        var contaCorrenteOptional = contaCorrenteRepository
                .findByAgenciaAndConta(
                        pixRequest.getPagador().getAgencia(),
                        pixRequest.getPagador().getConta()
                );

        if(contaCorrenteOptional.isEmpty())
            throw new RuntimeException("Conta corrente não encontrada");

        validaSaldoPagador(pixRequest, contaCorrenteOptional);

        return contaCorrenteOptional.get();
    }

    private void validaSaldoPagador(PixDTO pixRequest, Optional<ContaCorrente> contaCorrenteOptional) {
        if(contaCorrenteOptional.get().getSaldo()
                .compareTo(pixRequest.getValor()) < 0)
            throw new RuntimeException("Sem saldo suficiente");
    }

}
