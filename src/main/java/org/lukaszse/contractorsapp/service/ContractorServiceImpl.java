package org.lukaszse.contractorsapp.service;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Contractor;
import org.lukaszse.contractorsapp.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContractorServiceImpl implements ContractorService {

    // == fields ==
    private final ContractorRepository repository;

    // == constructors ==
    @Autowired
    public ContractorServiceImpl(ContractorRepository repository) {
        this.repository = repository;
    }


/*    @PostConstruct
    public void init() {
        System.out.println("===================================================");
        System.out.println("=========  this is your database print  ===========");
        System.out.println(findAll().toString());
        System.out.println("================  this is the end  ================");
    }*/

    // == methods ==
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
    public boolean editContractor(Contractor contractor) {    // == duplicated code - to be modified ==
        repository.save(contractor);                                        // == duplicated code - to be modified ==
        return true;                                                  // == duplicated code - to be modified ==
    }

    @Override
    public boolean deleteContractor(Integer Contractorid) {
        repository.deleteById(Contractorid);
        return true;
    }

    //    @Override
//    public ContractorsRepository getRepository() {
//        return repository;
//    }
}
