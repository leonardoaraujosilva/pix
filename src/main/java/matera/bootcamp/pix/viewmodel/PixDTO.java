package matera.bootcamp.pix.viewmodel;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PixDTO {

    private String chave;

    private BigDecimal valor;

    private PagadorDTO pagador;

}
