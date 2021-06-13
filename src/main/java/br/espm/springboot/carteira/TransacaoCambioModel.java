package br.espm.springboot.carteira;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import br.espm.springboot.cambio.common.datatype.Cotacao;
import br.espm.springboot.carteira.common.datatype.Carteira;
import br.espm.springboot.carteira.common.datatype.TransacaoCambio;
import br.espm.springboot.carteira.common.datatype.TransacaoTipo;

@Entity
@Table(name = "transacaocambio")
public class TransacaoCambioModel {

    @Id
    @Column(name = "tcamCodi")
    private String idTransacaoCambio;

    @Column(name = "carCodi")
    private String idCarteira;

    @Column(name = "cotCodi")
    private String idCotacao;

    @Column(name = "tcamData")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtData;

    @Column(name = "tcamQtd")
    private BigDecimal nrQtd;

    @Column(name = "tcamTipo")
    private Integer nrTipo;

    public TransacaoCambioModel() { // Construtor vaizo

    }

    public TransacaoCambioModel(TransacaoCambio tc) {
        this.idTransacaoCambio = tc.getId();
        this.dtData = tc.getData();
        this.idCarteira = tc.getCarteira().getId();
        this.idCotacao = tc.getCotacao().getId();
        this.nrTipo = tc.getTipo().equals(TransacaoTipo.VENDA) ? 1 : 2;
        this.nrQtd = tc.getQtd();
    }

    public TransacaoCambio to() {
        Carteira carteira = new Carteira();
        carteira.setId(idCarteira);

        Cotacao cotacao = new Cotacao();
        cotacao.setId(idCotacao);

        TransacaoCambio tc = new TransacaoCambio();
        tc.setId(idTransacaoCambio);
        tc.setData(dtData);
        tc.setQtd(nrQtd);
        tc.setTipo(1 == nrTipo ? TransacaoTipo.VENDA : TransacaoTipo.COMPRA);
        tc.setCarteira(carteira);
        tc.setCotacao(cotacao);
        return tc;
    }
    
}
