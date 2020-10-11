package org.lukaszse.contractorsapp.contractors;

import java.util.List;

public interface ContractorService {
    Contractor getContractor(Integer id);
    ContractorsRepository addContractor(Contractor contractor);
    ContractorsRepository editContractor(Contractor contractor);
    ContractorsRepository deleteContractor(Integer id);
    List<Contractor> findAll();
}
