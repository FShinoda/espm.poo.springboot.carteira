package br.espm.springboot.carteira;

import java.math.BigDecimal;

import javax.persistence.*;

import br.espm.springboot.carteira.common.datatype.Carteira;

@Entity
@Table(name = "carteira")
public class CarteiraModel {

    @Id
    @Column(name = "carCodi")
    private String idCarteira;

    @Column(name = "usuCodi") // --- Não temos usuario!!
    private String idUsuario;

    @Column(name = "vr_saldo")
    private BigDecimal vrSaldo;

    public CarteiraModel() { // Construtor vazio

    }

    public CarteiraModel(Carteira c) {
        this.idCarteira = c.getId();
        // this.idUsuario = c.getUsuario.getId();  --- Não temos usuario!!
        this.vrSaldo = c.getSaldo();
    }

    public Carteira to() {
        // Usuario usuario = new Usuario(); --- Não temos usuario!!
        // usuario.setId(idUsuario); --- Não temos usuario!!

        Carteira c = new Carteira();
        c.setId(this.idCarteira);
        c.setSaldo(this.vrSaldo);
        // c.setUsuario(usuario); --- Não temos usuario!!
        return c;
    }


    
}
