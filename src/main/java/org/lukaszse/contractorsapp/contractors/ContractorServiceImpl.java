package org.lukaszse.contractorsapp.contractors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class ContractorServiceImpl implements ContractorService {

    // == fields ==
    private final ContractorsRepository repository;

    // == constructors ==
    @Autowired
    public ContractorServiceImpl(ContractorsRepository repository) {
        this.repository = repository;
    }



    @PostConstruct
    public void init() {
        System.out.println("===================================================");
        System.out.println("=========  this is your database print  ===========");
        System.out.println(findAll().toString());
        System.out.println("================  this is the end  ================");
    }

    // == methods ==
    @Override
    public List<Contractor> findAll() {
        return repository.findAll();
    }

    @Override
    public Contractor getContractor(int id) {
        return repository.getOne(id);
    }

    @Override
    public ContractorsRepository addContractor(Contractor contractor) {
    repository.save(contractor);
    return repository;
    }

    @Override
    public ContractorsRepository editContractor(Contractor contractor) {    // == duplicated code - to be modified ==
        repository.save(contractor);                                        // == duplicated code - to be modified ==
        return repository;                                                  // == duplicated code - to be modified ==
    }

    @Override
    public ContractorsRepository deleteContractor(int id) {
        repository.deleteById(id);
        return repository;
    }

    //    @Override
//    public ContractorsRepository getRepository() {
//        return repository;
//    }
}
