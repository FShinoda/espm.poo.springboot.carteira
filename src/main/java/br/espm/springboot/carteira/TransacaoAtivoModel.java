package br.espm.springboot.carteira;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import br.espm.springboot.ativo.common.datatype.Ativo;
import br.espm.springboot.carteira.common.datatype.Carteira;
import br.espm.springboot.carteira.common.datatype.TransacaoAtivo;
import br.espm.springboot.carteira.common.datatype.TransacaoTipo;

@Entity
@Table(name = "transacaoativo")
public class TransacaoAtivoModel {

    @Id
    @Column(name = "tatiCodi")
    private String idTransacaoAtivo;

    @Column(name = "carCodi")
    private String idCarteira;

    @Column(name = "atvCodi")
    private String idAtivo;

    @Column(name = "tatiData")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtData;

    @Column(name = "tatiQtd")
    private BigDecimal nrQtd;

    @Column(name = "tatiTipo")
    private Integer nrTipo;

    public TransacaoAtivoModel(){} //Cosntruto vazio

    public TransacaoAtivoModel(TransacaoAtivo ta){
        this.idTransacaoAtivo = ta.getId();
    }

    public TransacaoAtivo to() {
        Carteira carteira = new Carteira();
        carteira.setId(idCarteira);

        Ativo ativo = new Ativo();
        ativo.setId(idAtivo);

        TransacaoAtivo ta = new TransacaoAtivo();
        ta.setId(idTransacaoAtivo);
        ta.setData(dtData);
        ta.setQtd(nrQtd);
        ta.setTipo(1 == nrTipo ? TransacaoTipo.VENDA : TransacaoTipo.COMPRA);
        ta.setCarteira(carteira);
        ta.setAtivo(ativo);
        return ta;
    }
    
}
