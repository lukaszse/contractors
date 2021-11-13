package org.lukaszse.contractorsapp.service;

import org.lukaszse.contractorsapp.model.Contractor;

import java.util.List;

public interface ContractorService {
    Contractor getContractor(Integer id);
    boolean addContractor(Contractor contractor);
    boolean editContractor(Contractor contractor);
    boolean deleteContractor(Integer id);
    List<Contractor> findAll();
}
