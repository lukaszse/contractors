package org.lukaszse.contractorsapp.contractors;

import java.util.List;
import java.util.Optional;

public interface
ContractorRepository {

    Optional<Contractor> findById(Integer id);

    List<Contractor> findAll();

    void deleteById(Integer id);

    Contractor save(Contractor entity);
}
