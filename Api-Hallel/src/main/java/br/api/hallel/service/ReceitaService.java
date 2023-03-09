package br.api.hallel.service;

import br.api.hallel.model.Financeiro;
import br.api.hallel.model.ReceitaFinanceira;
import br.api.hallel.payload.requerimento.ReceitaReq;
import br.api.hallel.repository.ReceitaFinanceiraRepository;
import br.api.hallel.service.interfaces.ReceitaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService implements ReceitaInterface {

    @Autowired
    ReceitaFinanceiraRepository repository;
    @Autowired
    FinanceiroService service;

    //CRUD DE RECEITAS FINANCEIRO DA COMUNIDADE
    @Override
    public ReceitaFinanceira createReceita(ReceitaFinanceira receitaFinanceira) {

        //SALVA A RECEITA NUM ARRAY DE FINANCEIRO
        this.service.salvarReceita(receitaFinanceira);

        //SALVA A RECEITA NA TABELA 'RECEITA' DO BD
        return this.repository.insert(receitaFinanceira);
    }

    @Override
    public ReceitaFinanceira listById(String id) {
        Optional<ReceitaFinanceira> optional = this.repository.findById(id);

        if(optional.isPresent()){
            ReceitaFinanceira receita = optional.get();
            return receita;
        }

        return null;
    }

    @Override
    public List<ReceitaFinanceira> listAll() {
        return this.repository.findAll();
    }

    @Override
    public ReceitaFinanceira update(String id, ReceitaReq receita) {

        Optional<ReceitaFinanceira> optional = this.repository.findById(id);

        if(optional.isPresent()){

            return this.repository.save(receita.toGasto());
        }

        return null;
    }

    @Override
    public void deleteReceita(String id) {
        Optional<ReceitaFinanceira> optional = this.repository.findById(id);

        if(optional.isPresent()){
            ReceitaFinanceira receita = optional.get();

            Financeiro financeiro = this.service.getFinanceiro();
            financeiro.getValorReceitas().remove(receita.getValor());

            this.service.update(financeiro);

            this.repository.deleteById(id);
        }else{

        System.out.println("Não existe nenhum usuário de id :"+id);
        }
    }
}
