package br.espm.springboot.carteira;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

import br.espm.springboot.carteira.common.controller.CarteiraController;
import br.espm.springboot.carteira.common.datatype.Carteira;
import br.espm.springboot.carteira.common.datatype.TransacaoAtivo;
import br.espm.springboot.carteira.common.datatype.TransacaoBean;
import br.espm.springboot.carteira.common.datatype.TransacaoCambio;

@EnableFeignClients(basePackages = {"br.espm.springboot.cambio.common.controller", "br.espm.springboot.ativo.common.controller"})
@RestController
public class CarteiraResource implements CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private TransacaoCambioService transacaoCambioService;

    @Autowired
    private TransacaoAtivoService transacaoAtivoService;

    @Override
    public List<Carteira> listCarteiras() {
        return null;
    }

    @Override
    public Carteira carteira(String id) {
        return carteiraService.findBy(id);
    }

    @Override
    public Carteira create(Carteira carteira) {
        Carteira c = new Carteira();
        c.setSaldo(BigDecimal.ZERO);
        return carteiraService.create(c);
    }

    @Override
    public TransacaoCambio cambioComprar(String idCarteira, TransacaoBean bean) {
        return transacaoCambioService.comprar(idCarteira, bean);
    }

    @Override
    public TransacaoCambio cambioVender(String idCarteira, TransacaoBean bean) {
        return transacaoCambioService.vender(idCarteira, bean);
    }

    @Override
    public TransacaoAtivo ativoComprar(String idCarteira, TransacaoBean bean) {
        return transacaoAtivoService.comprar(idCarteira, bean);
    }

    @Override
    public TransacaoAtivo ativoVender(String idCarteira, TransacaoBean bean) {
        return transacaoAtivoService.vender(idCarteira, bean);
    }

  
}
