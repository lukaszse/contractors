package org.lukaszse.contractors;

import org.lukaszse.contractors.Contractor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContractorService {
    Contractor getContractor(int id);
    ContractorsRepository addContractor(Contractor contractor);
    ContractorsRepository editContractor(Contractor contractor);
    ContractorsRepository deleteContractor(int id);
    List<Contractor> findAll();
}
