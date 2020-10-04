package org.lukaszse.contractorsapp.contractors;

import java.util.List;

public interface ContractorService {
    Contractor getContractor(int id);
    ContractorsRepository addContractor(Contractor contractor);
    ContractorsRepository editContractor(Contractor contractor);
    ContractorsRepository deleteContractor(int id);
    List<Contractor> findAll();
}
