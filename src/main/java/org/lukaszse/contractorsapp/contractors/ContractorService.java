package org.lukaszse.contractorsapp.contractors;

import java.util.List;

public interface ContractorService {
    Contractor getContractor(Integer id);
    boolean addContractor(Contractor contractor);
    boolean editContractor(Contractor contractor);
    boolean deleteContractor(Integer id);
    List<Contractor> findAll();
}
