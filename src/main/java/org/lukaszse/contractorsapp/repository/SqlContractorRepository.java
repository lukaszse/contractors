package org.lukaszse.contractorsapp.repository;

import org.lukaszse.contractorsapp.model.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SqlContractorRepository extends ContractorRepository, JpaRepository<Contractor, Integer> {

}
