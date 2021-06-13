package br.espm.springboot.carteira;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

import br.espm.springboot.cambio.common.controller.CambioController;
import br.espm.springboot.cambio.common.datatype.Cotacao;
import br.espm.springboot.carteira.common.controller.CarteiraController;
import br.espm.springboot.carteira.common.datatype.Carteira;
import br.espm.springboot.carteira.common.datatype.TransacaoCambio;

@EnableFeignClients(basePackages = "br.espm.springboot.cambio.common.controller")
@RestController
public class CarteiraResource implements CarteiraController {

    @Autowired
    private CambioController cambioController;

    @Override
    public Carteira carteira(String id) {
        Carteira carteira = new Carteira();

        Cotacao cotacao = new Cotacao();
        cotacao.setId(UUID.randomUUID().toString());


        cotacao.setMoeda(cambioController.listMoedas().get(0)); // manter - estabelece a ligação entre microsserviço


        cotacao.setData(new Date());
        cotacao.setValor(4.97);

        TransacaoCambio tCambio = new TransacaoCambio();
        tCambio.setCarteira(null); 
        tCambio.setData(new Date());
        tCambio.setCotacao(cotacao);
        tCambio.setQtd(40);

        List<TransacaoCambio> listaTransacoes = new ArrayList<>();
        listaTransacoes.add(tCambio);


        carteira.setTransacoesCambio(listaTransacoes);
        return carteira;
    }
  
}
