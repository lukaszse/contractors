package org.lukaszse.service;

import org.lukaszse.contractors.Contractor;

import java.util.List;

public interface ContractorService {
    String getContractorList();
    boolean addContractor(Contractor contractor);
    boolean isEmpty();
    List<Contractor> findAll();
}
