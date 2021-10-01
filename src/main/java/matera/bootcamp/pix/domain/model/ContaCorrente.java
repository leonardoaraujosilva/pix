package matera.bootcamp.pix.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long agencia;

    @Column(nullable = false)
    private Long conta;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal saldo = new BigDecimal("0.00");

    @OneToOne(mappedBy = "contaCorrente")
    @JsonIgnore
    private Usuario usuario;

}
