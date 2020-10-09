package org.lukaszse.contractorsapp.contractors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContractorsRepository extends JpaRepository<Contractor, Integer> {

}