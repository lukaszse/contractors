package org.lukaszse.contractorsapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Contractor;
import org.lukaszse.contractorsapp.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractorServiceImpl implements ContractorService {

    private final ContractorRepository repository;


    @Override
    public List<Contractor> findAll() {
        return repository.findAll();
    }

    @Override
    public Contractor getContractor(Integer id) {
            return repository.findById(id)
                    .orElse(null);
    }

    @Override
    public boolean addContractor(Contractor newContractor) {

        boolean ContractorExist
                = repository.findAll()
                            .stream()
                            .anyMatch(existingContr -> existingContr.getName().equals(newContractor.getName()));

        if(ContractorExist) {
            log.info("There is already contractor with given name in database");
            throw new IllegalArgumentException("Contractor with given name already exist!");
        }

        repository.save(newContractor);
        return true;
    }

    @Override
    public boolean editContractor(Contractor contractor) {
        repository.save(contractor);
        return true;
    }

    @Override
    public boolean deleteContractor(Integer Contractorid) {
        repository.deleteById(Contractorid);
        return true;
    }
}
