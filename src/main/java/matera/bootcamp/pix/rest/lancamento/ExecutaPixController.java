package matera.bootcamp.pix.rest.lancamento;

import lombok.RequiredArgsConstructor;
import matera.bootcamp.pix.service.lancamento.LancamentoPixService;
import matera.bootcamp.pix.viewmodel.PixDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExecutaPixController {

    private final LancamentoPixService lancamentoPixService;

    @PostMapping("/api/v1/pix")
    public ResponseEntity<?> executaPix(@RequestBody PixDTO pixRequest) {

        lancamentoPixService.executarPix(pixRequest);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    /**
     *
     * Pra executar um PIX precisamos (exemplos abaixo das linhas do passo a passo):
     *  1. Criar um usuário com conta corrente
     *     POST - http://localhost:8080/api/v1/usuario
     *     {
     *         "nome": "Leonardo",
     *         "sobrenome": "Silva",
     *         "contaCorrente": {
     *             "agencia": 1,
     *             "conta": 100,
     *             "saldo": 1000.00
     *         }
     *     }
     *
     *  2. Criar um usuário com conta corrente e saldo suficiente pra mandar o PIX
     *     POST - http://localhost:8080/api/v1/usuario
     *     {
     *         "nome": "Ederlon",
     *         "sobrenome": "Barbosa",
     *         "contaCorrente": {
     *             "agencia": 1,
     *             "conta": 300,
     *             "saldo": 15000.00
     *         }
     *     }
     *
     *  3. Cadastrar uma chave para o usuário recebedor
     *      POST - http://localhost:8080/api/v1/chave
     *      {
     *          "tipoChave": "EMAIL",
     *          "valor": "leonardo.araujo@matera.com",
     *          "contaCorrente": {
     *              "id": 1
     *          }
     *      }
     *
     *  4. Executar PIX informando a chave do recebedor e agencia e conta do pagador
     *     POST - http://localhost:8080/api/v1/pix
     *     {
     *          "chave": "leonardo.araujo@matera.com",
     *          "valor": "15000.00",
     *          "pagador": {
     *              "agencia": 1,
     *              "conta": 1000
     *          }
     *     }
     *
     *  5. Consultar saldo das contas dos usuários
     *      5.1 Para o recebedor
     *         GET - http://localhost:8080/api/v1/usuario/1/conta
     *      5.2 Para o pagador
     *         GET - http://localhost:8080/api/v1/usuario/2/conta
     *      5.3 Pode também consultar no banco em http://localhost:8080/h2-console
     *         Executar o seguinte select vai mostrar dados do usuário e conta com saldo
     *         select * from USUARIO u inner join CONTA_CORRENTE cc ON cc.ID = u.CONTA_CORRENTE_ID
     *
     */

}