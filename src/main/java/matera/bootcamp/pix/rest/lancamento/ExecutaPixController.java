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


}
