package br.espm.springboot.carteira;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.espm.springboot.ativo.common.controller.AtivoController;
import br.espm.springboot.ativo.common.datatype.Ativo;
import br.espm.springboot.ativo.common.datatype.Empresa;
import br.espm.springboot.carteira.common.datatype.Carteira;
import br.espm.springboot.carteira.common.datatype.TransacaoAtivo;
import br.espm.springboot.carteira.common.datatype.TransacaoBean;
import br.espm.springboot.carteira.common.datatype.TransacaoTipo;

@Component
public class TransacaoAtivoService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private AtivoController ativoController;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private TransacaoAtivoRepository transacaoAtivoRepository;


    public List<TransacaoAtivo> listByCarteira(String idCarteira) {
        List<TransacaoAtivo> l = transacaoAtivoRepository
                .listByCarteira(idCarteira).stream()
                .map(TransacaoAtivoModel::to)
                .collect(Collectors.toList());
        return l;
    }

    public TransacaoAtivo comprar(String idCarteira, TransacaoBean bean) {
        Carteira c = carteiraService.findBy(idCarteira);
        if (c == null) {
            throw new RuntimeException("Carteira nao existe: " + idCarteira);
        }

        Date agora = new Date();

        Empresa empresa = ativoController.listEmpresas(bean.getSimbolo());
        if (empresa == null) {
            throw new RuntimeException("Empresa nao existe: " + bean.getSimbolo());
        }

        Ativo ativo = ativoController.ativo(empresa.getSimbolo(), sdf.format(agora));
        if (ativo == null) {
            throw new RuntimeException("Ativo nao existe: " + sdf.format(agora));
        }

        TransacaoAtivo tc = new TransacaoAtivo();
        tc.setId(UUID.randomUUID().toString());
        tc.setCarteira(c);
        tc.setAtivo(ativo);
        tc.setTipo(TransacaoTipo.COMPRA);
        tc.setQtd(bean.getQtd());
        tc.setData(agora);

        return transacaoAtivoRepository.save(new TransacaoAtivoModel(tc)).to();
    }

    public TransacaoAtivo vender(String idCarteira, TransacaoBean bean) {
        Carteira c = carteiraService.findBy(idCarteira);
        if (c == null) {
            throw new RuntimeException("Carteira nao existe: " + idCarteira);
        }

        Date agora = new Date();

        Empresa empresa = ativoController.listEmpresas(bean.getSimbolo());
        if (empresa == null) {
            throw new RuntimeException("Empresa nao existe: " + bean.getSimbolo());
        }

        Ativo Ativo = ativoController.ativo(empresa.getSimbolo(), sdf.format(agora));
        if (Ativo == null) {
            throw new RuntimeException("Ativo nao existe: " + sdf.format(agora));
        }

        if (BigDecimal.ZERO.compareTo(bean.getLimite()) <= 0
                && bean.getLimite().compareTo(Ativo.getValor()) > 0) {
            throw new RuntimeException("Ativo limite, atual: " + Ativo.getValor());
        }

        TransacaoAtivo tc = new TransacaoAtivo();
        tc.setId(UUID.randomUUID().toString());
        tc.setCarteira(c);
        tc.setAtivo(Ativo);
        tc.setTipo(TransacaoTipo.VENDA);
        tc.setQtd(bean.getQtd());
        tc.setData(agora);

        return transacaoAtivoRepository.save(new TransacaoAtivoModel(tc)).to();
    }

    
}
