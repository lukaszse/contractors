package org.lukaszse.contractorsapp.contractors;

import java.util.List;
import java.util.Optional;

// to limit access only to selected methods from crud
public interface
ContractorRepository {

    Optional<Contractor> findById(Integer id);

    List<Contractor> findAll();

    void deleteById(Integer id);

    Contractor save(Contractor entity);
}
