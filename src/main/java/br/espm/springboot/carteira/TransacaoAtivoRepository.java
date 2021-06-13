package br.espm.springboot.carteira;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransacaoAtivoRepository extends CrudRepository<TransacaoAtivoModel, String> {

    @Override
    TransacaoAtivoModel save(TransacaoAtivoModel ta);

    @Override
    Optional<TransacaoAtivoModel> findById(String s);

    @Query("SELECT tc from TransacaoAtivoModel tc where tc.idCarteira = :idCarteira order by tc.dtData")
    List<TransacaoAtivoModel> listByCarteira(@Param("idCarteira") String idCarteira);

    
}
