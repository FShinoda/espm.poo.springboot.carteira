package br.espm.springboot.carteira;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CarteiraRepository extends CrudRepository<CarteiraModel, String>{
    
    @Override
    CarteiraModel save(CarteiraModel s);

    @Override
    Iterable<CarteiraModel> findAll();

    @Override
    Optional<CarteiraModel> findById(String s);
    
}
