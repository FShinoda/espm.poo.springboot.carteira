package br.espm.springboot.carteira;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.espm.springboot.ativo.common.controller.AtivoController;
import br.espm.springboot.cambio.common.controller.CambioController;
import br.espm.springboot.carteira.common.datatype.Carteira;

@Component
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CambioController cambioController;

    @Autowired
    private TransacaoCambioService transacaoCambioService;

    @Autowired
    private AtivoController ativoController;

    @Autowired
    private TransacaoAtivoService transacaoAtivoService;



    public Carteira create(Carteira c) {
        c.setId(UUID.randomUUID().toString());
        return carteiraRepository.save(new CarteiraModel(c)).to();
    }

    public Carteira findBy(String id) {
        Carteira c = carteiraRepository
                .findById(id)
                .map(CarteiraModel::to)
                .orElse(null);
        if (c != null) {
            // Aqui e uma amarracao mapping 1 .. n
            c.setTransacoesCambio(transacaoCambioService.listByCarteira(c.getId()));
            c.getTransacoesCambio().forEach(transacaoCambio -> {
                transacaoCambio.setCotacao(cambioController.getCotacao(transacaoCambio.getCotacao().getId()));
            });
            // c.setTransacoesAtivo(...);
            c.setTransacoesAtivo(transacaoAtivoService.listByCarteira(c.getId()));
            c.getTransacoesAtivo().forEach(transacaoAtivo -> {
                transacaoAtivo.setAtivo(ativoController.getAtivo(transacaoAtivo.getAtivo().getId()));
            });
        }
        return c;
    }
    
}
