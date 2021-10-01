package matera.bootcamp.pix.rest.chave;

import lombok.RequiredArgsConstructor;
import matera.bootcamp.pix.domain.model.Chave;
import matera.bootcamp.pix.service.chave.ChaveCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChaveCrudApiController {

    private final ChaveCrudService chaveCrudService;

    @PostMapping("/api/v1/chave")
    public ResponseEntity<Chave> cadastrarChave(@RequestBody Chave chave) {

        if(chave.getId() != null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();

        var chaveCadastrada = chaveCrudService
                .cadastrarChave(chave);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(chaveCadastrada);
    }

    @PutMapping("/api/v1/chave")
    public ResponseEntity<Chave> atualizarChave(@RequestBody Chave chave) {

        if(chave.getId() == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();

        var chaveCadastrada = chaveCrudService
                .cadastrarChave(chave);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(chaveCadastrada);
    }

}
